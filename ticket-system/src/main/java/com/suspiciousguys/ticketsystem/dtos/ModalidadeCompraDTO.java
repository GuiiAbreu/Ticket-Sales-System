package com.suspiciousguys.ticketsystem.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ModalidadeCompraDTO {
    private Long modalidadeId; // ID da modalidade
    private Integer quantidade; // Quantidade de ingressos para essa modalidade
    private List<IngressoRequestDTO> ingressos; // Lista de ingressos com respostas específicas
}


