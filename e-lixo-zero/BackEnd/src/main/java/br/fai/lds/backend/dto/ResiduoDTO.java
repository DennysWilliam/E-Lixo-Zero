package br.fai.lds.backend.dto;

public class ResiduoDTO {
    private Long id;
    private String nome;
    private String categoria;
    private String descricao;
    
    // Constructors
    public ResiduoDTO() {}
    
    public ResiduoDTO(Long id, String nome, String categoria, String descricao) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
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
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}