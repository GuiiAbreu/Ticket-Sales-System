package com.suspiciousguys.ticketsystem.controllers;

import com.suspiciousguys.ticketsystem.dtos.OrganizadorDTO;
import com.suspiciousguys.ticketsystem.models.OrganizadorModel;
import com.suspiciousguys.ticketsystem.services.OrganizadorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/organizadores")
@RestController
public class OrganizadorController {

    private final OrganizadorService organizadorService;

    public OrganizadorController(OrganizadorService organizadorService) {
        this.organizadorService = organizadorService;
    }

    @PostMapping
    public ResponseEntity<OrganizadorModel> create(@RequestBody @Valid OrganizadorDTO organizadorDTO) {
        this.organizadorService.create(organizadorDTO);
        return ResponseEntity.ok().header("message", "Organizador criado com sucesso.").build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizadorModel> update(@PathVariable Long id, @RequestBody @Valid OrganizadorDTO organizadorDTO) {
        this.organizadorService.update(organizadorDTO, id);
        return ResponseEntity.ok().header("message", "Organizador criado com sucesso.").build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrganizadorModel> delete(@PathVariable Long id) {
        this.organizadorService.delete(id);
        return ResponseEntity.ok().header("message", "Organizador deletado com sucesso.").build();
    }

    @GetMapping
    public ResponseEntity<List<OrganizadorDTO>> findAll() {
        return ResponseEntity.ok().body(this.organizadorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizadorModel> findById(@PathVariable Long id) {
        OrganizadorModel organizador = organizadorService.findById(id);
        return ResponseEntity.ok().body(organizador);
    }


}
