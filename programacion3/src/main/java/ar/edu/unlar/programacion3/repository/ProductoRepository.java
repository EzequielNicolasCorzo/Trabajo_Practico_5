package ar.edu.unlar.programacion3.repository;

import ar.edu.unlar.programacion3.model.Producto;

import java.util.List;

/**
 * Contrato del repositorio de productos.
 *
 * <p>Extiende {@link IGenericRepository} heredando las operaciones CRUD base
 * y agrega queries específicas de productos.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public interface ProductoRepository extends IGenericRepository<Producto, Long> {

    /**
     * Busca todos los productos pertenecientes a una categoría específica.
     *
     * @param categoriaId identificador de la categoría a filtrar
     * @return lista de productos de esa categoría; lista vacía si no hay ninguno
     */
    List<Producto> findByCategoria(Long categoriaId);

    /**
     * Busca productos cuyo nombre contenga el texto dado (case-insensitive).
     *
     * @param texto texto a buscar dentro del nombre del producto
     * @return lista de productos que coinciden; lista vacía si no hay ninguno
     */
    List<Producto> buscarPorNombre(String texto);
}