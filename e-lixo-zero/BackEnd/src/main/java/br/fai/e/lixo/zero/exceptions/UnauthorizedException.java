package br.fai.e.lixo.zero.exceptions;

public class UnauthorizedException extends ApiException {

    public UnauthorizedException(final String message) {
        super(message, 401);
    }
}
