package br.fai.lds.backend.ports_and_adpters.adpters.crud.repositories;

import br.fai.lds.backend.entities.SolicitacaoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitacaoColetaRepository extends JpaRepository<SolicitacaoColeta, Long> {
    
    List<SolicitacaoColeta> findByUsuarioId(Long usuarioId);
    
    List<SolicitacaoColeta> findByColetorId(Long coletorId);
    
    List<SolicitacaoColeta> findByStatus(String status);
    
    List<SolicitacaoColeta> findByUsuarioIdAndStatus(Long usuarioId, String status);
}