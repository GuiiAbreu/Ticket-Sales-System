package com.suspiciousguys.ticketsystem.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suspiciousguys.ticketsystem.dtos.CampoDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "campo")
public class CampoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nomeCampo;

    @Column(nullable = false, length = 255)
    private String descricaoCampo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY,  optional = false)
    @JoinColumn(name = "modalidade_id", nullable = false)
    private ModalidadeModel modalidade;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "campo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RespostaCampoModel> respostas = new HashSet<>();


    public CampoModel(CampoDTO campo) {
        BeanUtils.copyProperties(campo, this);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampoModel that = (CampoModel) o;
        return Objects.equals(id, that.id) && Objects.equals(nomeCampo, that.nomeCampo) && Objects.equals(descricaoCampo, that.descricaoCampo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeCampo, descricaoCampo);
    }

    @Override
    public String toString() {
        return "CampoModel{" +
                "descricaoCampo='" + descricaoCampo + '\'' +
                ", id=" + id +
                ", nomeCampo='" + nomeCampo + '\'' +
                '}';
    }
}
