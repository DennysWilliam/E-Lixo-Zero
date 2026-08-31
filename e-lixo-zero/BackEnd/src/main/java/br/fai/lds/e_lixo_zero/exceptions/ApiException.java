package br.fai.lds.e_lixo_zero.exceptions;

public abstract class ApiException extends RuntimeException {

    private final int statusCode;

    protected ApiException(final String message, final int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
