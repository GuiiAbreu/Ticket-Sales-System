package com.suspiciousguys.ticketsystem.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suspiciousguys.ticketsystem.dtos.ModalidadeDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "modalidade")
public class ModalidadeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String TipoModalidade;

    @Column(nullable = false)
    private int ingressosDisponiveis;

    @Column(nullable = false)
    private BigDecimal preco;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingresso_id", nullable = false)
    private IngressoModel ingresso; // Ingresso ao qual a modalidade pertence

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "modalidade", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CampoModel> campos = new HashSet<>();

    public ModalidadeModel(ModalidadeDTO modalidade) {
        BeanUtils.copyProperties(modalidade, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModalidadeModel that = (ModalidadeModel) o;
        return ingressosDisponiveis == that.ingressosDisponiveis && Objects.equals(id, that.id) && Objects.equals(preco, that.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingressosDisponiveis, preco);
    }

    @Override
    public String toString() {
        return "ModalidadeModel{" +
                "preco=" + preco +
                ", id=" + id +
                '}';
    }
}