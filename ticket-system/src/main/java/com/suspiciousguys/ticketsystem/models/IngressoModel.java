package com.suspiciousguys.ticketsystem.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suspiciousguys.ticketsystem.dtos.IngressoDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ingressos")
public class IngressoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable = false)
    private Integer dias;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id", nullable = false)
    private EventoModel evento;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "ingresso", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ModalidadeModel> modalidades = new HashSet<>();

    public IngressoModel(IngressoDTO ingressoDTO) {
        BeanUtils.copyProperties(ingressoDTO, this);
    }

}