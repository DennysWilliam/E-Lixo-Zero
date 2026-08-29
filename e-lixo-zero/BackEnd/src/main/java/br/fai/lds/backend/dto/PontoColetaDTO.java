package br.fai.lds.backend.dto;

import java.math.BigDecimal;
import java.util.List;

public class PontoColetaDTO {
    private Long id;
    private String nome;
    private String endereco;
    private String horario;
    private String telefone;
    private List<String> residuos;
    private BigDecimal latitude;
    private BigDecimal longitude;
    
    // Constructors
    public PontoColetaDTO() {}
    
    public PontoColetaDTO(Long id, String nome, String endereco, String horario, 
                         String telefone, List<String> residuos) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.horario = horario;
        this.telefone = telefone;
        this.residuos = residuos;
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
    
    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getHorario() {
        return horario;
    }
    
    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public List<String> getResiduos() {
        return residuos;
    }
    
    public void setResiduos(List<String> residuos) {
        this.residuos = residuos;
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
}