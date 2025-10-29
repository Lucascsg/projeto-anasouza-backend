package com.anasouza.anasouzabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anasouza.anasouzabackend.model.Usuario;
import com.anasouza.anasouzabackend.repository.UsuarioRepository;

import java.util.Map; // Para retornar mensagens simples
import java.util.Optional;

@RestController
@RequestMapping("/api/auth") // Caminho base para autenticação
@CrossOrigin(origins = "*") // Permite acesso do Front-End
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // --- Endpoint de Registro ---
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario novoUsuario) {
        // Validação básica (poderia ser mais robusta com DTOs e validações)
        if (novoUsuario.getNome() == null || novoUsuario.getEmail() == null || novoUsuario.getSenha() == null ||
            novoUsuario.getNome().isEmpty() || novoUsuario.getEmail().isEmpty() || novoUsuario.getSenha().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Todos os campos são obrigatórios."));
        }

        // Verifica se o email já existe
        if (usuarioRepository.findByEmail(novoUsuario.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "Este e-mail já está cadastrado."));
        }

        // Salva o novo usuário no banco de dados
        // IMPORTANTE: A senha está sendo salva em texto puro!
        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);

        // Retorna uma resposta de sucesso (201 Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Usuário cadastrado com sucesso!", "userId", usuarioSalvo.getId()));
    }

    // --- Endpoint de Login ---
    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody Map<String, String> credenciais) {
        String email = credenciais.get("email");
        String senha = credenciais.get("senha");

        if (email == null || senha == null || email.isEmpty() || senha.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "E-mail e senha são obrigatórios."));
        }

        // Busca o usuário pelo email
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            // Compara a senha enviada com a senha salva (texto puro!)
            if (senha.equals(usuario.getSenha())) {
                // Login bem-sucedido!
                // Retorna os dados do usuário (sem a senha)
                Usuario usuarioLogado = new Usuario(); // Cria um novo objeto para não expor a senha
                usuarioLogado.setId(usuario.getId());
                usuarioLogado.setNome(usuario.getNome());
                usuarioLogado.setEmail(usuario.getEmail());
                
                return ResponseEntity.ok(usuarioLogado);
            }
        }

        // Se o usuário não foi encontrado ou a senha está incorreta
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "E-mail ou senha inválidos."));
    }
}