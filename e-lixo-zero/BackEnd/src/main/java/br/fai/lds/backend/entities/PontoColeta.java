package br.fai.lds.backend.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pontos_coleta")
public class PontoColeta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ponto")
    private Long id;
    
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;
    
    @Column(name = "logradouro", nullable = false, length = 150)
    private String logradouro;
    
    @Column(name = "numero", length = 20)
    private String numero;
    
    @Column(name = "bairro", length = 100)
    private String bairro;
    
    @Column(name = "cidade", nullable = false, length = 100)
    private String cidade = "Santa Rita do Sapucaí";
    
    @Column(name = "estado", nullable = false, length = 2)
    private String estado = "MG";
    
    @Column(name = "horario_funcionamento", length = 150)
    private String horarioFuncionamento;
    
    @Column(name = "latitude", precision = 10, scale = 8)
    private BigDecimal latitude;
    
    @Column(name = "longitude", precision = 11, scale = 8)
    private BigDecimal longitude;
    
    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;
    
    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;
    
    @ManyToMany
    @JoinTable(
        name = "ponto_coleta_residuo",
        joinColumns = @JoinColumn(name = "id_ponto"),
        inverseJoinColumns = @JoinColumn(name = "id_residuo")
    )
    private Set<TipoResiduo> residuos = new HashSet<>();
    
    // Constructors
    public PontoColeta() {
        this.dataCadastro = LocalDateTime.now();
        this.ativo = true;
    }
    
    public PontoColeta(String nome, String logradouro, String cidade) {
        this();
        this.nome = nome;
        this.logradouro = logradouro;
        this.cidade = cidade;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
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
    
    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }
    
    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }
    
    public BigDecimal getLatitude() {
        return latitude;
    }
    
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
    
    public BigDecimal getLongitude() {
        return longitude;
    }
    
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
    
    public Boolean getAtivo() {
        return ativo;
    }
    
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
    
    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    public Set<TipoResiduo> getResiduos() {
        return residuos;
    }
    
    public void setResiduos(Set<TipoResiduo> residuos) {
        this.residuos = residuos;
    }
    
    public void addResiduo(TipoResiduo residuo) {
        this.residuos.add(residuo);
    }
    
    public void removeResiduo(TipoResiduo residuo) {
        this.residuos.remove(residuo);
    }
}