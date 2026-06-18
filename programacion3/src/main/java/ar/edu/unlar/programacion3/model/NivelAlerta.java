package ar.edu.unlar.programacion3.model;

/**
 * Enumeración que representa los niveles de alerta de stock de un producto.
 *
 * <p>El nivel se determina comparando el stock actual contra los umbrales
 * configurados en {@code application.yml}.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public enum NivelAlerta {

    /** Stock por encima o igual al umbral mínimo. Sin alerta. */
    NORMAL,

    /** Stock por debajo del mínimo pero por encima o igual al crítico. */
    BAJO,

    /** Stock por debajo del umbral crítico. Requiere acción inmediata. */
    CRITICO
}