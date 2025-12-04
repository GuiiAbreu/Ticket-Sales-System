package com.suspiciousguys.ticketsystem.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suspiciousguys.ticketsystem.dtos.RespostaCampoDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@Entity
@Table(name = "respostaCampo")
public class RespostaCampoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String resposta;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "campo_id", nullable = false)
    private CampoModel campo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ingresso_comprado_id", nullable = false)
    private IngressoCompradoModel ingressoComprado;

    public RespostaCampoModel(RespostaCampoDTO respostaCampoDTO){
        BeanUtils.copyProperties(respostaCampoDTO, this);
    }


}
