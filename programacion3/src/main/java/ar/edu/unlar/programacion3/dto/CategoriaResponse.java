package ar.edu.unlar.programacion3.dto;

/**
 * DTO de salida que representa una categoría en las respuestas de la API.
 *
 * <p>Se utiliza como respuesta en todos los endpoints de categorías y también
 * como campo embebido dentro de {@link ProductoResponse}.</p>
 *
 * @param id          identificador único de la categoría
 * @param nombre      nombre de la categoría
 * @param descripcion descripción opcional de la categoría
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public record CategoriaResponse(
        Long id,
        String nombre,
        String descripcion
) {}