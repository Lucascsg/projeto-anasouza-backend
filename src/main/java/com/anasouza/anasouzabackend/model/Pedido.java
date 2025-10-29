package com.anasouza.anasouzabackend.model;

import java.time.LocalDateTime; // Para registrar a data/hora do pedido
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos") // Nome da tabela
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Data e hora em que o pedido foi criado
    private LocalDateTime dataPedido; 
    
    // Valor total do pedido (calculado)
    private Double valorTotal; 

    // Relacionamento Muitos-para-Um: Muitos Pedidos pertencem a Um Usuario
    @ManyToOne(fetch = FetchType.LAZY) // LAZY é bom aqui, não precisamos sempre carregar o usuário com o pedido
    @JoinColumn(name = "usuario_id", nullable = false) // Chave estrangeira para a tabela usuarios
    private Usuario usuario;

    // Relacionamento Um-para-Muitos: Um Pedido tem Muitos ItensPedido
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER) // EAGER para carregar os itens junto com o pedido
    @JsonManagedReference // Lado "pai" da relação JSON com ItemPedido
    private List<ItemPedido> itens;

    // Construtor vazio
    public Pedido() {
        this.dataPedido = LocalDateTime.now(); // Define a data automaticamente ao criar
    }

    // Getters e Setters (Gere automaticamente no Eclipse)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
}