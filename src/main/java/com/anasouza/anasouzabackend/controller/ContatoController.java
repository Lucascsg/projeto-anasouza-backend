package com.anasouza.anasouzabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anasouza.anasouzabackend.model.Mensagem;
import com.anasouza.anasouzabackend.repository.MensagemRepository;

import java.util.Map;

@RestController
@RequestMapping("/api/contato")
@CrossOrigin(origins = "*")
public class ContatoController {

    @Autowired
    private MensagemRepository mensagemRepository;

    @PostMapping
    public ResponseEntity<?> receberMensagem(@RequestBody Mensagem mensagem) {
        try {
            // Validação simples
            if (mensagem.getNome() == null || mensagem.getEmail() == null || mensagem.getMensagem() == null ||
                mensagem.getNome().trim().isEmpty() || mensagem.getEmail().trim().isEmpty() || mensagem.getMensagem().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Todos os campos são obrigatórios."));
            }
            
            // Salva a mensagem no banco de dados
            mensagemRepository.save(mensagem);
            
            return ResponseEntity.ok(Map.of("message", "Mensagem recebida com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", "Erro ao salvar a mensagem."));
        }
    }
}