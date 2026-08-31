package br.fai.lds.e_lixo_zero.dto;

public class LoginRequestDto {

    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(final String senha) {
        this.senha = senha;
    }
}
