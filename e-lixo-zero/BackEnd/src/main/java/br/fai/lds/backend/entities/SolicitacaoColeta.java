package br.fai.lds.backend.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitacoes_coleta")
public class SolicitacaoColeta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitacao")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_residuo", nullable = false)
    private TipoResiduo tipoResiduo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_coletor")
    private Usuario coletor;
    
    @Column(name = "logradouro", nullable = false, length = 150)
    private String logradouro;
    
    @Column(name = "numero", length = 20)
    private String numero;
    
    @Column(name = "bairro", nullable = false, length = 100)
    private String bairro;
    
    @Column(name = "cidade", nullable = false, length = 100)
    private String cidade = "Santa Rita do Sapucaí";
    
    @Column(name = "estado", nullable = false, length = 2)
    private String estado = "MG";
    
    @Column(name = "quantidade_estimada", length = 100)
    private String quantidadeEstimada;
    
    @Column(name = "data_desejada")
    private LocalDate dataDesejada;
    
    @Column(name = "status", nullable = false, length = 30)
    private String status = "PENDENTE";
    
    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;
    
    @Column(name = "data_solicitacao", nullable = false, updatable = false)
    private LocalDateTime dataSolicitacao;
    
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    
    // Constructors
    public SolicitacaoColeta() {
        this.dataSolicitacao = LocalDateTime.now();
        this.status = "PENDENTE";
        this.cidade = "Santa Rita do Sapucaí";
        this.estado = "MG";
    }
    
    public SolicitacaoColeta(Usuario usuario, TipoResiduo tipoResiduo, String logradouro, String bairro) {
        this();
        this.usuario = usuario;
        this.tipoResiduo = tipoResiduo;
        this.logradouro = logradouro;
        this.bairro = bairro;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public TipoResiduo getTipoResiduo() {
        return tipoResiduo;
    }
    
    public void setTipoResiduo(TipoResiduo tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }
    
    public Usuario getColetor() {
        return coletor;
    }
    
    public void setColetor(Usuario coletor) {
        this.coletor = coletor;
    }
    
    public String getLogradouro() {
        return logradouro;
    }
    
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    
    public String getNumero() {
        return numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String getBairro() {
        return bairro;
    }
    
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    public String getCidade() {
        return cidade;
    }
    
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getQuantidadeEstimada() {
        return quantidadeEstimada;
    }
    
    public void setQuantidadeEstimada(String quantidadeEstimada) {
        this.quantidadeEstimada = quantidadeEstimada;
    }
    
    public LocalDate getDataDesejada() {
        return dataDesejada;
    }
    
    public void setDataDesejada(LocalDate dataDesejada) {
        this.dataDesejada = dataDesejada;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getObservacoes() {
        return observacoes;
    }
    
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }
    
    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }
    
    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }
    
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
    
    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}