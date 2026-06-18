package ar.edu.unlar.programacion3.service;

import ar.edu.unlar.programacion3.dto.AlertaStockResponse;

import java.util.List;

/**
 * Contrato del servicio de alertas de stock.
 *
 * <p>Define las operaciones para consultar productos que se encuentran
 * por debajo de los umbrales de stock configurados.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public interface AlertaService {

    /**
     * Retorna todos los productos cuyo stock está por debajo del umbral mínimo.
     *
     * <p>Incluye tanto los productos en nivel {@code BAJO} como {@code CRITICO}.</p>
     *
     * @return lista de alertas de stock; lista vacía si todos los productos
     *         tienen stock suficiente
     */
    List<AlertaStockResponse> obtenerProductosEnAlerta();
}