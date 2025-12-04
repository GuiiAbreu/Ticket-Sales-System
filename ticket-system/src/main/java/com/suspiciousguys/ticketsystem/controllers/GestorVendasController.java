package com.suspiciousguys.ticketsystem.controllers;

import com.suspiciousguys.ticketsystem.dtos.CompraDTO;
import com.suspiciousguys.ticketsystem.dtos.CompraRequestDTO;
import com.suspiciousguys.ticketsystem.exceptions.IngressosEsgotadosException;
import com.suspiciousguys.ticketsystem.infra.ApiResponse;
import com.suspiciousguys.ticketsystem.services.GestorVendasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendas")
public class GestorVendasController {

    private final GestorVendasService gestorVendasService;

    public GestorVendasController(GestorVendasService gestorVendasService) {
        this.gestorVendasService = gestorVendasService;
    }


    @PostMapping("/comprar")
    public ResponseEntity<ApiResponse<CompraDTO>> comprarIngressos(@RequestBody @Valid CompraRequestDTO compraRequestDTO) {
        try {
            CompraDTO compraDTO = gestorVendasService.comprarIngressos(compraRequestDTO);
            return ApiResponse.createResponse("Compra realizada com sucesso!", compraDTO, HttpStatus.CREATED);
        } catch (IngressosEsgotadosException e) {
            return ApiResponse.createResponse(e.getMessage(), null, HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return ApiResponse.createResponse("Erro ao processar a compra: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}