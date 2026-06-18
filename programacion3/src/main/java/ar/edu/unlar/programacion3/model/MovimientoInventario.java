package ar.edu.unlar.programacion3.model;

import java.time.LocalDateTime;

/**
 * Representa un movimiento de inventario registrado en el sistema.
 *
 * <p>Cada movimiento corresponde a una entrada o salida de stock de un producto
 * específico, y forma parte del historial trazable de operaciones del depósito.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public class MovimientoInventario {

    /** Identificador único del movimiento. */
    private Long id;

    /** Producto al que corresponde este movimiento. */
    private Producto producto;

    /** Tipo de movimiento: ENTRADA o SALIDA. */
    private TipoMovimiento tipo;

    /** Cantidad de unidades involucradas en el movimiento. */
    private int cantidad;

    /** Stock resultante del producto luego de aplicar el movimiento. */
    private int stockResultante;

    /** Motivo o descripción del movimiento (ej.: "Venta al cliente #1083"). */
    private String motivo;

    /** Fecha y hora en que se registró el movimiento. */
    private LocalDateTime fecha;

    /**
     * Constructor completo para crear un movimiento con todos sus atributos.
     *
     * @param id               identificador único
     * @param producto         producto afectado por el movimiento
     * @param tipo             tipo de movimiento (ENTRADA o SALIDA)
     * @param cantidad         cantidad de unidades involucradas
     * @param stockResultante  stock del producto tras aplicar el movimiento
     * @param motivo           descripción o motivo del movimiento
     * @param fecha            fecha y hora del registro
     */
    public MovimientoInventario(Long id, Producto producto, TipoMovimiento tipo,
                                 int cantidad, int stockResultante,
                                 String motivo, LocalDateTime fecha) {
        this.id = id;
        this.producto = producto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.stockResultante = stockResultante;
        this.motivo = motivo;
        this.fecha = fecha;
    }

    /**
     * Constructor sin ID, utilizado antes de persistir el movimiento.
     *
     * @param producto         producto afectado
     * @param tipo             tipo de movimiento
     * @param cantidad         cantidad de unidades
     * @param stockResultante  stock resultante tras el movimiento
     * @param motivo           motivo del movimiento
     * @param fecha            fecha y hora del registro
     */
    public MovimientoInventario(Producto producto, TipoMovimiento tipo,
                                 int cantidad, int stockResultante,
                                 String motivo, LocalDateTime fecha) {
        this.producto = producto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.stockResultante = stockResultante;
        this.motivo = motivo;
        this.fecha = fecha;
    }

    /**
     * Retorna el identificador único del movimiento.
     *
     * @return el ID del movimiento
     */
    public Long getId() { return id; }

    /**
     * Asigna el identificador único del movimiento.
     *
     * @param id el ID a asignar
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Retorna el producto asociado al movimiento.
     *
     * @return el producto
     */
    public Producto getProducto() { return producto; }

    /**
     * Asigna el producto asociado al movimiento.
     *
     * @param producto el producto a asignar
     */
    public void setProducto(Producto producto) { this.producto = producto; }

    /**
     * Retorna el tipo de movimiento.
     *
     * @return {@link TipoMovimiento#ENTRADA} o {@link TipoMovimiento#SALIDA}
     */
    public TipoMovimiento getTipo() { return tipo; }

    /**
     * Asigna el tipo de movimiento.
     *
     * @param tipo el tipo a asignar
     */
    public void setTipo(TipoMovimiento tipo) { this.tipo = tipo; }

    /**
     * Retorna la cantidad de unidades involucradas en el movimiento.
     *
     * @return la cantidad
     */
    public int getCantidad() { return cantidad; }

    /**
     * Asigna la cantidad de unidades del movimiento.
     *
     * @param cantidad la cantidad a asignar
     */
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    /**
     * Retorna el stock del producto resultante tras aplicar el movimiento.
     *
     * @return el stock resultante
     */
    public int getStockResultante() { return stockResultante; }

    /**
     * Asigna el stock resultante del movimiento.
     *
     * @param stockResultante el stock resultante a asignar
     */
    public void setStockResultante(int stockResultante) { this.stockResultante = stockResultante; }

    /**
     * Retorna el motivo o descripción del movimiento.
     *
     * @return el motivo
     */
    public String getMotivo() { return motivo; }

    /**
     * Asigna el motivo del movimiento.
     *
     * @param motivo el motivo a asignar
     */
    public void setMotivo(String motivo) { this.motivo = motivo; }

    /**
     * Retorna la fecha y hora en que se registró el movimiento.
     *
     * @return la fecha del movimiento
     */
    public LocalDateTime getFecha() { return fecha; }

    /**
     * Asigna la fecha y hora del movimiento.
     *
     * @param fecha la fecha a asignar
     */
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}