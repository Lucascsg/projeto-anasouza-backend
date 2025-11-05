package com.anasouza.anasouzabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // Importar @Query
import org.springframework.data.repository.query.Param; // Importar @Param
import org.springframework.stereotype.Repository;

import com.anasouza.anasouzabackend.model.Produto;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // ⭐ --- INÍCIO DA CORREÇÃO --- ⭐

    /**
     * Sobrescreve o método findAll() padrão.
     * Esta consulta "força" o JPA a buscar os Produtos E suas Variações
     * em uma única consulta ao banco de dados (usando JOIN FETCH).
     * Isso resolve o N+1 e garante que as variações nunca sejam nulas.
     */
    @Query("SELECT p FROM Produto p LEFT JOIN FETCH p.variacoes")
    @Override
    List<Produto> findAll();

    /**
     * Sobrescreve o método findById() padrão.
     * Esta consulta busca um Produto específico E suas Variações
     * em uma única consulta.
     * @param id O ID do produto a ser buscado.
     * @return um Optional contendo o Produto com suas variações.
     */
    @Query("SELECT p FROM Produto p LEFT JOIN FETCH p.variacoes WHERE p.id = :id")
    Optional<Produto> findById(@Param("id") Long id);
    
    // ⭐ --- FIM DA CORREÇÃO --- ⭐
}