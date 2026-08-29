package br.fai.lds.backend.services;

import br.fai.lds.backend.dto.UsuarioDTO;
import br.fai.lds.backend.entities.Usuario;
import br.fai.lds.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<UsuarioDTO> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::entityToDTO);
    }
    
    public Optional<UsuarioDTO> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(this::entityToDTO);
    }
    
    public UsuarioDTO criar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        // Só verificar CPF se foi fornecido
        if (usuario.getCpf() != null && !usuario.getCpf().isEmpty() && usuarioRepository.existsByCpf(usuario.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }
        // Criptografar senha antes de salvar
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        Usuario salvo = usuarioRepository.save(usuario);
        return entityToDTO(salvo);
    }
    
    public UsuarioDTO atualizar(Long id, Usuario usuario) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        existente.setNomeCompleto(usuario.getNomeCompleto());
        existente.setTelefone(usuario.getTelefone());
        existente.setLogradouro(usuario.getLogradouro());
        existente.setNumero(usuario.getNumero());
        existente.setBairro(usuario.getBairro());
        existente.setCidade(usuario.getCidade());
        existente.setEstado(usuario.getEstado());
        
        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            existente.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
        
        Usuario atualizado = usuarioRepository.save(existente);
        return entityToDTO(atualizado);
    }
    
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }
    
    public boolean autenticar(String email, String senha) {
        System.out.println("Autenticando usuário: " + email);
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isEmpty()) {
            System.out.println("Usuário não encontrado: " + email);
            return false;
        }
        
        Usuario user = usuario.get();
        System.out.println("Usuário encontrado: " + user.getEmail());
        System.out.println("Senha armazenada: " + user.getSenha());
        System.out.println("Senha informada: " + senha);
        
        // Verificar senha usando BCrypt
        boolean match = passwordEncoder.matches(senha, user.getSenha());
        System.out.println("Resultado da comparação de senha: " + match);
        
        return match;
    }
    
    private UsuarioDTO entityToDTO(Usuario usuario) {
        return new UsuarioDTO(
            usuario.getId(),
            usuario.getNomeCompleto(),
            usuario.getEmail(),
            usuario.getTelefone(),
            usuario.getLogradouro(),
            usuario.getNumero(),
            usuario.getBairro(),
            usuario.getCidade(),
            usuario.getEstado(),
            usuario.getTipoUsuario()
        );
    }
}