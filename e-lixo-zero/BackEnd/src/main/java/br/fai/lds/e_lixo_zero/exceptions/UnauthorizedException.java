package br.fai.lds.e_lixo_zero.exceptions;

public class UnauthorizedException extends ApiException {

    public UnauthorizedException(final String message) {
        super(message, 401);
    }
}
