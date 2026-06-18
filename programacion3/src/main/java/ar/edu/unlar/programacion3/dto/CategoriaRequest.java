package ar.edu.unlar.programacion3.dto;

/**
 * DTO de entrada para crear o actualizar una categoría.
 *
 * <p>Todos los campos son validados con Jakarta Bean Validation
 * antes de llegar al servicio.</p>
 *
 * @param nombre      nombre de la categoría (obligatorio, 2-100 caracteres)
 * @param descripcion descripción opcional (máximo 500 caracteres)
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public record CategoriaRequest(
        String nombre,
        String descripcion

) {}