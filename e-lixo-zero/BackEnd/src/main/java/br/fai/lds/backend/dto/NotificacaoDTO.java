package br.fai.lds.backend.dto;

import java.time.LocalDate;

public class NotificacaoDTO {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDate data;
    
    // Constructors
    public NotificacaoDTO() {}
    
    public NotificacaoDTO(Long id, String titulo, String mensagem, LocalDate data) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.data = data;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    public LocalDate getData() {
        return data;
    }
    
    public void setData(LocalDate data) {
        this.data = data;
    }
}