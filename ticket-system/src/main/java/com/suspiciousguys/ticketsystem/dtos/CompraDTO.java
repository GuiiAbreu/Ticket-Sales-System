package com.suspiciousguys.ticketsystem.dtos;

import com.suspiciousguys.ticketsystem.models.CompraModel;
import com.suspiciousguys.ticketsystem.models.ItemCompraModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompraDTO {

    private Long id;
    private LocalDateTime dataCompra;
    private Long clienteId;
    private Set<Long> itensIds;

    public CompraDTO(CompraModel compra) {
        this.id = compra.getId();
        this.dataCompra = compra.getDataCompra();
        this.clienteId = compra.getCliente().getId();
        if(compra.getItens() != null) {
            this.itensIds = compra.getItens().stream().map(ItemCompraModel::getId).collect(Collectors.toSet());
        }
        else {
            this.itensIds = Set.of();
        }



    }
}
