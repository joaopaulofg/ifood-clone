package com.ifood.clone.domain.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Produto {

    @Id
    private UUID id;

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String imagemUrl;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Restaurante restaurante;

    private LocalDateTime criadoEm;

    public Produto() {
        this.id = UUID.randomUUID();
        this.criadoEm = LocalDateTime.now();
    }

    public Produto(String nome, String descricao, BigDecimal preco, String imagemUrl,
                   Categoria categoria, Restaurante restaurante) {
        this();
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imagemUrl = imagemUrl;
        this.categoria = categoria;
        this.restaurante = restaurante;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public Restaurante getRestaurante() { return restaurante; }
    public void setRestaurante(Restaurante restaurante) { this.restaurante = restaurante; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
}

