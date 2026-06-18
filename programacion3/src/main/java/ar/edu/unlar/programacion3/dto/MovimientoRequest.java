package ar.edu.unlar.programacion3.dto;

import ar.edu.unlar.programacion3.model.TipoMovimiento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * DTO de entrada para registrar un movimiento de inventario.
 *
 * <p>Valida que el producto exista, que el tipo sea válido y
 * que la cantidad sea estrictamente positiva.</p>
 *
 * @param productoId identificador del producto afectado (obligatorio)
 * @param tipo       tipo de movimiento: ENTRADA o SALIDA (obligatorio)
 * @param cantidad   cantidad de unidades del movimiento (debe ser &gt; 0)
 * @param motivo     descripción o motivo del movimiento (máximo 255 caracteres)
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public record MovimientoRequest(

        @NotNull(message = "El producto es obligatorio")
        Long productoId,

        @NotNull(message = "El tipo de movimiento es obligatorio")
        TipoMovimiento tipo,

        @Positive(message = "La cantidad debe ser mayor a 0")
        int cantidad,

        @Size(max = 255, message = "El motivo no puede superar los 255 caracteres")
        String motivo

) {}