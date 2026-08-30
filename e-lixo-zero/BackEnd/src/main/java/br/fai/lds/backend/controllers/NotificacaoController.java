package br.fai.lds.backend.controllers;

import br.fai.lds.backend.dto.NotificacaoDTO;
import br.fai.lds.backend.entities.Notificacao;
import br.fai.lds.backend.ports_and_adpters.adpters.service.NotificacaoServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificacoes")
public class NotificacaoController {
    
    @Autowired
    private NotificacaoServiceAdapter notificacaoServiceAdapter;
    
    @GetMapping
    public ResponseEntity<List<NotificacaoDTO>> listarTodos() {
        List<NotificacaoDTO> notificacoes = notificacaoServiceAdapter.listarTodos();
        return ResponseEntity.ok(notificacoes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<NotificacaoDTO> buscarPorId(@PathVariable Long id) {
        return notificacaoServiceAdapter.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<NotificacaoDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<NotificacaoDTO> notificacoes = notificacaoServiceAdapter.listarPorUsuario(usuarioId);
        return ResponseEntity.ok(notificacoes);
    }
    
    @GetMapping("/usuario/{usuarioId}/nao-lidas")
    public ResponseEntity<List<NotificacaoDTO>> listarNaoLidasPorUsuario(@PathVariable Long usuarioId) {
        List<NotificacaoDTO> notificacoes = notificacaoServiceAdapter.listarNaoLidasPorUsuario(usuarioId);
        return ResponseEntity.ok(notificacoes);
    }
    
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Notificacao notificacao) {
        try {
            NotificacaoDTO criado = notificacaoServiceAdapter.criar(notificacao);
            return ResponseEntity.status(201).body(criado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}/marcar-lida")
    public ResponseEntity<?> marcarComoLida(@PathVariable Long id) {
        try {
            NotificacaoDTO atualizado = notificacaoServiceAdapter.marcarComoLida(id);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            notificacaoServiceAdapter.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}