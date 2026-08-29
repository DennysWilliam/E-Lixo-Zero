package br.fai.lds.backend.repositories;

import br.fai.lds.backend.entities.PontoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PontoColetaRepository extends JpaRepository<PontoColeta, Long> {
    
    List<PontoColeta> findByAtivoTrue();
    
    List<PontoColeta> findByCidade(String cidade);
    
    List<PontoColeta> findByCidadeAndAtivoTrue(String cidade);
}