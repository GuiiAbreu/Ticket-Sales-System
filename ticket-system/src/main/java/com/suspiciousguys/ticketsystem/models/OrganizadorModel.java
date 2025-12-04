package com.suspiciousguys.ticketsystem.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suspiciousguys.ticketsystem.dtos.OrganizadorDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
@Table(name = "organizador")
public class OrganizadorModel extends UsuarioModel {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "organizadores", fetch = FetchType.LAZY)
    private Set<EventoModel> eventos = new HashSet<>();

    public OrganizadorModel(OrganizadorDTO organizadorDTO) {
        this.setId(organizadorDTO.getId());
        this.setNome(organizadorDTO.getNome());
        this.setLogin(organizadorDTO.getLogin());
        this.setEmail(organizadorDTO.getEmail());
        this.setSenha(organizadorDTO.getSenha());
        this.eventos = (eventos != null) ? eventos : new HashSet<>();
    }
}
