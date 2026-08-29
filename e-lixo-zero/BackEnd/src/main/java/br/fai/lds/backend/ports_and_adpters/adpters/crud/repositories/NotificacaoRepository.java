package br.fai.lds.backend.repositories;

import br.fai.lds.backend.entities.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    
    List<Notificacao> findByUsuarioIdOrderByDataEnvioDesc(Long usuarioId);
    
    List<Notificacao> findByUsuarioIdAndLidaFalseOrderByDataEnvioDesc(Long usuarioId);
}