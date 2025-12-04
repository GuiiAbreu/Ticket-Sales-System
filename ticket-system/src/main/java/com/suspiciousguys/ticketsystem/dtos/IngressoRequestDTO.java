package com.suspiciousguys.ticketsystem.dtos;

import lombok.Data;

import java.util.List;

@Data
public class IngressoRequestDTO {
    private List<RespostaCampoDTO> respostasCampos;
}