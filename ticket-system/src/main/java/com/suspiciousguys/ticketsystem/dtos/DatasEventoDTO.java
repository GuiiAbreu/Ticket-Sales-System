package com.suspiciousguys.ticketsystem.dtos;

import com.suspiciousguys.ticketsystem.models.DataEventoModel;
import com.suspiciousguys.ticketsystem.models.EventoModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class DatasEventoDTO {

    private Long id;
    private LocalDate data;
    private Set<Long> eventosIds;

    DatasEventoDTO(DataEventoModel dataEventoModel){
        BeanUtils.copyProperties(dataEventoModel, this);
        if (!dataEventoModel.getEventos().isEmpty()){
            this.eventosIds = dataEventoModel.getEventos().stream().map(EventoModel::getId).collect(Collectors.toSet());
        } else {
            this.eventosIds = Set.of();
        }

    }
}

