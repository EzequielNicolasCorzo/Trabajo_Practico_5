package ar.edu.unlar.programacion3.service;

import ar.edu.unlar.programacion3.model.Categoria;
import ar.edu.unlar.programacion3.dto.CategoriaRequest;
import ar.edu.unlar.programacion3.exception.ResourceNotFoundException;
import ar.edu.unlar.programacion3.exception.BusinessRuleException;

import java.util.List;

/**
 * Contrato del servicio de categorías.
 *
 * <p>Define las operaciones de negocio disponibles para la gestión
 * de categorías del inventario.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public interface CategoriaService {

    /**
     * Retorna todas las categorías del sistema.
     *
     * @return lista con todas las categorías; lista vacía si no hay ninguna
     */
    List<Categoria> findAll();

    /**
     * Busca una categoría por su identificador único.
     *
     * @param id identificador de la categoría
     * @return la categoría encontrada
     * @throws ResourceNotFoundException si no existe categoría con ese ID
     */
    Categoria findById(Long id);

    /**
     * Crea una nueva categoría en el sistema.
     *
     * @param request datos de la categoría a crear
     * @return la categoría creada con su ID asignado
     */
    Categoria crear(CategoriaRequest request);

    /**
     * Actualiza una categoría existente.
     *
     * @param id      identificador de la categoría a actualizar
     * @param request nuevos datos de la categoría
     * @return la categoría actualizada
     * @throws ResourceNotFoundException si no existe categoría con ese ID
     */
    Categoria actualizar(Long id, CategoriaRequest request);

    /**
     * Elimina una categoría del sistema.
     *
     * @param id identificador de la categoría a eliminar
     * @throws ResourceNotFoundException si no existe categoría con ese ID
     * @throws BusinessRuleException     si la categoría tiene productos asociados
     */
    void eliminar(Long id);
}