package br.fai.e.lixo.zero.controller;

import br.fai.e.lixo.zero.domain.UsuarioModel;
import br.fai.e.lixo.zero.dto.LoginRequestDto;
import br.fai.e.lixo.zero.dto.LoginResponseDto;
import br.fai.e.lixo.zero.exceptions.UnauthorizedException;
import br.fai.e.lixo.zero.exceptions.ResourceNotFoundException;
import br.fai.e.lixo.zero.ports_and_adapters.port.service.usuario.UsuarioService;
import br.fai.e.lixo.zero.security.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> getAll() {
        return ResponseEntity.ok(usuarioService.findALl());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> getById(@PathVariable final int id) {
        final UsuarioModel usuario = usuarioService.findById(id);
        if (usuario == null) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody final UsuarioModel usuarioModel) {
        final int id = usuarioService.create(usuarioModel);
        if (id == 0) {
            return ResponseEntity.badRequest().build();
        }
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final int id, @RequestBody final UsuarioModel usuarioModel) {
        final boolean updated = usuarioService.update(id, usuarioModel);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final int id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody final LoginRequestDto loginRequest) {
        final UsuarioModel usuario = usuarioService.findByEmail(loginRequest.getEmail());
        if (usuario == null || !usuario.getSenha().equals(loginRequest.getSenha())) {
            throw new UnauthorizedException("E-mail ou senha inválidos");
        }

        final String token = jwtTokenService.generateToken(usuario.getEmail());
        final LoginResponseDto response = toLoginResponse(usuario, token);
        return ResponseEntity.ok(response);
    }

    private LoginResponseDto toLoginResponse(final UsuarioModel usuario, final String token) {
        final LoginResponseDto response = new LoginResponseDto();
        response.setId(usuario.getId());
        response.setNomeCompleto(usuario.getNomeCompleto());
        response.setEmail(usuario.getEmail());
        response.setCpf(usuario.getCpf());
        response.setTelefone(usuario.getTelefone());
        response.setLogradouro(usuario.getLogradouro());
        response.setNumero(usuario.getNumero());
        response.setBairro(usuario.getBairro());
        response.setCidade(usuario.getCidade());
        response.setEstado(usuario.getEstado());
        response.setTipoUsuario(usuario.getTipoUsuario());
        response.setToken(token);
        return response;
    }
}
