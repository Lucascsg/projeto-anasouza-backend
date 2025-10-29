package com.anasouza.anasouzabackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table; // Para especificar o nome da tabela (opcional)

@Entity
@Table(name = "usuarios") // Define o nome da tabela como 'usuarios' (boa prática)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Nome não pode ser nulo
    private String nome;

    @Column(nullable = false, unique = true) // Email não pode ser nulo e deve ser único
    private String email;

    @Column(nullable = false)
    private String senha; // !! IMPORTANTE: Para este projeto, salvaremos em texto puro. NUNCA faça isso em produção.

    // Construtor vazio (obrigatório para JPA)
    public Usuario() {
    }

    // Getters e Setters (Gere automaticamente no Eclipse)
    // Source -> Generate Getters and Setters... -> Select All -> Generate

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}