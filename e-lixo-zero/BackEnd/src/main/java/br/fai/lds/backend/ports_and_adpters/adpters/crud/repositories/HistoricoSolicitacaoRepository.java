package br.fai.lds.backend.ports_and_adpters.adpters.crud.repositories;

import br.fai.lds.backend.entities.HistoricoSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoSolicitacaoRepository extends JpaRepository<HistoricoSolicitacao, Long> {
    
    List<HistoricoSolicitacao> findBySolicitacaoIdOrderByDataAlteracaoDesc(Long solicitacaoId);
}