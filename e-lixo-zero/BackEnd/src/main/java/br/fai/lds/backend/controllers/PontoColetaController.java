package br.fai.lds.backend.controllers;

import br.fai.lds.backend.dto.PontoColetaDTO;
import br.fai.lds.backend.entities.PontoColeta;
import br.fai.lds.backend.ports_and_adpters.adpters.service.PontoColetaServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pontos-coleta")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PontoColetaController {
    
    @Autowired
    private PontoColetaServiceAdapter pontoColetaServiceAdapter;
    
    @GetMapping
    public ResponseEntity<List<PontoColetaDTO>> listarTodos() {
        List<PontoColetaDTO> pontos = pontoColetaServiceAdapter.listarTodos();
        return ResponseEntity.ok(pontos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PontoColetaDTO> buscarPorId(@PathVariable Long id) {
        return pontoColetaServiceAdapter.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<List<PontoColetaDTO>> buscarPorCidade(@PathVariable String cidade) {
        List<PontoColetaDTO> pontos = pontoColetaServiceAdapter.buscarPorCidade(cidade);
        return ResponseEntity.ok(pontos);
    }
    
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody PontoColeta pontoColeta) {
        try {
            PontoColetaDTO criado = pontoColetaServiceAdapter.criar(pontoColeta);
            return ResponseEntity.status(201).body(criado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody PontoColeta pontoColeta) {
        try {
            PontoColetaDTO atualizado = pontoColetaServiceAdapter.atualizar(id, pontoColeta);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            pontoColetaServiceAdapter.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}