package br.fai.lds.e_lixo_zero.domain;

public class SolicitacaoColetaModel {

    private int id;
    private int usuarioId;
    private int tipoResiduoId;
    private int coletorId;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String quantidadeEstimada;
    private String dataDesejada;
    private String status;
    private String observacoes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getTipoResiduoId() {
        return tipoResiduoId;
    }

    public void setTipoResiduoId(int tipoResiduoId) {
        this.tipoResiduoId = tipoResiduoId;
    }

    public int getColetorId() {
        return coletorId;
    }

    public void setColetorId(int coletorId) {
        this.coletorId = coletorId;
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

    public String getDataDesejada() {
        return dataDesejada;
    }

    public void setDataDesejada(String dataDesejada) {
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
}
