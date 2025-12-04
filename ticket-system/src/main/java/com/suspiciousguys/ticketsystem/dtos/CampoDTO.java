package com.suspiciousguys.ticketsystem.dtos;

import com.suspiciousguys.ticketsystem.models.CampoModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CampoDTO {
    private Long id;
    private String nomeCampo;
    private String descricaoCampo;
    private Long modalidadeId; // ID da modalidade à qual o campo pertence

    public CampoDTO(CampoModel campoModel) {
        this.id = campoModel.getId();
        this.nomeCampo = campoModel.getNomeCampo();
        this.descricaoCampo = campoModel.getDescricaoCampo();

        if (campoModel.getModalidade() != null) {
            this.modalidadeId = campoModel.getModalidade().getId();
        }
    }
}