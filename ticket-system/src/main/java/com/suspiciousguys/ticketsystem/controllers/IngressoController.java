package com.suspiciousguys.ticketsystem.controllers;

import com.suspiciousguys.ticketsystem.dtos.IngressoDTO;
import com.suspiciousguys.ticketsystem.infra.ApiResponse;
import com.suspiciousguys.ticketsystem.services.IngressoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingressos")
public class IngressoController {

    private final IngressoService ingressoService;

    public IngressoController(IngressoService ingressoService) {
        this.ingressoService = ingressoService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<IngressoDTO>> createIngresso (@RequestBody @Valid IngressoDTO dto) {
        IngressoDTO ingressoCriado = ingressoService.create(dto);
        return ApiResponse.createResponse("Ingresso criado com sucesso!", ingressoCriado, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<IngressoDTO>> updateIngresso (@RequestBody @Valid IngressoDTO dto,@PathVariable Long id) {
        IngressoDTO ingressoAtualizado = ingressoService.update(dto, id);
        return ApiResponse.createResponse("Ingresso atualizado com sucesso!", ingressoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteIngresso (@PathVariable Long id) {
        ingressoService.delete(id);
        return ApiResponse.createResponse("Ingresso deletado com sucesso!", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<IngressoDTO>>> getAllIngressos() {
        List<IngressoDTO> ingressos = ingressoService.getAll();
        return ApiResponse.createResponse("todos os ingressos",ingressos,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<IngressoDTO>> getIngressoById(@PathVariable Long id) {
        IngressoDTO ingressoCriado = ingressoService.getById(id);
        return ApiResponse.createResponse("sucesso",ingressoCriado, HttpStatus.OK);
    }
}
