package com.suspiciousguys.ticketsystem.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suspiciousguys.ticketsystem.dtos.ClienteDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@NoArgsConstructor
@Table(name = "cliente")
public class ClienteModel extends UsuarioModel {

    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<CompraModel> compras = new HashSet<>();


    public ClienteModel(ClienteDTO clienteDTO) {
        this.setId(clienteDTO.getId());
        this.setNome(clienteDTO.getNome());
        this.setLogin(clienteDTO.getLogin());
        this.setEmail(clienteDTO.getEmail());
        this.setSenha(clienteDTO.getSenha());
        this.compras = (compras!=null)  ? compras: new HashSet<>();
    }


}
