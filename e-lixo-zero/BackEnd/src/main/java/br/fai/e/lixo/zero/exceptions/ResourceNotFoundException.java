package br.fai.e.lixo.zero.exceptions;

public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(final String message) {
        super(message, 404);
    }
}
