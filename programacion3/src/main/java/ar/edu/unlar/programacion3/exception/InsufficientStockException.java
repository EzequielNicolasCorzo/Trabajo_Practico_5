package ar.edu.unlar.programacion3.exception;

/**
 * Excepción lanzada cuando se intenta retirar más stock del disponible.
 *
 * <p>Es manejada por {@link GlobalExceptionHandler} y resulta en una
 * respuesta HTTP {@code 409 Conflict}.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public class InsufficientStockException extends RuntimeException {

    /** Identificador del producto con stock insuficiente. */
    private final Long productoId;

    /** Stock disponible al momento de lanzar la excepción. */
    private final int stockDisponible;

    /** Cantidad que se intentó retirar. */
    private final int cantidadSolicitada;

    /**
     * Construye la excepción con información detallada del conflicto de stock.
     *
     * @param productoId         identificador del producto afectado
     * @param cantidadSolicitada cantidad que se intentó retirar
     * @param stockDisponible    stock real disponible en ese momento
     */
    public InsufficientStockException(Long productoId, int cantidadSolicitada, int stockDisponible) {
        super(String.format(
                "No se pueden retirar %d unidades. Stock disponible: %d",
                cantidadSolicitada, stockDisponible
        ));
        this.productoId = productoId;
        this.stockDisponible = stockDisponible;
        this.cantidadSolicitada = cantidadSolicitada;
    }

    /**
     * Retorna el identificador del producto con stock insuficiente.
     *
     * @return el ID del producto
     */
    public Long getProductoId() {
        return productoId;
    }

    /**
     * Retorna el stock disponible al momento del error.
     *
     * @return el stock disponible
     */
    public int getStockDisponible() {
        return stockDisponible;
    }

    /**
     * Retorna la cantidad que se intentó retirar.
     *
     * @return la cantidad solicitada
     */
    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }
}
