package ar.edu.unlar.programacion3.dto;

import java.time.LocalDateTime;

import ar.edu.unlar.programacion3.model.TipoMovimiento;

/**
 * DTO de salida que representa un movimiento de inventario en las respuestas de la API.
 *
 * @param id              identificador único del movimiento
 * @param productoId      identificador del producto afectado
 * @param nombreProducto  nombre del producto afectado
 * @param tipo            tipo de movimiento (ENTRADA o SALIDA)
 * @param cantidad        cantidad de unidades involucradas
 * @param stockResultante stock del producto tras aplicar el movimiento
 * @param motivo          descripción o motivo del movimiento
 * @param fecha           fecha y hora en que se registró el movimiento
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public record MovimientoResponse(
        Long id,
        Long productoId,
        String nombreProducto,
        TipoMovimiento tipo,
        int cantidad,
        int stockResultante,
        String motivo,
        LocalDateTime fecha
) {}