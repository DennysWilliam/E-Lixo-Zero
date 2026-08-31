package br.fai.lds.e_lixo_zero.exceptions;

public class BadRequestException extends ApiException {

    public BadRequestException(final String message) {
        super(message, 400);
    }
}
