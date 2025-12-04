package com.suspiciousguys.ticketsystem.services;

import com.suspiciousguys.ticketsystem.dtos.ModalidadeDTO;
import com.suspiciousguys.ticketsystem.models.ModalidadeModel;
import com.suspiciousguys.ticketsystem.repositories.ModalidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModalidadeService {

    private final ModalidadeRepository modalidadeRepository;

    public ModalidadeService(ModalidadeRepository modalidadeRepository) {
        this.modalidadeRepository = modalidadeRepository;
    }

    public ModalidadeDTO createModalidade(ModalidadeDTO modalidadeDTO) {
        ModalidadeModel Modalidade = new ModalidadeModel(modalidadeDTO);
        this.modalidadeRepository.save(Modalidade);
        return new ModalidadeDTO(Modalidade);
    }

    public ModalidadeDTO updateModalidade(ModalidadeDTO modalidadeDTO, Long id) {
        ModalidadeModel newModalidade = new ModalidadeModel(modalidadeDTO);
        ModalidadeModel oldModalidade = this.modalidadeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Modalidade não encontrada."));
            newModalidade.setId(oldModalidade.getId());
            this.modalidadeRepository.save(newModalidade);
            return new ModalidadeDTO(newModalidade);
    }

    public void deleteModalidade(Long id) {
        ModalidadeModel modalidade = this.modalidadeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Modalidade não encontrada."));
        this.modalidadeRepository.deleteById(modalidade.getId());
    }


    public List<ModalidadeDTO> getAllModalidades() {
        List<ModalidadeModel> modalidades = this.modalidadeRepository.findAll();
        List<ModalidadeDTO> modalidadesDTO = modalidades.stream().map(ModalidadeDTO::new).toList();
        if(!modalidadesDTO.isEmpty()){
            return modalidadesDTO;
        } else {
            return new ArrayList<>();
        }
    }

    public ModalidadeDTO getModalidadeById(Long id) {
        ModalidadeModel modalidade = this.modalidadeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Modalidade não encontrada."));
        return new ModalidadeDTO(modalidade);
    }

}
