package com.anasouza.anasouzabackend.repository; // Pacote correto

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anasouza.anasouzabackend.model.Produto; // Import da classe Produto correta

@Repository // Indica ao Spring que esta é uma interface de repositório
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    // NENHUM código aqui dentro é necessário por enquanto!
    // A mágica do JpaRepository<Produto, Long> nos dá automaticamente:
    // - findAll(): Retorna todos os produtos.
    // - findById(Long id): Retorna um produto pelo seu ID.
    // - save(Produto produto): Salva um novo produto ou atualiza um existente.
    // - deleteById(Long id): Deleta um produto pelo ID.
    // - E muitos outros!
    
    // (Futuramente, se precisarmos de buscas mais específicas, como "buscar por categoria",
    // poderíamos declarar métodos aqui, ex: List<Produto> findByCategoria(String categoria);)
}