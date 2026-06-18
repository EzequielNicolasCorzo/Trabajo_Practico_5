package ar.edu.unlar.programacion3.repository;

import ar.edu.unlar.programacion3.model.MovimientoInventario;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementación en memoria del repositorio de movimientos de inventario.
 *
 * <p>Extiende {@link GenericInMemoryRepository} heredando el comportamiento
 * CRUD base e implementa la consulta de historial por producto.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
@Repository
public class InMemoryMovimientoRepository
        extends GenericInMemoryRepository<MovimientoInventario, Long>
        implements MovimientoRepository {

    /**
     * Persiste un movimiento nuevo o actualiza uno existente.
     *
     * <p>Si el movimiento no tiene ID, se le asigna uno automáticamente
     * mediante el generador atómico.</p>
     *
     * @param movimiento el movimiento a guardar o actualizar
     * @return el movimiento persistido con su ID asignado
     */
    @Override
    public MovimientoInventario save(MovimientoInventario movimiento) {
        if (movimiento.getId() == null) {
            movimiento.setId(idGenerator.getAndIncrement());
        }
        dataStore.put(movimiento.getId(), movimiento);
        return movimiento;
    }

    /**
     * Retorna el historial de movimientos asociados a un producto específico.
     *
     * <p>Itera todos los movimientos filtrando por ID de producto → O(n).</p>
     *
     * @param productoId identificador del producto a consultar
     * @return lista de movimientos del producto; lista vacía si no hay ninguno
     */
    @Override
    public List<MovimientoInventario> findByProductoId(Long productoId) {
        return dataStore.values().stream()
                .filter(m -> m.getProducto().getId().equals(productoId))
                .toList();
    }
}