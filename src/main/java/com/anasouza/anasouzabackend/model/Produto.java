package com.anasouza.anasouzabackend.model; 

// Importações necessárias
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import java.util.List;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType; // Import necessário
import com.fasterxml.jackson.annotation.JsonManagedReference; 

@Entity 
public class Produto {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable = false) 
    private String nome;

    private String preco; 

    @Column(length = 1000) 
    private String descricao;

    private String imagem; 
    private String categoria;
    
    // Relacionamento Um-para-Muitos com Variações
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER) // EAGER adicionado
    @JsonManagedReference 
    private List<Variacao> variacoes;

    // Construtor vazio
    public Produto() {
    }

    // Construtor com campos
    public Produto(String nome, String preco, String descricao, String imagem, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.imagem = imagem;
        this.categoria = categoria;
    }

    // --- Getters e Setters --- 
    // (Incluindo os novos para 'variacoes')

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // GETTER E SETTER PARA VARIACOES (ADICIONADOS)
    public List<Variacao> getVariacoes() {
        return variacoes;
    }

    public void setVariacoes(List<Variacao> variacoes) {
        this.variacoes = variacoes;
    }
}