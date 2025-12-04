package com.suspiciousguys.ticketsystem.controllers;

import com.suspiciousguys.ticketsystem.dtos.CompraDTO;
import com.suspiciousguys.ticketsystem.infra.ApiResponse;
import com.suspiciousguys.ticketsystem.services.CompraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }


    // Exclui uma compra
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCompra(@PathVariable Long id) {
        compraService.deleteCompra(id);
        return ApiResponse.createResponse("Compra deletada com sucesso!", HttpStatus.OK);
    }

    // Lista todas as compras
    @GetMapping
    public ResponseEntity<ApiResponse<List<CompraDTO>>> getAllCompras() {
        List<CompraDTO> compras = compraService.getAllCompras();
        return ApiResponse.createResponse("Lista de compras", compras, HttpStatus.OK);
    }

    // Busca uma compra por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CompraDTO>> getCompraById(@PathVariable Long id) {
        CompraDTO compra = compraService.getCompraById(id);
        return ApiResponse.createResponse("Compra encontrada com sucesso!", compra, HttpStatus.OK);
    }
}