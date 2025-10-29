package com.anasouza.anasouzabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anasouza.anasouzabackend.model.ItemPedido;
import com.anasouza.anasouzabackend.model.Pedido;
import com.anasouza.anasouzabackend.model.Usuario; // Precisaremos do Usuário
import com.anasouza.anasouzabackend.repository.PedidoRepository;
import com.anasouza.anasouzabackend.repository.UsuarioRepository; // Para buscar o usuário

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// DTO (Data Transfer Object) simples para receber os dados do carrinho do Front-End
// Coloque esta classe dentro do arquivo PedidoController.java por simplicidade,
// ou crie um pacote 'dto' e um arquivo 'CarrinhoItemDTO.java' separado.
class CarrinhoItemDTO {
    public Long id; // ID do produto original
    public String nome;
    public String preco;
    public String variacaoId;
    public String cor;
    public String imagem;
    public int quantidade;
}


@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; // Para associar o pedido a um usuário

    // --- Endpoint para Criar um Novo Pedido (Checkout) ---
    @PostMapping
    public ResponseEntity<?> criarPedido(@RequestBody List<CarrinhoItemDTO> itensCarrinho) {
        
        // --- SIMULAÇÃO: Pegar o Usuário Logado ---
        // Em um sistema real com Spring Security, pegaríamos o usuário autenticado.
        // Por agora, vamos assumir que o usuário com ID 1 está fazendo o pedido.
        // Você PODE receber o email do usuário do front-end (do sessionStorage) e buscar aqui.
        Long usuarioId = 1L; // Exemplo Fixo! Mude se quiser testar com outro ID
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId); 
        if (!usuarioOptional.isPresent()) {
             // Cadastre um usuário com ID 1 manualmente no data.sql ou via API /registrar para testar
             // INSERT INTO USUARIOS (nome, email, senha) VALUES ('Usuário Teste', 'teste@teste.com', '123');
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Usuário de teste com ID 1 não encontrado. Cadastre-o primeiro."));
        }
        Usuario usuarioLogado = usuarioOptional.get();
        // --- FIM DA SIMULAÇÃO ---

        if (itensCarrinho == null || itensCarrinho.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "O carrinho não pode estar vazio."));
        }

        try {
            Pedido novoPedido = new Pedido();
            novoPedido.setUsuario(usuarioLogado); // Associa o pedido ao usuário
            novoPedido.setDataPedido(LocalDateTime.now()); // Data já é definida no construtor, mas podemos garantir aqui
            
            List<ItemPedido> itensDoPedido = new ArrayList<>();
            double valorTotalCalculado = 0.0;

            // Transforma os DTOs do carrinho em Entidades ItemPedido
            for (CarrinhoItemDTO itemDTO : itensCarrinho) {
                ItemPedido item = new ItemPedido();
                item.setPedido(novoPedido); // Associa o item ao novo pedido
                item.setProdutoId(itemDTO.id);
                item.setNomeProduto(itemDTO.nome);
                item.setCorProduto(itemDTO.cor);
                item.setPrecoUnitario(itemDTO.preco); // Guarda o preço como string
                item.setQuantidade(itemDTO.quantidade);
                
                // Calcula o subtotal deste item
                double precoUnit = parsePrecoParaNumero(itemDTO.preco);
                if (!isNaN(precoUnit)) {
                    valorTotalCalculado += precoUnit * itemDTO.quantidade;
                }
                
                itensDoPedido.add(item);
            }

            novoPedido.setItens(itensDoPedido); // Adiciona a lista de itens ao pedido
            novoPedido.setValorTotal(valorTotalCalculado); // Define o valor total calculado

            // Salva o Pedido (e os Itens, devido ao CascadeType.ALL)
            Pedido pedidoSalvo = pedidoRepository.save(novoPedido);

            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo); // Retorna o pedido criado

        } catch (Exception e) {
            // Logar o erro real no servidor é importante
            System.err.println("Erro ao criar pedido: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Erro interno ao processar o pedido."));
        }
    }
    
    // --- Endpoint para Listar Pedidos do Usuário (Histórico) ---
    @GetMapping("/meus-pedidos")
    public ResponseEntity<?> listarMeusPedidos() {
        // --- SIMULAÇÃO: Pegar o Usuário Logado ---
        Long usuarioId = 1L; // Mesma simulação de ID fixo
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId); 
        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Usuário não encontrado para buscar histórico."));
        }
        Usuario usuarioLogado = usuarioOptional.get();
        // --- FIM DA SIMULAÇÃO ---

        try {
            // Usa o método customizado do repositório para buscar os pedidos do usuário
            List<Pedido> pedidos = pedidoRepository.findByUsuarioOrderByDataPedidoDesc(usuarioLogado);
            return ResponseEntity.ok(pedidos); // Retorna a lista de pedidos (com itens, devido ao EAGER fetch)

        } catch (Exception e) {
             System.err.println("Erro ao buscar pedidos: " + e.getMessage());
             e.printStackTrace();
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Erro interno ao buscar o histórico de pedidos."));
        }
    }

    // --- Funções Auxiliares ---
    private double parsePrecoParaNumero(String precoStr) {
        if (precoStr == null) return 0.0;
        try {
            return Double.parseDouble(precoStr.replace("R$ ", "").replace(".", "").replace(",", "."));
        } catch (NumberFormatException e) {
            System.err.println("Erro ao parsear preço: " + precoStr + " - " + e.getMessage());
            return 0.0; // Retorna 0 se não conseguir converter
        }
    }
    private boolean isNaN(double num) {
        return Double.isNaN(num);
    }

}