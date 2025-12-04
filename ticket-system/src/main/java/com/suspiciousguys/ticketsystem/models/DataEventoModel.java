package com.suspiciousguys.ticketsystem.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.suspiciousguys.ticketsystem.dtos.DatasEventoDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "datasEvento")
public class DataEventoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "datasEvento", fetch = FetchType.LAZY)
    private Set<EventoModel> eventos = new HashSet<>();

    public DataEventoModel(DatasEventoDTO datasEventoDTO) {
        BeanUtils.copyProperties(datasEventoDTO, this);
    }

    public DataEventoModel(LocalDate date) {
        this.data = date;
    }

}
