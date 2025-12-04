package com.suspiciousguys.ticketsystem.dtos;

import com.suspiciousguys.ticketsystem.models.ClienteModel;
import com.suspiciousguys.ticketsystem.models.CompraModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    private String nome;
    private String login;
    private String email;
    private String senha;
    private Set<Long> comprasIds; // Apenas os IDs das compras, evitando expor objetos inteiros

    public ClienteDTO(ClienteModel clienteModel) {
        BeanUtils.copyProperties(clienteModel, this);
        if (clienteModel.getCompras() != null) {
            Set<CompraModel> compras = new HashSet<>(clienteModel.getCompras());
            this.comprasIds = compras.stream()
                    .map(CompraModel::getId)
                    .collect(Collectors.toSet());
        } else {
            this.comprasIds = Set.of(); // Retorna um conjunto vazio caso o cliente não tenha compras
        }
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
