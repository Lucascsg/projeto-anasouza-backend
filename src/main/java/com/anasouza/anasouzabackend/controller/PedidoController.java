package com.anasouza.anasouzabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anasouza.anasouzabackend.model.ItemPedido;
import com.anasouza.anasouzabackend.model.Pedido;
import com.anasouza.anasouzabackend.model.Usuario; 
import com.anasouza.anasouzabackend.repository.PedidoRepository;
import com.anasouza.anasouzabackend.repository.UsuarioRepository; 

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// DTO 'CarrinhoItemDTO' (sem mudanças, está perfeito)
class CarrinhoItemDTO {
    private Long id; 
    private String nome;
    private String preco;
    private String variacaoId;
    private String cor;
    private String imagem;
    private int quantidade;
    private boolean personalizado;
    private String frase;
    private String fraseCor;
    private String fraseFonte;

    // Getters e Setters (todos perfeitos)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getPreco() { return preco; }
    public void setPreco(String preco) { this.preco = preco; }
    public String getVariacaoId() { return variacaoId; }
    public void setVariacaoId(String variacaoId) { this.variacaoId = variacaoId; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public boolean isPersonalizado() { return personalizado; }
    public void setPersonalizado(boolean personalizado) { this.personalizado = personalizado; }
    public String getFrase() { return frase; }
    public void setFrase(String frase) { this.frase = frase; }
    public String getFraseCor() { return fraseCor; }
    public void setFraseCor(String fraseCor) { this.fraseCor = fraseCor; }
    public String getFraseFonte() { return fraseFonte; }
    public void setFraseFonte(String fraseFonte) { this.fraseFonte = fraseFonte; }
}


// ⭐ --- DTO 'CriarPedidoRequest' ATUALIZADO --- ⭐
// (Agora inclui os dados de entrega)
class CriarPedidoRequest {
    private List<CarrinhoItemDTO> itensCarrinho;
    private String emailUsuario; 
    
    // Novos campos de entrega
    private String destinatarioNome;
    private String cpf;
    private String cep;
    private String endereco;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    // Getters e Setters para campos antigos
    public List<CarrinhoItemDTO> getItensCarrinho() {
        return itensCarrinho;
    }
    public void setItensCarrinho(List<CarrinhoItemDTO> itensCarrinho) {
        this.itensCarrinho = itensCarrinho;
    }
    public String getEmailUsuario() {
        return emailUsuario;
    }
    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    // Getters e Setters para os NOVOS campos de entrega
    public String getDestinatarioNome() { return destinatarioNome; }
    public void setDestinatarioNome(String destinatarioNome) { this.destinatarioNome = destinatarioNome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
// ⭐ --- FIM DO DTO ATUALIZADO --- ⭐


@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; 

    // --- Endpoint para Criar um Novo Pedido (Checkout) ---
    @PostMapping
    public ResponseEntity<?> criarPedido(@RequestBody CriarPedidoRequest request) {
        
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(request.getEmailUsuario()); 
        
        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Usuário " + request.getEmailUsuario() + " não encontrado."));
        }
        Usuario usuarioLogado = usuarioOptional.get();

        if (request.getItensCarrinho() == null || request.getItensCarrinho().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "O carrinho não pode estar vazio."));
        }

        try {
            Pedido novoPedido = new Pedido();
            novoPedido.setUsuario(usuarioLogado); 
            novoPedido.setDataPedido(LocalDateTime.now());
            
            // ⭐ --- NOVAS LINHAS: Copiando os dados de ENTREGA do Request para a Entidade --- ⭐
            novoPedido.setDestinatarioNome(request.getDestinatarioNome());
            novoPedido.setCpf(request.getCpf());
            novoPedido.setCep(request.getCep());
            novoPedido.setEndereco(request.getEndereco());
            novoPedido.setComplemento(request.getComplemento()); // Pode ser nulo
            novoPedido.setBairro(request.getBairro());
            novoPedido.setCidade(request.getCidade());
            novoPedido.setEstado(request.getEstado());
            // ⭐ --- FIM DAS NOVAS LINHAS --- ⭐

            List<ItemPedido> itensDoPedido = new ArrayList<>();
            double valorTotalCalculado = 0.0;

            for (CarrinhoItemDTO itemDTO : request.getItensCarrinho()) {
                ItemPedido item = new ItemPedido(); // A Entidade
                item.setPedido(novoPedido); 
                
                // Copiando dados básicos (sem mudança)
                item.setProdutoId(itemDTO.getId());
                item.setNomeProduto(itemDTO.getNome());
                item.setCorProduto(itemDTO.getCor());
                item.setPrecoUnitario(itemDTO.getPreco()); 
                item.setQuantidade(itemDTO.getQuantidade());
                
                // Copiando dados de personalização (sem mudança)
                item.setPersonalizado(itemDTO.isPersonalizado());
                item.setFrase(itemDTO.getFrase());
                item.setFraseCor(itemDTO.getFraseCor());
                item.setFraseFonte(itemDTO.getFraseFonte());
                
                // Cálculo de total (sem mudança)
                double precoUnit = parsePrecoParaNumero(itemDTO.getPreco());
                if (!isNaN(precoUnit)) {
                    valorTotalCalculado += precoUnit * itemDTO.getQuantidade();
                }
                
                itensDoPedido.add(item);
            }

            novoPedido.setItens(itensDoPedido); 
            novoPedido.setValorTotal(valorTotalCalculado); 

            Pedido pedidoSalvo = pedidoRepository.save(novoPedido);

            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo); 

        } catch (Exception e) {
            System.err.println("Erro ao criar pedido: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Erro interno ao processar o pedido."));
        }
    }
    
    // --- Endpoint para Listar Pedidos do Usuário (Histórico) ---
    // (Sem mudanças, ele automaticamente retornará os novos campos do Pedido)
    @GetMapping("/meus-pedidos")
    public ResponseEntity<?> listarMeusPedidos(@RequestParam("email") String email) {
        
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email); 
        
        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Usuário não encontrado para buscar histórico."));
        }
        Usuario usuarioLogado = usuarioOptional.get();

        try {
            List<Pedido> pedidos = pedidoRepository.findByUsuarioOrderByDataPedidoDesc(usuarioLogado);
            return ResponseEntity.ok(pedidos);

        } catch (Exception e) {
            System.err.println("Erro ao buscar pedidos: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Erro interno ao buscar o histórico de pedidos."));
        }
    }

    // --- Funções Auxiliares (Sem Mudanças) ---
    private double parsePrecoParaNumero(String precoStr) {
        if (precoStr == null) return 0.0;
        try {
            String numeroLimpo = precoStr.replaceAll("[^\\d,]", ""); 
            numeroLimpo = numeroLimpo.replaceAll(",", "."); 
            return Double.parseDouble(numeroLimpo);
        } catch (NumberFormatException e) {
            System.err.println("!!! ERRO CRÍTICO AO PARSEAR PREÇO NO BACK-END: '" + precoStr + "'");
            return 0.0; 
        }
    }

    private boolean isNaN(double num) {
        return Double.isNaN(num);
    }
}