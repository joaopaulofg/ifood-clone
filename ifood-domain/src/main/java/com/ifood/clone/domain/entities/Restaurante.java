package com.ifood.clone.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Restaurante {

    @Id
    private UUID id;
    private String nome;
    private String descricao;
    private final LocalDateTime criadoEm;

    public Restaurante() {
        this.id = UUID.randomUUID();
        this.criadoEm = LocalDateTime.now();
    }

    public Restaurante(String nome, String descricao) {
        this();
        this.nome = nome;
        this.descricao = descricao;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
}
