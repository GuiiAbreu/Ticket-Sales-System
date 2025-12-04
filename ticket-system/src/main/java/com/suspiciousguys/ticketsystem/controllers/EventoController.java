package com.suspiciousguys.ticketsystem.controllers;

import com.suspiciousguys.ticketsystem.dtos.EventoDTO;
import com.suspiciousguys.ticketsystem.infra.ApiResponse;
import com.suspiciousguys.ticketsystem.services.EventoService;
import com.suspiciousguys.ticketsystem.services.GestorEventosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;
    private final GestorEventosService gestorEventosService;

    public EventoController(EventoService eventoService, GestorEventosService gestorEventosService) {
        this.eventoService = eventoService;
        this.gestorEventosService = gestorEventosService;
    }

    @PostMapping
    public ResponseEntity<EventoDTO> create(@RequestBody @Valid EventoDTO eventoDTO) {
        this.eventoService.create(eventoDTO);
        return ResponseEntity.ok().header("message","Evento criado com sucesso.").build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO> update(@PathVariable Long id, @RequestBody @Valid EventoDTO eventoDTO) {
        this.eventoService.update(eventoDTO,id);
        return ResponseEntity.ok().header("message","Evento atualizado com sucesso.").build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.eventoService.delete(id);
        return ResponseEntity.ok().header("message","Evento removido com sucesso.").build();
    }

    @GetMapping
    public ResponseEntity<List<EventoDTO>> getAll() {
        return ResponseEntity.ok().body(this.eventoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.eventoService.getById(id));
    }

    @PostMapping("/{eventoId}/organizadores/{organizadorId}")
    public ResponseEntity<ApiResponse<Void>> addOrganizadorToEvento(
            @PathVariable Long eventoId,
            @PathVariable Long organizadorId
    ) {
        gestorEventosService.addOrganizadorToEvento(eventoId, organizadorId);
        return ApiResponse.createResponse("Organizador adicionado ao evento com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{eventoId}/organizadores/{organizadorId}")
    public ResponseEntity<ApiResponse<Void>> removeOrganizadorFromEvento(
            @PathVariable Long eventoId,
            @PathVariable Long organizadorId
    ) {
        gestorEventosService.removeOrganizadorFromEvento(eventoId, organizadorId);
        return ApiResponse.createResponse("Organizador removido da organização do evento com sucesso.", HttpStatus.OK);
    }

    @PostMapping("/{eventoId}/datas")
    public ResponseEntity<ApiResponse<List<EventoDTO>>> addDataToEvento(@PathVariable Long eventoId,@RequestBody @Valid EventoDTO eventoDTO) {
        gestorEventosService.addDataToEvento(eventoDTO,eventoId);
        return ApiResponse.createResponse("Data adicionada ao evento com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{eventoId}/datas")
    public ResponseEntity<ApiResponse<List<EventoDTO>>> deleteDataFromEvento(@PathVariable Long eventoId, @RequestBody @Valid EventoDTO eventoDTO) {
        gestorEventosService.deleteDataFromEvento(eventoDTO, eventoId);
        return ApiResponse.createResponse("Data removida do evento com sucesso.", HttpStatus.OK);
    }

}
