package com.suspiciousguys.ticketsystem.services;

import com.suspiciousguys.ticketsystem.dtos.EventoDTO;
import com.suspiciousguys.ticketsystem.exceptions.EventosConflitantesException;
import com.suspiciousguys.ticketsystem.models.DataEventoModel;
import com.suspiciousguys.ticketsystem.models.EventoModel;
import com.suspiciousguys.ticketsystem.models.OrganizadorModel;
import com.suspiciousguys.ticketsystem.repositories.DatasEventoRepository;
import com.suspiciousguys.ticketsystem.repositories.EventoRepository;
import com.suspiciousguys.ticketsystem.repositories.OrganizadorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GestorEventosService {

    private final EventoRepository eventoRepository;
    private final DatasEventoRepository datasEventoRepository;
    private final OrganizadorRepository organizadorRepository;

    public GestorEventosService(EventoRepository eventoRepository, DatasEventoRepository datasEventoRepository, OrganizadorRepository organizadorRepository) {
        this.eventoRepository = eventoRepository;
        this.datasEventoRepository = datasEventoRepository;
        this.organizadorRepository = organizadorRepository;
    }

    public void addDataToEvento(EventoDTO eventoDTO, Long eventoId) {
        LocalDate[] datasFornecidas = eventoDTO.getDatas();
        EventoModel evento = eventoRepository.findById(eventoId).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        ;
        if (evento == null) {
            throw new RuntimeException("Evento informado não encontrado.");
        }

        // Verificar se há eventos conflitantes
        List<EventoModel> eventosConflitantes = verificarConflitos(eventoDTO, evento);
        if (!eventosConflitantes.isEmpty()) {
            List<EventoDTO> eventosConflitantesDTO = eventosConflitantes.stream().map(EventoDTO::new).toList();
            throw new EventosConflitantesException(eventosConflitantesDTO);
        }
        // Processar cada data fornecida no DTO
        for (LocalDate data : datasFornecidas) {
            DataEventoModel dataEvento = datasEventoRepository.findByData(data);
            if (dataEvento == null) {
                dataEvento = new DataEventoModel(data);
                datasEventoRepository.save(dataEvento);
            }
            // Associa o evento à data e a data ao evento
            dataEvento.getEventos().add(evento);
            evento.getDatasEvento().add(dataEvento);
        }
        eventoRepository.save(evento);
    }

    public void deleteDataFromEvento(EventoDTO eventoDTO, Long eventoId) {
        LocalDate[] datasParaRemover = eventoDTO.getDatas();
        EventoModel evento = eventoRepository.findById(eventoId).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        if (evento == null) {
            throw new RuntimeException("Evento informado não encontrado.");
        }
        // Percorre cada data fornecida e tenta remover do evento
        for (LocalDate data : datasParaRemover) {
            DataEventoModel dataEvento = datasEventoRepository.findByData(data);
            if (dataEvento != null) {
                // Verifica se a data está associada ao evento antes de removê-la
                if (evento.getDatasEvento().contains(dataEvento)) {
                    evento.getDatasEvento().remove(dataEvento);
                    dataEvento.getEventos().remove(evento);
                } else {
                    throw new RuntimeException("A data não está associada ao evento.");
                }

                if (dataEvento.getEventos().isEmpty()) {
                    datasEventoRepository.delete(dataEvento);
                }
            } else {
                throw new RuntimeException("Data não encontrada.");
            }
        }
        eventoRepository.save(evento);
    }


    public void addOrganizadorToEvento(Long eventoId, Long organizadorId) {
        EventoModel evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        OrganizadorModel organizador = organizadorRepository.findById(organizadorId)
                .orElseThrow(() -> new RuntimeException("Organizador não encontrado"));

        evento.getOrganizadores().add(organizador);
        organizador.getEventos().add(evento);

        eventoRepository.save(evento);
        organizadorRepository.save(organizador);
    }

    public void removeOrganizadorFromEvento(Long eventoId, Long organizadorId) {
        EventoModel evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        OrganizadorModel organizador = organizadorRepository.findById(organizadorId)
                .orElseThrow(() -> new RuntimeException("Organizador não encontrado"));

        evento.getOrganizadores().remove(organizador);
        organizador.getEventos().remove(evento);

        eventoRepository.save(evento);
        organizadorRepository.save(organizador);
    }

    public List<EventoModel> verificarConflitos(EventoDTO eventoDTO, EventoModel evento) {
        LocalDate[] datas = eventoDTO.getDatas();
        Set<EventoModel> eventosConflitantesSet = new HashSet<>();

        // Percorre cada data e verifica se há conflito de evento para o local, horário e data
        for (LocalDate data : datas) {
            List<EventoModel> conflitosPorData = eventoRepository.findEventosComConflito(
                    evento.getLocal(),
                    data,
                    evento.getHorario(),
                    evento.getHorarioFim()
            );
            System.out.println(evento.getLocal());
            System.out.println(evento.getHorario());
            System.out.println(evento.getHorarioFim());
            eventosConflitantesSet.addAll(conflitosPorData);
        }
        List<EventoModel> eventosConflitantes = new ArrayList<>(eventosConflitantesSet);

        List<EventoDTO> eventosConflitantesDTO = eventosConflitantes.stream().map(EventoDTO::new).toList();

        // Se houver eventos conflitantes, lança a exceção
        if (!eventosConflitantes.isEmpty()) {
            throw new EventosConflitantesException(eventosConflitantesDTO);
        }
        return eventosConflitantes;
    }
}

