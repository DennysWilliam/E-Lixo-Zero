package br.fai.lds.backend.ports_and_adpters.adpters.service;

import br.fai.lds.backend.dto.PontoColetaDTO;
import br.fai.lds.backend.entities.PontoColeta;
import br.fai.lds.backend.ports_and_adpters.adpters.crud.repositories.PontoColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PontoColetaService {
    
    @Autowired
    private PontoColetaRepository pontoColetaRepository;
    
    public List<PontoColetaDTO> listarTodos() {
        return pontoColetaRepository.findByAtivoTrue().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<PontoColetaDTO> buscarPorId(Long id) {
        return pontoColetaRepository.findById(id)
                .map(this::entityToDTO);
    }
    
    public List<PontoColetaDTO> buscarPorCidade(String cidade) {
        return pontoColetaRepository.findByCidadeAndAtivoTrue(cidade).stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }
    
    public PontoColetaDTO criar(PontoColeta pontoColeta) {
        PontoColeta salvo = pontoColetaRepository.save(pontoColeta);
        return entityToDTO(salvo);
    }
    
    public PontoColetaDTO atualizar(Long id, PontoColeta pontoColeta) {
        PontoColeta existente = pontoColetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ponto de coleta não encontrado"));
        
        existente.setNome(pontoColeta.getNome());
        existente.setLogradouro(pontoColeta.getLogradouro());
        existente.setNumero(pontoColeta.getNumero());
        existente.setBairro(pontoColeta.getBairro());
        existente.setCidade(pontoColeta.getCidade());
        existente.setEstado(pontoColeta.getEstado());
        existente.setHorarioFuncionamento(pontoColeta.getHorarioFuncionamento());
        existente.setLatitude(pontoColeta.getLatitude());
        existente.setLongitude(pontoColeta.getLongitude());
        existente.setAtivo(pontoColeta.getAtivo());
        
        PontoColeta atualizado = pontoColetaRepository.save(existente);
        return entityToDTO(atualizado);
    }
    
    public void deletar(Long id) {
        if (!pontoColetaRepository.existsById(id)) {
            throw new RuntimeException("Ponto de coleta não encontrado");
        }
        pontoColetaRepository.deleteById(id);
    }
    
    private PontoColetaDTO entityToDTO(PontoColeta pontoColeta) {
        List<String> residuos = pontoColeta.getResiduos().stream()
                .map(residuo -> residuo.getNome())
                .collect(Collectors.toList());
        
        String endereco = pontoColeta.getLogradouro();
        if (pontoColeta.getNumero() != null && !pontoColeta.getNumero().isEmpty()) {
            endereco += ", " + pontoColeta.getNumero();
        }
        if (pontoColeta.getBairro() != null && !pontoColeta.getBairro().isEmpty()) {
            endereco += " - " + pontoColeta.getBairro();
        }
        endereco += " - " + pontoColeta.getCidade();
        
        return new PontoColetaDTO(
            pontoColeta.getId(),
            pontoColeta.getNome(),
            endereco,
            pontoColeta.getHorarioFuncionamento(),
            null, // telefone não está na entidade
            residuos
        );
    }
}