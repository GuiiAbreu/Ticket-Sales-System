package com.suspiciousguys.ticketsystem.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suspiciousguys.ticketsystem.dtos.*;
import com.suspiciousguys.ticketsystem.exceptions.IngressosEsgotadosException;
import com.suspiciousguys.ticketsystem.models.*;
import com.suspiciousguys.ticketsystem.producer.EmailRequestProducer;
import com.suspiciousguys.ticketsystem.producer.IngressoRequestProducer;
import com.suspiciousguys.ticketsystem.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GestorVendasService {

    private CompraRepository compraRepository;
    private ModalidadeRepository modalidadeRepository;
    private ItemCompraRepository itemCompraRepository;
    private ClienteRepository clienteRepository;
    private IngressoRepository ingressoRepository;
    private CampoRepository campoRepository;
    private RespostaCampoRepository respostaCampoRepository;
    private IngressoCompradoRepository ingressoCompradoRepository;
    private EmailRequestProducer emailRequestProducer;
    private IngressoRequestProducer ingressoRequestProducer;

    public GestorVendasService(IngressoCompradoRepository ingressoCompradoRepository, CompraRepository compraRepository, ModalidadeRepository modalidadeRepository, ItemCompraRepository itemCompraRepository, ClienteRepository clienteRepository, IngressoRepository ingressoRepository, CampoRepository campoRepository, RespostaCampoRepository respostaCampoRepository, EmailRequestProducer emailRequestProducer, IngressoRequestProducer ingressoRequestProducer) {
        this.compraRepository = compraRepository;
        this.modalidadeRepository = modalidadeRepository;
        this.itemCompraRepository = itemCompraRepository;
        this.clienteRepository = clienteRepository;
        this.ingressoRepository = ingressoRepository;
        this.campoRepository = campoRepository;
        this.respostaCampoRepository = respostaCampoRepository;
        this.ingressoCompradoRepository = ingressoCompradoRepository;
        this.emailRequestProducer = emailRequestProducer;
        this.ingressoRequestProducer = ingressoRequestProducer;
    }


    @Transactional
    public CompraDTO comprarIngressos(CompraRequestDTO compraRequestDTO) {

        List<IngressoCompradoModel> listaIngresso = new ArrayList<>();

        ClienteModel cliente = clienteRepository.findById(compraRequestDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));


        CompraModel compra = new CompraModel();
        compra.setCliente(cliente);
        compra.setDataCompra(LocalDateTime.now());
        compraRepository.save(compra);


        for (ItemCompraDTO itemCompraDTO : compraRequestDTO.getItensCompra()) {
            Long ingressoId = itemCompraDTO.getIngressoId();
            Long modalidadeId = itemCompraDTO.getModalidadeId();


            IngressoModel ingresso = ingressoRepository.findById(ingressoId)
                    .orElseThrow(() -> new RuntimeException("Ingresso não encontrado"));


            ModalidadeModel modalidade = modalidadeRepository.findById(modalidadeId)
                    .orElseThrow(() -> new RuntimeException("Modalidade não encontrada"));


            if (modalidade.getIngressosDisponiveis() < 1) {
                throw new IngressosEsgotadosException("Ingressos esgotados para a modalidade: " + modalidadeId);
            }


            IngressoCompradoModel ingressoComprado = new IngressoCompradoModel();
            ingressoComprado.setIngresso(ingresso);
            ingressoComprado.setModalidade(modalidade);
            ingressoComprado.setCliente(cliente);
            ingressoCompradoRepository.save(ingressoComprado);
            listaIngresso.add(ingressoComprado);


            for (RespostaCampoDTO respostaDTO : itemCompraDTO.getRespostasCampos()) {
                CampoModel campo = campoRepository.findById(respostaDTO.getCampoId())
                        .orElseThrow(() -> new RuntimeException("Campo não encontrado"));

                RespostaCampoModel respostaCampo = new RespostaCampoModel();
                respostaCampo.setResposta(respostaDTO.getResposta());
                respostaCampo.setCampo(campo);
                respostaCampo.setIngressoComprado(ingressoComprado);
                respostaCampoRepository.save(respostaCampo);
            }


            modalidade.setIngressosDisponiveis(modalidade.getIngressosDisponiveis() - 1);
            modalidadeRepository.save(modalidade);


            ItemCompraModel itemCompra = new ItemCompraModel();
            itemCompra.setCompra(compra);
            itemCompra.setIngressoComprado(ingressoComprado);
            itemCompra.setModalidade(modalidade);
            itemCompraRepository.save(itemCompra);
        }

        EmailDTO emailDTO = new EmailDTO(cliente.getEmail(), "Ingresso comprado");

        this.emailRequestProducer.integrate(emailDTO);

        for (IngressoCompradoModel ingressos:listaIngresso){
            Integer hash = ingressos.hashCode();

            IngressoProducerDTO ingressoProducerDTO = new IngressoProducerDTO(hash.toString(), cliente.getEmail());

            this.ingressoRequestProducer.integrate(ingressoProducerDTO);
        }

        return new CompraDTO(compra);
    }
}