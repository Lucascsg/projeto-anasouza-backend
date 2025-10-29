package com.anasouza.anasouzabackend.controller; // Pacote correto

// Importações do Spring Framework
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin; // Import para CORS
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity; // Para retornar 404 Not Found
import java.util.Optional; // Para lidar com resultados que podem não existir

// Importações de Coleções Java
import java.util.List;

// Importações das nossas classes Model e Repository
import com.anasouza.anasouzabackend.model.Produto;
import com.anasouza.anasouzabackend.repository.ProdutoRepository;

@RestController // Marca a classe como um Controller REST (responde com dados, não HTML)
@RequestMapping("/api/produtos") // Define que todas as rotas aqui começarão com /api/produtos
@CrossOrigin(origins = "*") // Permite requisições de qualquer origem (IMPORTANTE para conectar Front-End e Back-End localmente)
public class ProdutoController {

    // Injeção de Dependência: Pede ao Spring uma instância do ProdutoRepository
    @Autowired 
    private ProdutoRepository produtoRepository;

    // Mapeia requisições GET para /api/produtos
    @GetMapping
    public List<Produto> listarTodosProdutos() {
        // Usa o método findAll() herdado do JpaRepository para buscar todos os produtos
        List<Produto> produtos = produtoRepository.findAll();
        
        // Retorna a lista. Spring Boot converte para JSON automaticamente.
        return produtos;
    }
    
 // Mapeia requisições GET para /api/produtos/{id} (onde {id} é um número)
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable("id") Long id) {
        // Usa o método findById() (do JpaRepository) para buscar um produto pelo ID
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isPresent()) {
            // Se encontrou o produto, retorna 200 OK com o produto no corpo
            return ResponseEntity.ok(produtoOptional.get());
        } else {
            // Se não encontrou, retorna 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    // --- Métodos Futuros ---
    // Exemplo: @GetMapping("/{id}") para buscar um produto por ID
    // Exemplo: @PostMapping para criar um novo produto
    // Exemplo: @PutMapping("/{id}") para atualizar um produto
    // Exemplo: @DeleteMapping("/{id}") para deletar um produto
}