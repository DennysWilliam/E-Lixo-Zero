package br.fai.e.lixo.zero.exceptions;

public class BadRequestException extends ApiException {

    public BadRequestException(final String message) {
        super(message, 400);
    }
}
