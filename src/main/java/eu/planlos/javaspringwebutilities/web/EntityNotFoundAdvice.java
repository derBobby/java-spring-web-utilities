package eu.planlos.javaspringwebutilities.web;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class EntityNotFoundAdvice {

    /**
     * This handler can be used if a REST request wants to access a non-existing database entity.
     *
     * @param ex Exception which should be handled
     * @return Map that contains all field errors
     */

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(EntityNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }
}
