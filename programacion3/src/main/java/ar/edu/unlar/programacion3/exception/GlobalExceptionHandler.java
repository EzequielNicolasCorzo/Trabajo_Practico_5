package ar.edu.unlar.programacion3.exception;

import ar.edu.unlar.programacion3.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Manejador centralizado de excepciones para toda la API REST.
 *
 * <p>Intercepta las excepciones lanzadas en cualquier capa y las convierte
 * en respuestas HTTP estructuradas con el código de estado adecuado.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones de recurso no encontrado.
     *
     * @param ex la excepción capturada
     * @return respuesta HTTP {@code 404 Not Found} con mensaje descriptivo
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                "Recurso no encontrado",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Maneja excepciones de stock insuficiente.
     *
     * <p>Retorna información adicional sobre el producto y el stock disponible
     * para que el cliente pueda tomar decisiones informadas.</p>
     *
     * @param ex la excepción capturada
     * @return respuesta HTTP {@code 409 Conflict} con detalles del conflicto
     */
    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<Map<String, Object>> handleInsufficientStock(InsufficientStockException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Stock insuficiente");
        body.put("mensaje", ex.getMessage());
        body.put("productoId", ex.getProductoId());
        body.put("stockDisponible", ex.getStockDisponible());
        body.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    /**
     * Maneja excepciones de violación de reglas de negocio.
     *
     * @param ex la excepción capturada
     * @return respuesta HTTP {@code 409 Conflict} con mensaje descriptivo
     */
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorResponse> handleBusinessRule(BusinessRuleException ex) {
        ErrorResponse error = new ErrorResponse(
                "Violación de regla de negocio",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    /**
     * Maneja excepciones de validación de campos ({@code @Valid}).
     *
     * <p>Recorre todos los errores de campo y los devuelve como un mapa
     * {@code campo → mensaje} para facilitar la corrección en el cliente.</p>
     *
     * @param ex la excepción de validación capturada por Spring
     * @return respuesta HTTP {@code 400 Bad Request} con detalle por campo
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> erroresCampos = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            erroresCampos.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        Map<String, Object> body = new HashMap<>();
        body.put("error", "Error de validación");
        body.put("campos", erroresCampos);
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    /**
     * Maneja cualquier excepción no contemplada específicamente.
     *
     * <p>Actúa como red de seguridad para errores inesperados,
     * evitando exponer stack traces al cliente.</p>
     *
     * @param ex la excepción capturada
     * @return respuesta HTTP {@code 500 Internal Server Error}
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                "Error interno del servidor",
                "Ocurrió un error inesperado. Por favor intente nuevamente.",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}