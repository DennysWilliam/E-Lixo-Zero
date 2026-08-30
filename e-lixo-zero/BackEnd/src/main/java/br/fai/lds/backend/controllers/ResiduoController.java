package br.fai.lds.backend.controllers;

import br.fai.lds.backend.dto.ResiduoDTO;
import br.fai.lds.backend.entities.TipoResiduo;
import br.fai.lds.backend.ports_and_adpters.adpters.service.TipoResiduoServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residuos")
public class ResiduoController {
    
    @Autowired
    private TipoResiduoServiceAdapter residuoService;
    
    @GetMapping
    public ResponseEntity<List<ResiduoDTO>> listarTodos() {
        List<ResiduoDTO> residuos = residuoService.listarTodos();
        return ResponseEntity.ok(residuos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResiduoDTO> buscarPorId(@PathVariable Long id) {
        return residuoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ResiduoDTO>> buscarPorCategoria(@PathVariable String categoria) {
        List<ResiduoDTO> residuos = residuoService.buscarPorCategoria(categoria);
        return ResponseEntity.ok(residuos);
    }
    
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody TipoResiduo tipoResiduo) {
        try {
            ResiduoDTO criado = residuoService.criar(tipoResiduo);
            return ResponseEntity.status(201).body(criado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody TipoResiduo tipoResiduo) {
        try {
            ResiduoDTO atualizado = residuoService.atualizar(id, tipoResiduo);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            residuoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}