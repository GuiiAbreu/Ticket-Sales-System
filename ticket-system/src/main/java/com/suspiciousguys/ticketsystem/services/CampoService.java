package com.suspiciousguys.ticketsystem.services;

import com.suspiciousguys.ticketsystem.dtos.CampoDTO;
import com.suspiciousguys.ticketsystem.dtos.ModalidadeDTO;
import com.suspiciousguys.ticketsystem.models.CampoModel;
import com.suspiciousguys.ticketsystem.models.ModalidadeModel;
import com.suspiciousguys.ticketsystem.repositories.CampoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampoService {

    private final CampoRepository campoRepository;
    private final ModalidadeService modalidadeService;

    public CampoService(CampoRepository campoRepository, ModalidadeService modalidadeService) {
        this.campoRepository = campoRepository;
        this.modalidadeService = modalidadeService;
    }


    public CampoDTO createCampo(CampoDTO campoDTO, Long id) {
        CampoModel newCampo = new CampoModel(campoDTO);
        ModalidadeDTO modalidade = modalidadeService.getModalidadeById(id);
        ModalidadeModel modalidadeModel = new ModalidadeModel(modalidade);
        newCampo.setModalidade(modalidadeModel);
        campoRepository.save(newCampo);
        return new CampoDTO(newCampo);
    }

    public CampoDTO updateCampo(CampoDTO campoDTO, Long id) {
        CampoModel campo = campoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Campo não encontrado."));
        campo.setNomeCampo(campoDTO.getNomeCampo());
        campo.setDescricaoCampo(campoDTO.getDescricaoCampo());

        if(campoDTO.getModalidadeId() != null) {
            ModalidadeModel newModalidade = new ModalidadeModel(modalidadeService.getModalidadeById(campoDTO.getModalidadeId()));
            campo.setModalidade(newModalidade);
            campoRepository.save(campo);
            return new CampoDTO(campo);
        } else {
        campoRepository.save(campo);
        return new CampoDTO(campo);
        }
    }

    public void deleteCampo(Long id) {
        CampoModel campo = campoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Campo não encontrado."));
        campoRepository.deleteById(id);
    }

    public List<CampoDTO> getAllCampos() {
        List<CampoModel> campos = campoRepository.findAll();
        List<CampoDTO> camposDTO = campos.stream().map(CampoDTO::new).toList();

        if (!camposDTO.isEmpty()) {
            return camposDTO;
        } else {
            return new ArrayList<>();
        }
    }

    public CampoDTO getCampoById(Long id) {
        CampoModel campo = campoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Campo não encontrado."));
        return new CampoDTO(campo);
    }
}