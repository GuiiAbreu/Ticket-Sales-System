package com.suspiciousguys.ticketsystem.dtos;

import com.suspiciousguys.ticketsystem.models.EventoModel;
import com.suspiciousguys.ticketsystem.models.OrganizadorModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrganizadorDTO {
    private Long id;
    private String nome;
    private String login;
    private String email;
    private String senha;
    private Set<Long> eventosIds;

    public OrganizadorDTO(OrganizadorModel organizadorModel) {
        this.id = organizadorModel.getId();
        this.nome = organizadorModel.getNome();
        this.login = organizadorModel.getLogin();
        this.email = organizadorModel.getEmail();
        this.senha = organizadorModel.getSenha();
        if (organizadorModel.getEventos() != null) {
            this.eventosIds= organizadorModel.getEventos().stream().map(EventoModel::getId).collect(Collectors.toSet());
        }
    }

}
