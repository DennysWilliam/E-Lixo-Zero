package br.fai.lds.e_lixo_zero.domain;

public class NotificacaoModel {

    private int id;
    private int usuarioId;
    private String titulo;
    private String mensagem;
    private String tipoNotificacao;
    private boolean lida;
    private String dataEnvio;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(final int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(final String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTipoNotificacao() {
        return tipoNotificacao;
    }

    public void setTipoNotificacao(final String tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(final boolean lida) {
        this.lida = lida;
    }

    public String getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(final String dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}
