package br.fai.lds.backend.ports_and_adpters.adpters.service;

import br.fai.lds.backend.dto.NotificacaoDTO;
import br.fai.lds.backend.entities.Notificacao;
import br.fai.lds.backend.ports_and_adpters.adpters.crud.repositories.NotificacaoRepository;
import br.fai.lds.backend.ports_and_adpters.adpters.crud.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificacaoServiceAdapter {
    
    @Autowired
    private NotificacaoRepository notificacaoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<NotificacaoDTO> listarTodos() {
        return notificacaoRepository.findAll().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }
    
    public List<NotificacaoDTO> listarPorUsuario(Long usuarioId) {
        return notificacaoRepository.findByUsuarioIdOrderByDataEnvioDesc(usuarioId).stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }
    
    public List<NotificacaoDTO> listarNaoLidasPorUsuario(Long usuarioId) {
        return notificacaoRepository.findByUsuarioIdAndLidaFalseOrderByDataEnvioDesc(usuarioId).stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<NotificacaoDTO> buscarPorId(Long id) {
        return notificacaoRepository.findById(id)
                .map(this::entityToDTO);
    }
    
    public NotificacaoDTO criar(Notificacao notificacao) {
        Notificacao salvo = notificacaoRepository.save(notificacao);
        return entityToDTO(salvo);
    }
    
    public NotificacaoDTO marcarComoLida(Long id) {
        Notificacao notificacao = notificacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));
        
        notificacao.setLida(true);
        Notificacao atualizado = notificacaoRepository.save(notificacao);
        return entityToDTO(atualizado);
    }
    
    public void deletar(Long id) {
        if (!notificacaoRepository.existsById(id)) {
            throw new RuntimeException("Notificação não encontrada");
        }
        notificacaoRepository.deleteById(id);
    }
    
    private NotificacaoDTO entityToDTO(Notificacao notificacao) {
        return new NotificacaoDTO(
            notificacao.getId(),
            notificacao.getTitulo(),
            notificacao.getMensagem(),
            notificacao.getDataEnvio().toLocalDate()
        );
    }
}