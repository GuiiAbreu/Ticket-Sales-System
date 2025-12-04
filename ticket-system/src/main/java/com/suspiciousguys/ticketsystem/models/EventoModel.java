package com.suspiciousguys.ticketsystem.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suspiciousguys.ticketsystem.dtos.EventoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "evento")
public class EventoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private LocalTime horario;

    @Column(nullable = false,name = "horario_fim")
    private LocalTime horarioFim;

    @Column(nullable = false, length = 255)
    private String local;

    @Column(nullable = false, name = "capacidade_max")
    private Integer capacidadeMax;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "eventoOrganizador",
            joinColumns = @JoinColumn(name = "organizador_id"),
            inverseJoinColumns = @JoinColumn (name = "evento_id")
    )
    private Set<OrganizadorModel> organizadores = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "eventoDataEvento",
                joinColumns = @JoinColumn(name = "datasEvento_id"),
                inverseJoinColumns = @JoinColumn(name = "evento_id"))
    private Set<DataEventoModel> datasEvento = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<IngressoModel> ingressos = new HashSet<>();


    public EventoModel(EventoDTO eventoDTO) {
        BeanUtils.copyProperties(eventoDTO, this);
    }

    @Override
    public String toString() {
        return "EventoModel{" +
                "capacidadeMax=" + capacidadeMax +
                ", local='" + local + '\'' +
                ", horario=" + horario +
                ", descricao='" + descricao + '\'' +
                ", nome='" + nome + '\'' +
                ", id=" + id +
                '}';
    }



    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, horario, local, capacidadeMax);
    }
}
