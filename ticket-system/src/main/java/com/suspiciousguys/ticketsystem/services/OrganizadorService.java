package com.suspiciousguys.ticketsystem.services;

import com.suspiciousguys.ticketsystem.dtos.OrganizadorDTO;
import com.suspiciousguys.ticketsystem.models.OrganizadorModel;
import com.suspiciousguys.ticketsystem.repositories.OrganizadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizadorService {
    private final OrganizadorRepository organizadorRepository;

    public OrganizadorService(OrganizadorRepository organizadorRepository) {
        this.organizadorRepository = organizadorRepository;
    }

    public void create(OrganizadorDTO organizador) {
        OrganizadorModel organizadorModel = new OrganizadorModel(organizador);
        this.organizadorRepository.save(organizadorModel);
    }

    public void update(OrganizadorDTO organizadorDTO, Long id) {
        OrganizadorModel oldOrganizador = organizadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Organizador não encontrado!"));
        if (oldOrganizador != null) {
            OrganizadorModel newOrganizador = new OrganizadorModel(organizadorDTO);
            newOrganizador.setId(id);
            newOrganizador.setEventos(oldOrganizador.getEventos());
            this.organizadorRepository.save(newOrganizador);
        }
     }

    public void delete(Long id) {
        OrganizadorModel organizador = organizadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Organizador não encontrado!"));
        if (organizador != null) {
        this.organizadorRepository.delete(organizador);
        }
    }

    public OrganizadorModel findById(Long id) {
        return this.organizadorRepository.findById(id).orElseThrow(() -> new RuntimeException("organizador não encontrado."));
    }

    public List<OrganizadorDTO> findAll() {
        List<OrganizadorModel> organizadores = this.organizadorRepository.findAll();
        return organizadores.stream().map(OrganizadorDTO::new).toList();
    }



}
