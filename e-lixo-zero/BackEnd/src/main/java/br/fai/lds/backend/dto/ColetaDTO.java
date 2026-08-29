package br.fai.lds.backend.dto;

import java.time.LocalDate;

public class ColetaDTO {
    private Long id;
    private String residuo;
    private Integer quantidade;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private LocalDate data;
    private String periodo;
    private String status;
    
    // Constructors
    public ColetaDTO() {}
    
    public ColetaDTO(String residuo, Integer quantidade, String logradouro, 
                    String numero, String bairro, String cidade, 
                    LocalDate data, String periodo, String status) {
        this.residuo = residuo;
        this.quantidade = quantidade;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.data = data;
        this.periodo = periodo;
        this.status = status;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getResiduo() {
        return residuo;
    }
    
    public void setResiduo(String residuo) {
        this.residuo = residuo;
    }
    
    public Integer getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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
    
    public LocalDate getData() {
        return data;
    }
    
    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public String getPeriodo() {
        return periodo;
    }
    
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}