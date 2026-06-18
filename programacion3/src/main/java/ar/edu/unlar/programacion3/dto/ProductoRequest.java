package ar.edu.unlar.programacion3.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/**
 * DTO de entrada para crear o actualizar un producto.
 *
 * <p>Todos los campos son validados con Jakarta Bean Validation
 * antes de llegar al servicio. El stock inicial debe ser mayor o igual a 0.</p>
 *
 * @param nombre      nombre del producto (obligatorio, 2-100 caracteres)
 * @param descripcion descripción opcional (máximo 500 caracteres)
 * @param precio      precio unitario (debe ser &gt;= 0)
 * @param stockInicial cantidad inicial en inventario (debe ser &gt;= 0)
 * @param categoriaId identificador de la categoría a la que pertenece (obligatorio)
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public record ProductoRequest(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
        String nombre,

        @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
        String descripcion,

        @PositiveOrZero(message = "El precio debe ser mayor o igual a 0")
        double precio,

        @PositiveOrZero(message = "El stock inicial debe ser mayor o igual a 0")
        int stockInicial,

        @NotNull(message = "La categoría es obligatoria")
        Long categoriaId

) {}