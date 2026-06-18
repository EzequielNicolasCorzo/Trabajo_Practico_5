package ar.edu.unlar.programacion3.dto;

import java.time.LocalDateTime;

/**
 * DTO de salida genérico para respuestas de error de la API.
 *
 * <p>Se utiliza en el {@code GlobalExceptionHandler} para retornar
 * mensajes de error estructurados y consistentes en todos los endpoints.</p>
 *
 * @param error     descripción corta del tipo de error (ej.: "Stock insuficiente")
 * @param mensaje   mensaje detallado con información útil para el cliente
 * @param timestamp fecha y hora en que ocurrió el error
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public record ErrorResponse(
        String error,
        String mensaje,
        LocalDateTime timestamp
) {}