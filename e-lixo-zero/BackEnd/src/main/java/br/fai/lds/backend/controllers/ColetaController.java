package br.fai.lds.backend.controllers;

import br.fai.lds.backend.dto.ColetaDTO;
import br.fai.lds.backend.ports_and_adpters.adpters.service.SolicitacaoColetaServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coletas")
public class ColetaController {
    
    @Autowired
    private SolicitacaoColetaServiceAdapter coletaService;
    
    @GetMapping
    public ResponseEntity<List<ColetaDTO>> listarTodos() {
        List<ColetaDTO> coletas = coletaService.listarTodos();
        return ResponseEntity.ok(coletas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ColetaDTO> buscarPorId(@PathVariable Long id) {
        return coletaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ColetaDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<ColetaDTO> coletas = coletaService.listarPorUsuario(usuarioId);
        return ResponseEntity.ok(coletas);
    }
    
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ColetaDTO coletaDTO) {
        try {
            // Para compatibilidade com o frontend, vamos usar um ID de usuário fixo por enquanto
            // Em produção, isso viria do token de autenticação
            Long usuarioId = 1L; 
            ColetaDTO criado = coletaService.criar(coletaDTO, usuarioId);
            return ResponseEntity.status(201).body(criado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<?> atualizarStatus(@PathVariable Long id, @RequestBody StatusRequest statusRequest) {
        try {
            ColetaDTO atualizado = coletaService.atualizarStatus(id, statusRequest.getStatus());
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            coletaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    static class StatusRequest {
        private String status;
        
        public String getStatus() {
            return status;
        }
        
        public void setStatus(String status) {
            this.status = status;
        }
    }
}