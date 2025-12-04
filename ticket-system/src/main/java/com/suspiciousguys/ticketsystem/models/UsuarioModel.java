package com.suspiciousguys.ticketsystem.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, length = 100, unique = true)
    private String login;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String senha;



    @Override
    public String toString() {
        return "UsuarioModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
