package com.anasouza.anasouzabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anasouza.anasouzabackend.model.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    
    // Geralmente não precisamos de métodos customizados aqui para o básico,
    // JpaRepository já oferece o suficiente (save, findById, etc.)
}