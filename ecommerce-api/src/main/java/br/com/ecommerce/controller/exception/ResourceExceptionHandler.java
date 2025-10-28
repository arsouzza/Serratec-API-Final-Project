package br.com.ecommerce.controller.exception;

import br.com.ecommerce.exception.RecursoNaoEncontradoException;
import br.com.ecommerce.exception.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<StandardError> resourceNotFound(RecursoNaoEncontradoException e, HttpServletRequest request) {
        String error = "Recurso não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> databaseViolation(DataIntegrityViolationException e, HttpServletRequest request) {
        String error = "Erro no banco de dados";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, "Violação de integridade: este recurso não pode ser removido pois está associado a outro.", request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> enumError(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        String error = "Erro no parâmetro da requisição";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "O valor '" + e.getValue() + "' não é válido para o campo '" + e.getName() + "'.";
        StandardError err = new StandardError(Instant.now(), status.value(), error, message, request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}