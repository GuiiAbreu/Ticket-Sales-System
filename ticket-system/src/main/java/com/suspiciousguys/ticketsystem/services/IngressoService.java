package com.suspiciousguys.ticketsystem.services;

import com.suspiciousguys.ticketsystem.dtos.IngressoDTO;
import com.suspiciousguys.ticketsystem.models.IngressoModel;
import com.suspiciousguys.ticketsystem.repositories.IngressoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngressoService {

    private final IngressoRepository ingressoRepository;

    public IngressoService(IngressoRepository ingressoRepository) {
        this.ingressoRepository = ingressoRepository;
    }

    public IngressoDTO create(IngressoDTO ingressoDTO) {
        IngressoModel ingressoComprado = new IngressoModel(ingressoDTO);
        IngressoModel ingressoSalvo = ingressoRepository.save(ingressoComprado);
        IngressoDTO ingresso = new IngressoDTO(ingressoSalvo);
        return ingresso;
    }

    public IngressoDTO update(IngressoDTO ingressoDTO, Long id) {
        IngressoModel newIngresso = new IngressoModel(ingressoDTO);
        IngressoModel oldIngresso = ingressoRepository.findById(id).orElseThrow(()-> new RuntimeException("Ingresso não encontrado."));
        newIngresso.setId(oldIngresso.getId());
        ingressoRepository.save(newIngresso);
        return new IngressoDTO(newIngresso);
    }

    public void delete(Long id) {
        IngressoModel ingresso = ingressoRepository.findById(id).orElseThrow(()-> new RuntimeException("Ingresso não encontrado."));
        ingressoRepository.deleteById(id);
    }

    public IngressoDTO getById(Long id) {
        return new IngressoDTO(ingressoRepository.findById(id).orElseThrow(()-> new RuntimeException("Ingresso não encontrado.")));
    }

    public List<IngressoDTO> getAll() {
        List<IngressoModel> ingressos = ingressoRepository.findAll();
        List<IngressoDTO> ingressoDTOs = ingressos.stream().map(IngressoDTO::new).toList();
        return ingressoDTOs;
    }


}

