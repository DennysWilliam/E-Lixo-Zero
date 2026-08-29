package br.fai.lds.backend.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;
    
    @Column(name = "nome_completo", nullable = false, length = 150)
    private String nomeCompleto;
    
    @Column(name = "cpf", unique = true, length = 14)
    private String cpf;
    
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;
    
    @Column(name = "telefone", length = 20)
    private String telefone;
    
    @Column(name = "logradouro", length = 150)
    private String logradouro;
    
    @Column(name = "numero", length = 20)
    private String numero;
    
    @Column(name = "bairro", length = 100)
    private String bairro;
    
    @Column(name = "cidade", length = 100)
    private String cidade = "Santa Rita do Sapucaí";
    
    @Column(name = "estado", length = 2)
    private String estado = "MG";
    
    @Column(name = "senha", nullable = false)
    private String senha;
    
    @Column(name = "tipo_usuario", nullable = false, length = 20)
    private String tipoUsuario = "CIDADAO";
    
    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;
    
    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;
    
    // Constructors
    public Usuario() {
        this.dataCadastro = LocalDateTime.now();
    }
    
    public Usuario(String nomeCompleto, String cpf, String email, String senha) {
        this();
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNomeCompleto() {
        return nomeCompleto;
    }
    
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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
}