package ar.edu.unlar.programacion3.repository;

import ar.edu.unlar.programacion3.model.MovimientoInventario;

import java.util.List;

/**
 * Contrato del repositorio de movimientos de inventario.
 *
 * <p>Extiende {@link IGenericRepository} heredando las operaciones CRUD base
 * y agrega la consulta de historial por producto.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public interface MovimientoRepository extends IGenericRepository<MovimientoInventario, Long> {

    /**
     * Retorna el historial de movimientos asociados a un producto específico.
     *
     * @param productoId identificador del producto a consultar
     * @return lista de movimientos del producto; lista vacía si no hay ninguno
     */
    List<MovimientoInventario> findByProductoId(Long productoId);
}