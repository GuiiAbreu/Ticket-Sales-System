package com.suspiciousguys.ticketsystem.dtos;

import com.suspiciousguys.ticketsystem.models.IngressoModel;
import com.suspiciousguys.ticketsystem.models.ModalidadeModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class IngressoDTO {
    private Long id;
    private Integer dias;
    private String nomeEvento; // Apenas o nome do evento
    private Set<Long> modalidadeId; // ID da modalidade à qual o ingresso pertence

    public IngressoDTO(IngressoModel ingresso) {
        BeanUtils.copyProperties(ingresso, this);

        // Define apenas o nome do evento
        if (ingresso.getEvento() != null) {
            this.nomeEvento = ingresso.getEvento().getNome();
        }

        // Define o ID da modalidade
        if (ingresso.getModalidades() != null) {

            this.modalidadeId = ingresso.getModalidades().stream().map(ModalidadeModel::getId).collect(Collectors.toSet());
        }

    }
}