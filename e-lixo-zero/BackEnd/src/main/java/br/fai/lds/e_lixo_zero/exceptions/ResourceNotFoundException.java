package br.fai.lds.e_lixo_zero.exceptions;

public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(final String message) {
        super(message, 404);
    }
}
