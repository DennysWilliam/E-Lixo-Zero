package br.fai.lds.backend.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificacoes")
public class Notificacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacao")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;
    
    @Column(name = "mensagem", nullable = false, columnDefinition = "TEXT")
    private String mensagem;
    
    @Column(name = "tipo_notificacao", nullable = false, length = 30)
    private String tipoNotificacao = "INFORMATIVA";
    
    @Column(name = "lida", nullable = false)
    private Boolean lida = false;
    
    @Column(name = "data_envio", nullable = false, updatable = false)
    private LocalDateTime dataEnvio;
    
    // Constructors
    public Notificacao() {
        this.dataEnvio = LocalDateTime.now();
        this.tipoNotificacao = "INFORMATIVA";
        this.lida = false;
    }
    
    public Notificacao(Usuario usuario, String titulo, String mensagem) {
        this();
        this.usuario = usuario;
        this.titulo = titulo;
        this.mensagem = mensagem;
    }
    
    public Notificacao(Usuario usuario, String titulo, String mensagem, String tipoNotificacao) {
        this(usuario, titulo, mensagem);
        this.tipoNotificacao = tipoNotificacao;
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
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getMensagem() {
        return mensagem;
    }
    
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public String getTipoNotificacao() {
        return tipoNotificacao;
    }
    
    public void setTipoNotificacao(String tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }
    
    public Boolean getLida() {
        return lida;
    }
    
    public void setLida(Boolean lida) {
        this.lida = lida;
    }
    
    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }
    
    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}