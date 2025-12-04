package com.suspiciousguys.ticketsystem.controllers;

import com.suspiciousguys.ticketsystem.dtos.ClienteDTO;
import com.suspiciousguys.ticketsystem.models.ClienteModel;
import com.suspiciousguys.ticketsystem.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteModel> create(@RequestBody @Valid ClienteDTO clienteDTO) {
        this.clienteService.create(clienteDTO);
        return ResponseEntity.ok().header("message","Cliente criado com sucesso.").build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> update(@RequestBody @Valid ClienteDTO clienteDTO, @PathVariable Long id) {
        this.clienteService.update(clienteDTO, id);
        return ResponseEntity.ok().header("message","Cliente atualizado com sucesso.").build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteModel> deleteById(@PathVariable Long id) {
        this.clienteService.delete(id);
        return ResponseEntity.ok().header("message","Cliente deletado com sucesso.").build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAll() {
        return ResponseEntity.ok().body(this.clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.clienteService.findById(id));
    }
}
