package ar.edu.unlar.programacion3.model;

/**
 * Representa una categoría que agrupa productos del inventario.
 *
 * <p>Una categoría tiene un identificador único y un nombre descriptivo.
 * Los productos se asocian a una categoría mediante composición.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public class Categoria {

    /** Identificador único de la categoría. */
    private Long id;

    /** Nombre descriptivo de la categoría (ej.: Electrónicos, Alimentos). */
    private String nombre;

    /** Descripción opcional de la categoría. */
    private String descripcion;

    /**
     * Constructor completo para crear una categoría con todos sus atributos.
     *
     * @param id          identificador único
     * @param nombre      nombre de la categoría
     * @param descripcion descripción opcional
     */
    public Categoria(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Constructor sin ID, utilizado antes de persistir la entidad.
     *
     * @param nombre      nombre de la categoría
     * @param descripcion descripción opcional
     */
    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Retorna el identificador único de la categoría.
     *
     * @return el ID de la categoría
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el identificador único de la categoría.
     *
     * @param id el ID a asignar
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna el nombre de la categoría.
     *
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre de la categoría.
     *
     * @param nombre el nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna la descripción de la categoría.
     *
     * @return la descripción, o {@code null} si no fue definida
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Asigna la descripción de la categoría.
     *
     * @param descripcion la descripción a asignar
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
