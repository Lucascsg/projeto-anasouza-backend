package com.anasouza.anasouzabackend.model;

import java.time.LocalDateTime; // Para registrar a data/hora do pedido
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column; // Importar a anotação Column
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
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "usuario_id", nullable = false) // Chave estrangeira para a tabela usuarios
    private Usuario usuario;

    // ⭐ --- NOVOS CAMPOS PARA ENTREGA --- ⭐
    
    @Column(nullable = false)
    private String destinatarioNome; // Nome de quem vai receber

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String endereco; // Ex: "Rua das Flores, 123"

    private String complemento; // Ex: "Apto 10", pode ser nulo

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado; // Ex: "SP"
    
    // ⭐ --- FIM DOS NOVOS CAMPOS --- ⭐

    // Relacionamento Um-para-Muitos: Um Pedido tem Muitos ItensPedido
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference // Lado "pai" da relação JSON com ItemPedido
    private List<ItemPedido> itens;

    // Construtor vazio
    public Pedido() {
        this.dataPedido = LocalDateTime.now(); // Define a data automaticamente ao criar
    }

    // --- Getters e Setters (Antigos) ---

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

    // ⭐ --- NOVOS GETTERS E SETTERS (Para Entrega) --- ⭐

    public String getDestinatarioNome() {
        return destinatarioNome;
    }

    public void setDestinatarioNome(String destinatarioNome) {
        this.destinatarioNome = destinatarioNome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}