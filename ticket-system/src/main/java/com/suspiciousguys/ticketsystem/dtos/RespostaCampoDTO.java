package com.suspiciousguys.ticketsystem.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suspiciousguys.ticketsystem.models.RespostaCampoModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RespostaCampoDTO {
    private Long id;
    private String resposta;
    private Long campoId; // ID do campo associado à resposta
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long ingressoId; // ID do ingresso associado à resposta

    public RespostaCampoDTO(RespostaCampoModel respostaCampoModel) {
        this.id = respostaCampoModel.getId();
        this.resposta = respostaCampoModel.getResposta();

        if (respostaCampoModel.getCampo() != null) {
            this.campoId = respostaCampoModel.getCampo().getId();
        }

        if (respostaCampoModel.getIngressoComprado() != null) {
            this.ingressoId = respostaCampoModel.getIngressoComprado().getId();
        }
    }
}