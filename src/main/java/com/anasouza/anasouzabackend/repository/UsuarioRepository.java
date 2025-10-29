package com.anasouza.anasouzabackend.repository; // Pacote correto

import java.util.Optional; // Para lidar com resultados que podem não existir

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anasouza.anasouzabackend.model.Usuario; // Import da entidade Usuario

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método customizado para buscar um usuário pelo email.
    // O Spring Data JPA implementa este método automaticamente baseado no nome!
    // Ele busca na entidade Usuario um campo chamado 'email' e compara com o parâmetro.
    Optional<Usuario> findByEmail(String email); 

}