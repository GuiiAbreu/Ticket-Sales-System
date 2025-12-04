package com.suspiciousguys.ticketsystem.services;

import com.suspiciousguys.ticketsystem.dtos.RespostaCampoDTO;
import com.suspiciousguys.ticketsystem.models.RespostaCampoModel;
import com.suspiciousguys.ticketsystem.repositories.CampoRepository;
import com.suspiciousguys.ticketsystem.repositories.RespostaCampoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespostaCampoService {

    private final RespostaCampoRepository respostaCampoRepository;
    private final CampoRepository campoRepository;
    private final CampoService campoService;
    private final IngressoService ingressoService;

    RespostaCampoService(RespostaCampoRepository respostaCampoRepository, CampoRepository campoRepository, CampoService campoService, IngressoService ingressoService) {
        this.respostaCampoRepository = respostaCampoRepository;
        this.campoRepository = campoRepository;
        this.campoService = campoService;
        this.ingressoService = ingressoService;
    }

/*
    public RespostaCampoDTO create(RespostaCampoDTO respostaCampoDTO) {
        RespostaCampoModel newResposta = new RespostaCampoModel(respostaCampoDTO);
        CampoModel campoModel = new CampoModel(campoService.getCampoById(respostaCampoDTO.getCampoId()));
        IngressoModel ingressoComprado = new IngressoModel(ingressoService.getById(respostaCampoDTO.getIngressoId()));
        newResposta.setCampo(campoModel);
        newResposta.setIngressoComprado(ingressoComprado);
        respostaCampoRepository.save(newResposta);
        return new RespostaCampoDTO(newResposta);
    }

    public RespostaCampoDTO update (RespostaCampoDTO respostaCampoDTO, Long idResposta) {
        RespostaCampoModel resposta = respostaCampoRepository.findById(idResposta).orElseThrow(()-> new EntityNotFoundException("Resposta não encontrada"));
        RespostaCampoModel respostaUpdated = new RespostaCampoModel(respostaCampoDTO);
        resposta.setResposta(respostaUpdated.getResposta());
        respostaCampoRepository.save(resposta);
        return new RespostaCampoDTO(resposta);
    }

    public void delete(Long idResposta) {
        if (respostaCampoRepository.existsById(idResposta)) {
            respostaCampoRepository.deleteById(idResposta);
        } else {
            throw new EntityNotFoundException("Resposta não encontrada.");
        }
    }
*/

    public RespostaCampoDTO findById(Long idResposta) {
        RespostaCampoModel resposta = respostaCampoRepository.findById(idResposta).orElseThrow(()-> new EntityNotFoundException("Resposta não encontrada"));
        return new RespostaCampoDTO(resposta);
    }

    public List<RespostaCampoDTO> findAll() {
        List<RespostaCampoModel> respostas = respostaCampoRepository.findAll();
        List<RespostaCampoDTO> respostasDTO = respostas.stream().map(RespostaCampoDTO::new).toList();
        return respostasDTO;
    }
}
