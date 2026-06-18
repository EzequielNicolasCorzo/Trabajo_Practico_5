package ar.edu.unlar.programacion3.repository;

import ar.edu.unlar.programacion3.model.Categoria;

/**
 * Contrato del repositorio de categorías.
 *
 * <p>Extiende {@link IGenericRepository} heredando las operaciones CRUD base.
 * Siguiendo el principio ISP (Interface Segregation), esta interfaz solo
 * agrega operaciones específicas de categorías.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public interface CategoriaRepository extends IGenericRepository<Categoria, Long> {
}