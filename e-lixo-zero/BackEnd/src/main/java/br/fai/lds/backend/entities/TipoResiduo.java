package br.fai.lds.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tipos_residuos")
public class TipoResiduo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_residuo")
    private Long id;
    
    @Column(name = "nome", nullable = false, unique = true, length = 100)
    private String nome;
    
    @Column(name = "categoria", length = 100)
    private String categoria;
    
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;
    
    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;
    
    // Constructors
    public TipoResiduo() {
        this.ativo = true;
    }
    
    public TipoResiduo(String nome, String categoria, String descricao) {
        this();
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
    
    public Boolean getAtivo() {
        return ativo;
    }
    
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}