package br.fai.lds.backend.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_solicitacao")
public class HistoricoSolicitacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitacao", nullable = false)
    private SolicitacaoColeta solicitacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_responsavel")
    private Usuario usuarioResponsavel;
    
    @Column(name = "status_anterior", length = 30)
    private String statusAnterior;
    
    @Column(name = "status_novo", nullable = false, length = 30)
    private String statusNovo;
    
    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;
    
    @Column(name = "data_alteracao", nullable = false, updatable = false)
    private LocalDateTime dataAlteracao;
    
    // Constructors
    public HistoricoSolicitacao() {
        this.dataAlteracao = LocalDateTime.now();
    }
    
    public HistoricoSolicitacao(SolicitacaoColeta solicitacao, String statusNovo) {
        this();
        this.solicitacao = solicitacao;
        this.statusNovo = statusNovo;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public SolicitacaoColeta getSolicitacao() {
        return solicitacao;
    }
    
    public void setSolicitacao(SolicitacaoColeta solicitacao) {
        this.solicitacao = solicitacao;
    }
    
    public Usuario getUsuarioResponsavel() {
        return usuarioResponsavel;
    }
    
    public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }
    
    public String getStatusAnterior() {
        return statusAnterior;
    }
    
    public void setStatusAnterior(String statusAnterior) {
        this.statusAnterior = statusAnterior;
    }
    
    public String getStatusNovo() {
        return statusNovo;
    }
    
    public void setStatusNovo(String statusNovo) {
        this.statusNovo = statusNovo;
    }
    
    public String getObservacao() {
        return observacao;
    }
    
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }
    
    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}