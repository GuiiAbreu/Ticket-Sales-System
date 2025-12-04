package com.suspiciousguys.ticketsystem.dtos;

import com.suspiciousguys.ticketsystem.models.DataEventoModel;
import com.suspiciousguys.ticketsystem.models.EventoModel;
import com.suspiciousguys.ticketsystem.models.IngressoModel;
import com.suspiciousguys.ticketsystem.models.OrganizadorModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class EventoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private LocalTime horario;
    private LocalTime horarioFim;
    private String local;
    private Integer capacidadeMax;
    private Set<Long> organizadoresIds;
    private Set<LocalDate> datasEvento;
    private Set<Long> ingressosIds;
    private LocalDate[] datas;


    public EventoDTO(EventoModel eventoModel) {
        BeanUtils.copyProperties(eventoModel, this);
        if (!eventoModel.getOrganizadores().isEmpty()) {
            this.organizadoresIds = eventoModel.getOrganizadores().stream().map(OrganizadorModel::getId).collect(Collectors.toSet());
        } else {
            this.organizadoresIds = Set.of();
        }

        if (!eventoModel.getDatasEvento().isEmpty()) {
            this.datasEvento = eventoModel.getDatasEvento().stream().map(DataEventoModel::getData).collect(Collectors.toSet());
        } else {
            this.datasEvento = Set.of();
        }

        if (!eventoModel.getIngressos().isEmpty()) {
            this.ingressosIds = eventoModel.getIngressos().stream().map(IngressoModel::getId).collect(Collectors.toSet());
        } else {
            this.ingressosIds = Set.of();
        }
    }

}
