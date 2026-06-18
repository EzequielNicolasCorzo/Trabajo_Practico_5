package ar.edu.unlar.programacion3.model;

/**
 * Enumeración que representa los tipos posibles de movimiento de inventario.
 *
 * <p>Un movimiento puede ser una entrada (incremento de stock)
 * o una salida (decremento de stock).</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public enum TipoMovimiento {

    /** Incremento de stock (compra, devolución, ajuste positivo). */
    ENTRADA,

    /** Decremento de stock (venta, pérdida, ajuste negativo). */
    SALIDA
}