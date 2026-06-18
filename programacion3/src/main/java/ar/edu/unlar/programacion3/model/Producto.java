package ar.edu.unlar.programacion3.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Representa un producto del inventario del depósito inteligente.
 *
 * <p>El stock se gestiona mediante {@link AtomicInteger} para garantizar
 * thread-safety en entornos concurrentes, donde múltiples requests HTTP
 * pueden modificar el stock del mismo producto simultáneamente.</p>
 *
 * <p>Un {@code Producto} <strong>tiene una</strong> {@link Categoria}
 * (composición sobre herencia), lo que permite cambiar la categoría
 * sin modificar la jerarquía de clases.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public class Producto {

    /** Identificador único del producto. */
    private Long id;

    /** Nombre del producto. */
    private String nombre;

    /** Descripción opcional del producto. */
    private String descripcion;

    /** Precio unitario del producto. Debe ser mayor o igual a 0. */
    private double precio;

    /**
     * Cantidad de unidades disponibles en inventario.
     * Se usa {@link AtomicInteger} para garantizar operaciones thread-safe
     * sin necesidad de sincronización explícita.
     */
    private final AtomicInteger stock;

    /**
     * Categoría a la que pertenece el producto.
     * Relación de composición: el producto tiene una categoría.
     */
    private Categoria categoria;

    /**
     * Constructor completo para crear un producto con todos sus atributos.
     *
     * @param id          identificador único
     * @param nombre      nombre del producto
     * @param descripcion descripción opcional
     * @param precio      precio unitario (debe ser &gt;= 0)
     * @param stockInicial cantidad inicial en inventario (debe ser &gt;= 0)
     * @param categoria   categoría a la que pertenece el producto
     */
    public Producto(Long id, String nombre, String descripcion,
                    double precio, int stockInicial, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = new AtomicInteger(stockInicial);
        this.categoria = categoria;
    }

    /**
     * Constructor sin ID, utilizado antes de persistir la entidad.
     *
     * @param nombre      nombre del producto
     * @param descripcion descripción opcional
     * @param precio      precio unitario (debe ser &gt;= 0)
     * @param stockInicial cantidad inicial en inventario (debe ser &gt;= 0)
     * @param categoria   categoría a la que pertenece el producto
     */
    public Producto(String nombre, String descripcion,
                    double precio, int stockInicial, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = new AtomicInteger(stockInicial);
        this.categoria = categoria;
    }

    /**
     * Retorna el identificador único del producto.
     *
     * @return el ID del producto
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el identificador único del producto.
     *
     * @param id el ID a asignar
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna el nombre del producto.
     *
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del producto.
     *
     * @param nombre el nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna la descripción del producto.
     *
     * @return la descripción, o {@code null} si no fue definida
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Asigna la descripción del producto.
     *
     * @param descripcion la descripción a asignar
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Retorna el precio unitario del producto.
     *
     * @return el precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Asigna el precio unitario del producto.
     *
     * @param precio el precio a asignar (debe ser &gt;= 0)
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Retorna la cantidad actual de stock disponible.
     *
     * @return el stock actual como entero
     */
    public int getStock() {
        return stock.get();
    }

    /**
     * Incrementa el stock del producto de forma atómica.
     *
     * @param cantidad cantidad a agregar (debe ser positiva)
     * @return el nuevo valor del stock tras el incremento
     */
    public int incrementarStock(int cantidad) {
        return stock.addAndGet(cantidad);
    }

    /**
     * Decrementa el stock del producto de forma atómica.
     *
     * <p>Este método no valida si el stock resultante es negativo;
     * esa validación es responsabilidad del servicio.</p>
     *
     * @param cantidad cantidad a retirar (debe ser positiva)
     * @return el nuevo valor del stock tras el decremento
     */
    public int decrementarStock(int cantidad) {
        return stock.addAndGet(-cantidad);
    }

    /**
     * Retorna la categoría a la que pertenece el producto.
     *
     * @return la categoría del producto
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Asigna la categoría del producto.
     *
     * @param categoria la categoría a asignar
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}