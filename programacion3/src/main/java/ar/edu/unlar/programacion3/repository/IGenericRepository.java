package ar.edu.unlar.programacion3.repository;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica que define el contrato CRUD para todos los repositorios del sistema.
 *
 * <p>Aplicando el principio DIP (Dependency Inversion Principle), las capas superiores
 * dependen de esta abstracción y no de implementaciones concretas.</p>
 *
 * @param <T>  tipo de la entidad gestionada
 * @param <ID> tipo del identificador de la entidad
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public interface IGenericRepository<T, ID> {

    /**
     * Retorna todas las entidades almacenadas.
     *
     * @return lista con todas las entidades; lista vacía si no hay ninguna
     */
    List<T> findAll();

    /**
     * Busca una entidad por su identificador único.
     *
     * @param id identificador de la entidad a buscar
     * @return un {@link Optional} con la entidad si existe, o vacío si no
     */
    Optional<T> findById(ID id);

    /**
     * Persiste una entidad nueva o actualiza una existente.
     *
     * <p>Si la entidad no tiene ID asignado, se le genera uno automáticamente.
     * Si ya tiene ID, se reemplaza la entrada existente.</p>
     *
     * @param entity la entidad a guardar o actualizar
     * @return la entidad persistida, con su ID asignado
     */
    T save(T entity);

    /**
     * Elimina la entidad con el identificador dado.
     *
     * @param id identificador de la entidad a eliminar
     * @throws ar.edu.unlar.programacion3.exception.ResourceNotFoundException si no existe entidad con ese ID
     */
    void deleteById(ID id);

    /**
     * Verifica si existe una entidad con el identificador dado.
     *
     * @param id identificador a verificar
     * @return {@code true} si existe, {@code false} en caso contrario
     */
    boolean existsById(ID id);

    /**
     * Retorna la cantidad total de entidades almacenadas.
     *
     * @return el número de entidades
     */
    long count();
}