package com.anasouza.anasouzabackend.repository;

import java.util.List; // Para retornar uma lista de pedidos
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anasouza.anasouzabackend.model.Pedido;
import com.anasouza.anasouzabackend.model.Usuario; // Precisamos do Usuario para buscar por ele

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Método mágico do Spring Data JPA para buscar todos os pedidos
    // associados a um determinado usuário, ordenados pela data do pedido
    // em ordem decrescente (mais recentes primeiro).
    List<Pedido> findByUsuarioOrderByDataPedidoDesc(Usuario usuario); 

}