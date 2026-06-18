package ar.edu.unlar.programacion3.repository;

import ar.edu.unlar.programacion3.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Implementación abstracta de {@link IGenericRepository} usando {@link ConcurrentHashMap}
 * como almacenamiento en memoria.
 *
 * <p>Provee el comportamiento CRUD base reutilizable para todos los repositorios
 * concretos del sistema, evitando duplicación de código (principio DRY).</p>
 *
 * <p><strong>¿Por qué {@link ConcurrentHashMap}?</strong> Es thread-safe sin bloquear
 * todo el mapa. Múltiples requests HTTP concurrentes pueden leer y escribir
 * simultáneamente sin generar condiciones de carrera ni
 * {@link java.util.ConcurrentModificationException}.</p>
 *
 * @param <T>  tipo de la entidad gestionada
 * @param <ID> tipo del identificador de la entidad
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public abstract class GenericInMemoryRepository<T, ID> implements IGenericRepository<T, ID> {

    /**
     * Almacenamiento en memoria thread-safe.
     * Las subclases pueden acceder directamente para implementar queries específicas.
     */
    protected final ConcurrentHashMap<ID, T> dataStore = new ConcurrentHashMap<>();

    /**
     * Generador de IDs autoincremental thread-safe.
     * Garantiza unicidad de IDs en entornos concurrentes.
     */
    protected final AtomicLong idGenerator = new AtomicLong(1);

    /**
     * Retorna todas las entidades almacenadas.
     *
     * @return lista con todas las entidades; lista vacía si no hay ninguna
     */
    @Override
    public List<T> findAll() {
        return new ArrayList<>(dataStore.values());
    }

    /**
     * Busca una entidad por su identificador único.
     *
     * @param id identificador de la entidad a buscar
     * @return un {@link Optional} con la entidad si existe, o vacío si no
     */
    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(dataStore.get(id));
    }

    /**
     * Elimina la entidad con el identificador dado.
     *
     * @param id identificador de la entidad a eliminar
     * @throws ResourceNotFoundException si no existe entidad con ese ID
     */
    @Override
    public void deleteById(ID id) {
        if (!existsById(id)) {
            throw new ResourceNotFoundException(
                    "No se encontró el recurso con id: " + id
            );
        }
        dataStore.remove(id);
    }

    /**
     * Verifica si existe una entidad con el identificador dado.
     *
     * @param id identificador a verificar
     * @return {@code true} si existe, {@code false} en caso contrario
     */
    @Override
    public boolean existsById(ID id) {
        return dataStore.containsKey(id);
    }

    /**
     * Retorna la cantidad total de entidades almacenadas.
     *
     * @return el número de entidades
     */
    @Override
    public long count() {
        return dataStore.size();
    }
}