package com.anasouza.anasouzabackend.repository; // Pacote correto

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anasouza.anasouzabackend.model.Post; // Import da entidade Post

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    
    // Vazio por enquanto. JpaRepository fornece os métodos básicos.
    // findAll(), findById(), save(), deleteById()
}