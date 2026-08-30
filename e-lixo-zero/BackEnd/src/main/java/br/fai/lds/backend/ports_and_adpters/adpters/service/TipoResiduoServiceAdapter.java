package br.fai.lds.backend.ports_and_adpters.adpters.service;

import br.fai.lds.backend.dto.ResiduoDTO;
import br.fai.lds.backend.entities.TipoResiduo;
import br.fai.lds.backend.ports_and_adpters.adpters.crud.repositories.TipoResiduoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoResiduoServiceAdapter {
    
    @Autowired
    private TipoResiduoRepository tipoResiduoRepository;
    
    public List<ResiduoDTO> listarTodos() {
        return tipoResiduoRepository.findByAtivoTrue().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<ResiduoDTO> buscarPorId(Long id) {
        return tipoResiduoRepository.findById(id)
                .map(this::entityToDTO);
    }
    
    public List<ResiduoDTO> buscarPorCategoria(String categoria) {
        return tipoResiduoRepository.findByCategoria(categoria).stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }
    
    public ResiduoDTO criar(TipoResiduo tipoResiduo) {
        TipoResiduo salvo = tipoResiduoRepository.save(tipoResiduo);
        return entityToDTO(salvo);
    }
    
    public ResiduoDTO atualizar(Long id, TipoResiduo tipoResiduo) {
        TipoResiduo existente = tipoResiduoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de resíduo não encontrado"));
        
        existente.setNome(tipoResiduo.getNome());
        existente.setCategoria(tipoResiduo.getCategoria());
        existente.setDescricao(tipoResiduo.getDescricao());
        existente.setAtivo(tipoResiduo.getAtivo());
        
        TipoResiduo atualizado = tipoResiduoRepository.save(existente);
        return entityToDTO(atualizado);
    }
    
    public void deletar(Long id) {
        if (!tipoResiduoRepository.existsById(id)) {
            throw new RuntimeException("Tipo de resíduo não encontrado");
        }
        tipoResiduoRepository.deleteById(id);
    }
    
    private ResiduoDTO entityToDTO(TipoResiduo tipoResiduo) {
        return new ResiduoDTO(
            tipoResiduo.getId(),
            tipoResiduo.getNome(),
            tipoResiduo.getCategoria(),
            tipoResiduo.getDescricao()
        );
    }
}