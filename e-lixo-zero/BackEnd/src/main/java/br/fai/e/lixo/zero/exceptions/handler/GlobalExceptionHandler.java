package br.fai.e.lixo.zero.exceptions.handler;

import br.fai.e.lixo.zero.exceptions.ApiException;
import br.fai.e.lixo.zero.exceptions.BadRequestException;
import br.fai.e.lixo.zero.exceptions.ResourceNotFoundException;
import br.fai.e.lixo.zero.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, Object>> handleApiException(final ApiException ex) {
        return buildResponse(ex.getStatusCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(final Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro interno no servidor");
    }

    private ResponseEntity<Map<String, Object>> buildResponse(final int status, final String message) {
        final Map<String, Object> body = Map.of(
                "timestamp", LocalDateTime.now().toString(),
                "status", status,
                "error", message
        );
        return ResponseEntity.status(status).body(body);
    }
}
