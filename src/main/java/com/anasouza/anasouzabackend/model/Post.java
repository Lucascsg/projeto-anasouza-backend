package com.anasouza.anasouzabackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Lob; // Para campos de texto muito longos (CLOB)

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 500) // Resumo um pouco mais longo
    private String resumo;

    private String imagem; // Caminho/URL da imagem de destaque

    private String data; // Mantendo como String por simplicidade

    @Lob // Indica que este campo pode ser muito longo (mapeia para CLOB no banco)
    @Column(nullable = false, columnDefinition="TEXT") // Garante que não seja nulo e define tipo TEXT
    private String conteudo;

    // Construtor vazio (obrigatório para JPA)
    public Post() {
    }

 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}