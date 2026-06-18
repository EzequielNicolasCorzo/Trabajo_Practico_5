package ar.edu.unlar.programacion3.dto;

/**
 * DTO de salida que representa un producto en las respuestas de la API.
 *
 * <p>Incluye la categoría embebida como {@link CategoriaResponse}
 * para evitar exponer entidades del modelo directamente.</p>
 *
 * @param id          identificador único del producto
 * @param nombre      nombre del producto
 * @param descripcion descripción del producto
 * @param precio      precio unitario
 * @param stock       cantidad actual en inventario
 * @param categoria   categoría a la que pertenece el producto
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public record ProductoResponse(
        Long id,
        String nombre,
        String descripcion,
        double precio,
        int stock,
        CategoriaResponse categoria
) {}