package br.fai.lds.e_lixo_zero.domain;

import java.util.ArrayList;
import java.util.List;

public class PontoColetaModel {

    private int id;
    private String nome;
    private String endereco;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefone;
    private String horario;
    private List<String> residuos = new ArrayList<>();
    private double latitude;
    private double longitude;
    private boolean ativo;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        if (endereco != null && !endereco.isBlank()) {
            return endereco;
        }
        final StringBuilder sb = new StringBuilder();
        if (logradouro != null && !logradouro.isBlank()) {
            sb.append(logradouro);
        }
        if (numero != null && !numero.isBlank()) {
            sb.append(", ").append(numero);
        }
        if (bairro != null && !bairro.isBlank()) {
            sb.append(" - ").append(bairro);
        }
        if (cidade != null && !cidade.isBlank()) {
            sb.append(", ").append(cidade);
        }
        if (estado != null && !estado.isBlank()) {
            sb.append(" - ").append(estado);
        }
        return sb.toString();
    }

    public void setEndereco(final String endereco) {
        this.endereco = endereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(final String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(final String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(final String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(final String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(final String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone != null ? telefone : "";
    }

    public void setTelefone(final String telefone) {
        this.telefone = telefone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(final double longitude) {
        this.longitude = longitude;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(final String horario) {
        this.horario = horario;
    }

    public List<String> getResiduos() {
        return residuos;
    }

    public void setResiduos(final List<String> residuos) {
        this.residuos = residuos != null ? residuos : new ArrayList<>();
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(final boolean ativo) {
        this.ativo = ativo;
    }
}
