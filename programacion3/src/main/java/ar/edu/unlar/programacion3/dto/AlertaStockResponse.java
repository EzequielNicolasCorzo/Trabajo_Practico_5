package ar.edu.unlar.programacion3.dto;

import ar.edu.unlar.programacion3.model.NivelAlerta;

/**
 * DTO de salida que representa un producto en estado de alerta de stock.
 *
 * <p>Se retorna en el endpoint {@code GET /api/alertas/stock-bajo}
 * para informar qué productos requieren reposición de inventario.</p>
 *
 * @param productoId  identificador del producto en alerta
 * @param nombre      nombre del producto
 * @param stockActual cantidad actual de stock
 * @param nivelAlerta nivel de alerta calculado: BAJO o CRITICO
 * @param categoria   nombre de la categoría del producto
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public record AlertaStockResponse(
        Long productoId,
        String nombre,
        int stockActual,
        NivelAlerta nivelAlerta,
        String categoria
) {}