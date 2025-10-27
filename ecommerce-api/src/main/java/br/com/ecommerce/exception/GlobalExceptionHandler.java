package br.com.ecommerce.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ecommerce.dto.CategoriaResponseDTO;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CategoriaResponseDTO<Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        // Renomeado CategoriaResponseDTO para ApiResponseDTO
        CategoriaResponseDTO<Object> response = new CategoriaResponseDTO<>("error", ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<CategoriaResponseDTO<Object>> handleInvalidData(InvalidDataException ex) {
        // Renomeado CategoriaResponseDTO para ApiResponseDTO
        CategoriaResponseDTO<Object> response = new CategoriaResponseDTO<>("error", ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Unificando os tratamentos de erro de banco de dados [cite: 36]
    @ExceptionHandler({DatabaseException.class, DataIntegrityViolationException.class})
    public ResponseEntity<CategoriaResponseDTO<Object>> handleDatabaseErrors(Exception ex) {
        String mensagem = (ex instanceof DatabaseException) 
            ? ex.getMessage() 
            : "Erro de integridade no banco de dados.";
        
        // Renomeado CategoriaResponseDTO para ApiResponseDTO
        CategoriaResponseDTO<Object> response = new CategoriaResponseDTO<>("error", mensagem, null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response); // HTTP 409 Conflict é bom para integridade
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CategoriaResponseDTO<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String mensagem = (bindingResult.getFieldError() != null)
                ? ex.getBindingResult().getFieldError().getDefaultMessage()
                : "Erro de validação nos dados enviados.";
        
        // Renomeado CategoriaResponseDTO para ApiResponseDTO
        CategoriaResponseDTO<Object> response = new CategoriaResponseDTO<>("error", mensagem, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CategoriaResponseDTO<Object>> handleGenericError(Exception ex) {
        ex.printStackTrace(); // Útil para debug no console
        // Renomeado CategoriaResponseDTO para ApiResponseDTO
        CategoriaResponseDTO<Object> response = new CategoriaResponseDTO<>("error", "Erro interno no servidor.", null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}