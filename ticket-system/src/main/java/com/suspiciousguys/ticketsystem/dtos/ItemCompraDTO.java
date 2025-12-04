package com.suspiciousguys.ticketsystem.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ItemCompraDTO {

    @NotNull(message = "O ID do ingresso é obrigatório.")
    private Long ingressoId;

    @NotNull(message = "O ID da modalidade é obrigatório.")
    private Long modalidadeId;

    @Valid // Valida as respostas dos campos
    private List<RespostaCampoDTO> respostasCampos;
}