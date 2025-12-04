package com.suspiciousguys.ticketsystem.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suspiciousguys.ticketsystem.dtos.ItemCompraDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@NoArgsConstructor
@Data
@Entity
@Table(name = "item_compra")
public class
ItemCompraModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private CompraModel compra;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "modalidade_id")
    private ModalidadeModel modalidade;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "ingresso_id", nullable = false)
    private IngressoCompradoModel ingressoComprado;

    public ItemCompraModel(ItemCompraDTO itemCompraDTO) {
        BeanUtils.copyProperties(itemCompraDTO, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCompraModel that = (ItemCompraModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}