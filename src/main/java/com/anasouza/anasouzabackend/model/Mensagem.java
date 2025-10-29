package com.anasouza.anasouzabackend.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "mensagens")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Lob // Para texto longo
    @Column(nullable = false, columnDefinition="TEXT")
    private String mensagem;

    private LocalDateTime dataEnvio;

    // Construtor vazio
    public Mensagem() {
        this.dataEnvio = LocalDateTime.now(); // Define a data ao criar
    }

    // Gere os Getters e Setters (Source -> Generate Getters and Setters...)
    // ... (Getters e Setters para id, nome, email, mensagem, dataEnvio)
}