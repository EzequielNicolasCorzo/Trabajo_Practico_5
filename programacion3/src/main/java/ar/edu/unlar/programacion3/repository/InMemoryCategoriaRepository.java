package ar.edu.unlar.programacion3.repository;

import ar.edu.unlar.programacion3.model.Categoria;
import org.springframework.stereotype.Repository;

/**
 * Implementación en memoria del repositorio de categorías.
 *
 * <p>Extiende {@link GenericInMemoryRepository} heredando el comportamiento
 * CRUD base con {@link java.util.concurrent.ConcurrentHashMap}.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
@Repository
public class InMemoryCategoriaRepository
        extends GenericInMemoryRepository<Categoria, Long>
        implements CategoriaRepository {

    /**
     * Persiste una categoría nueva o actualiza una existente.
     *
     * <p>Si la categoría no tiene ID, se le asigna uno automáticamente
     * mediante el generador atómico.</p>
     *
     * @param categoria la categoría a guardar o actualizar
     * @return la categoría persistida con su ID asignado
     */
    @Override
    public Categoria save(Categoria categoria) {
        if (categoria.getId() == null) {
            categoria.setId(idGenerator.getAndIncrement());
        }
        dataStore.put(categoria.getId(), categoria);
        return categoria;
    }
}