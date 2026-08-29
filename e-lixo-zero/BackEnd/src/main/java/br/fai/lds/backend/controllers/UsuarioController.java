package br.fai.lds.backend.controllers;

import br.fai.lds.backend.dto.UsuarioDTO;
import br.fai.lds.backend.entities.Usuario;
import br.fai.lds.backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        List<UsuarioDTO> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioDTO> buscarPorEmail(@PathVariable String email) {
        return usuarioService.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Usuario usuario) {
        try {
            UsuarioDTO criado = usuarioService.criar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(criado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            UsuarioDTO atualizado = usuarioService.atualizar(id, usuario);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            usuarioService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Tentativa de login - Email: " + loginRequest.getEmail());
        System.out.println("Tentativa de login - Senha: " + loginRequest.getSenha());
        
        boolean autenticado = usuarioService.autenticar(loginRequest.getEmail(), loginRequest.getSenha());
        System.out.println("Resultado da autenticação: " + autenticado);
        
        if (autenticado) {
            return usuarioService.buscarPorEmail(loginRequest.getEmail())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }
    
    static class LoginRequest {
        private String email;
        private String senha;
        
        public String getEmail() {
            return email;
        }
        
        public void setEmail(String email) {
            this.email = email;
        }
        
        public String getSenha() {
            return senha;
        }
        
        public void setSenha(String senha) {
            this.senha = senha;
        }
    }
}