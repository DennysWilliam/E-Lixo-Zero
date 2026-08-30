package br.fai.lds.backend.ports_and_adpters.adpters.crud.repositories;

import br.fai.lds.backend.entities.TipoResiduo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoResiduoRepository extends JpaRepository<TipoResiduo, Long> {
    
    List<TipoResiduo> findByAtivoTrue();
    
    List<TipoResiduo> findByCategoria(String categoria);
    
    Optional<TipoResiduo> findByNome(String nome);
}