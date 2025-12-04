package com.suspiciousguys.ticketsystem.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CompraRequestDTO {

    @NotNull(message = "O ID do cliente é obrigatório.")
    private Long clienteId;

    @Valid // Valida os itens de compra
    private List<ItemCompraDTO> itensCompra;

}