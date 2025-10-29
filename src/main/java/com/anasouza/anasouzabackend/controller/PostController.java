package com.anasouza.anasouzabackend.controller; // Pacote correto

// Importações do Spring Framework
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Importações de Coleções Java e Optional
import java.util.List;
import java.util.Optional;

// Importações das nossas classes Model e Repository
import com.anasouza.anasouzabackend.model.Post;
import com.anasouza.anasouzabackend.repository.PostRepository;

@RestController
@RequestMapping("/api/blog") // Define o caminho base para as rotas do blog
@CrossOrigin(origins = "*") // Permite acesso do Front-End
public class PostController {

    @Autowired // Injeta o PostRepository
    private PostRepository postRepository;

    // Método para listar todos os posts (resumos) - GET /api/blog
    @GetMapping
    public List<Post> listarTodosPosts() {
        // Busca todos os posts. Para otimizar, poderíamos criar um DTO
        // que retorne apenas id, titulo, resumo, imagem, data,
        // mas por simplicidade, retornaremos o objeto completo por enquanto.
        return postRepository.findAll(); 
    }

    // Método para buscar um post completo por ID - GET /api/blog/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            return ResponseEntity.ok(postOptional.get()); // Retorna 200 OK com o post
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        }
    }
}