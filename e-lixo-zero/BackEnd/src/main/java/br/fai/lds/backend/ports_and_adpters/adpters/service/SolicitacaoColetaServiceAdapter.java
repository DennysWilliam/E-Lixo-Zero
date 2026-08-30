package br.fai.lds.backend.ports_and_adpters.adpters.service;

import br.fai.lds.backend.dto.ColetaDTO;
import br.fai.lds.backend.entities.SolicitacaoColeta;
import br.fai.lds.backend.entities.Usuario;
import br.fai.lds.backend.entities.TipoResiduo;
import br.fai.lds.backend.ports_and_adpters.adpters.crud.repositories.SolicitacaoColetaRepository;
import br.fai.lds.backend.ports_and_adpters.adpters.crud.repositories.UsuarioRepository;
import br.fai.lds.backend.ports_and_adpters.adpters.crud.repositories.TipoResiduoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolicitacaoColetaServiceAdapter {
    
    @Autowired
    private SolicitacaoColetaRepository solicitacaoColetaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private TipoResiduoRepository tipoResiduoRepository;
    
    public List<ColetaDTO> listarTodos() {
        return solicitacaoColetaRepository.findAll().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }
    
    public List<ColetaDTO> listarPorUsuario(Long usuarioId) {
        return solicitacaoColetaRepository.findByUsuarioId(usuarioId).stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<ColetaDTO> buscarPorId(Long id) {
        return solicitacaoColetaRepository.findById(id)
                .map(this::entityToDTO);
    }
    
    public ColetaDTO criar(ColetaDTO coletaDTO, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        TipoResiduo tipoResiduo = tipoResiduoRepository.findAll().stream()
                .filter(tr -> tr.getNome().equals(coletaDTO.getResiduo()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Tipo de resíduo não encontrado"));
        
        SolicitacaoColeta solicitacao = new SolicitacaoColeta();
        solicitacao.setUsuario(usuario);
        solicitacao.setTipoResiduo(tipoResiduo);
        solicitacao.setLogradouro(coletaDTO.getLogradouro());
        solicitacao.setNumero(coletaDTO.getNumero());
        solicitacao.setBairro(coletaDTO.getBairro());
        solicitacao.setCidade(coletaDTO.getCidade());
        solicitacao.setQuantidadeEstimada(coletaDTO.getQuantidade().toString());
        solicitacao.setDataDesejada(coletaDTO.getData());
        solicitacao.setStatus(coletaDTO.getStatus());
        
        SolicitacaoColeta salvo = solicitacaoColetaRepository.save(solicitacao);
        return entityToDTO(salvo);
    }
    
    public ColetaDTO atualizarStatus(Long id, String novoStatus) {
        SolicitacaoColeta solicitacao = solicitacaoColetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));
        
        String statusAnterior = solicitacao.getStatus();
        solicitacao.setStatus(novoStatus);
        
        SolicitacaoColeta atualizado = solicitacaoColetaRepository.save(solicitacao);
        return entityToDTO(atualizado);
    }
    
    public void deletar(Long id) {
        if (!solicitacaoColetaRepository.existsById(id)) {
            throw new RuntimeException("Solicitação não encontrada");
        }
        solicitacaoColetaRepository.deleteById(id);
    }
    
    private ColetaDTO entityToDTO(SolicitacaoColeta solicitacao) {
        ColetaDTO dto = new ColetaDTO();
        dto.setId(solicitacao.getId());
        dto.setResiduo(solicitacao.getTipoResiduo().getNome());
        dto.setQuantidade(solicitacao.getQuantidadeEstimada() != null ? 
                         Integer.parseInt(solicitacao.getQuantidadeEstimada()) : 1);
        dto.setLogradouro(solicitacao.getLogradouro());
        dto.setNumero(solicitacao.getNumero());
        dto.setBairro(solicitacao.getBairro());
        dto.setCidade(solicitacao.getCidade());
        dto.setData(solicitacao.getDataDesejada());
        dto.setPeriodo("Manhã"); // padrão, pois não está na entidade
        dto.setStatus(solicitacao.getStatus());
        return dto;
    }
}