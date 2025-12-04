package com.suspiciousguys.ticketsystem.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.suspiciousguys.ticketsystem.models.ModalidadeModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@JsonPropertyOrder({
        "id",
        "ingressosDisponiveis",
        "tipoModalidade",
        "preco",
        "campos",
        "ingressosIds"
})
@Data
@NoArgsConstructor
public class ModalidadeDTO {
    private Long id;
    private String TipoModalidade;
    private int ingressosDisponiveis;
    private BigDecimal preco;

    private Long ingresso; // Lista de ingressos associados a esta modalidade

    private Set<CampoDTO> campos; // Lista de campos associados a esta modalidade

    public ModalidadeDTO(ModalidadeModel modalidade) {
        BeanUtils.copyProperties(modalidade, this);

        // Converte os ingressos para IngressoDTO
        if (modalidade.getIngresso() != null) {
            this.ingresso = modalidade.getIngresso().getId();
        } else {
            this.ingresso = null;
        }

        // Converte os campos para CampoDTO
        if (!modalidade.getCampos().isEmpty()) {
            this.campos = modalidade.getCampos().stream()
                    .map(CampoDTO::new)
                    .collect(Collectors.toSet());
        } else {
            this.campos = Set.of();
        }
    }
}