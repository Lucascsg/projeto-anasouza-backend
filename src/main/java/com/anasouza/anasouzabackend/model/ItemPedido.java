package com.anasouza.anasouzabackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "itens_pedido") // Nome da tabela
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento Muitos-para-Um: Muitos Itens pertencem a Um Pedido
    @ManyToOne(fetch = FetchType.LAZY) // LAZY aqui é fundamental
    @JoinColumn(name = "pedido_id", nullable = false) // Chave estrangeira para a tabela pedidos
    @JsonBackReference // Lado "filho" da relação JSON com Pedido (evita loop)
    private Pedido pedido;

    // Informações do produto no momento da compra (guardamos cópias)
    private Long produtoId; // Poderia ser um @ManyToOne Produto, mas guardar ID/nome/preço é mais simples e seguro contra mudanças futuras no produto
    private String nomeProduto;
    private String corProduto;
    private String precoUnitario; // Guardamos o preço como string, como estava no carrinho
    private int quantidade;

    // Construtor vazio
    public ItemPedido() {
    }

    // Getters e Setters (Gere automaticamente no Eclipse)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    
    public String getCorProduto() {
        return corProduto;
    }

    public void setCorProduto(String corProduto) {
        this.corProduto = corProduto;
    }

    public String getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(String precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}