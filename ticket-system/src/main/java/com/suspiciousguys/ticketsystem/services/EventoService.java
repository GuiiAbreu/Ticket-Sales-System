package com.suspiciousguys.ticketsystem.services;

import com.suspiciousguys.ticketsystem.dtos.EventoDTO;
import com.suspiciousguys.ticketsystem.models.EventoModel;
import com.suspiciousguys.ticketsystem.repositories.EventoRepository;
import com.suspiciousguys.ticketsystem.repositories.OrganizadorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {
    private EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository, OrganizadorRepository organizadorRepository) {
        this.eventoRepository = eventoRepository;
    }

    public void create(EventoDTO eventoDTO) {
        EventoModel eventoModel = new EventoModel(eventoDTO);
        eventoRepository.save(eventoModel);
    }

    public EventoDTO update(EventoDTO eventoDTO, Long id) {
        EventoModel oldEvento = eventoRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        EventoModel newEvento = new EventoModel(eventoDTO);
        newEvento.setId(oldEvento.getId());
        eventoRepository.save(newEvento);
        return new EventoDTO(newEvento);
    }

    public void delete(Long id) {
        EventoModel Evento = this.eventoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado!"));
        if (Evento != null) {
            this.eventoRepository.delete(Evento);
        }
    }

    public List<EventoDTO> getAll() {
        List<EventoModel> eventos = this.eventoRepository.findAll();
        return eventos.stream().map(EventoDTO::new).toList();
    }

    public EventoDTO getById(Long id) {
        EventoModel eventoModel = this.eventoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado!"));
        return new EventoDTO(eventoModel);
    }



}
