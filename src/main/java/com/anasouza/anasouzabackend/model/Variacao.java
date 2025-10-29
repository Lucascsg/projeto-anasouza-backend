package com.anasouza.anasouzabackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonBackReference; // Importante para evitar loop no JSON

@Entity
public class Variacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cor;
    private String imagem; // Caminho/URL da imagem para esta cor

    // Relacionamento Muitos-para-Um: Muitas variações pertencem a Um Produto
    @ManyToOne 
    @JoinColumn(name = "produto_id") // Nome da coluna da chave estrangeira no banco
    @JsonBackReference // Evita que o Produto seja incluído no JSON da Variacao (evita loop infinito)
    private Produto produto;

    // Construtor vazio
    public Variacao() {
    }

    // Getters e Setters (Gere automaticamente no Eclipse)
    // Source -> Generate Getters and Setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}