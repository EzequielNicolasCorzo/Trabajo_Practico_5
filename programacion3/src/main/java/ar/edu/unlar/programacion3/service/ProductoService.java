package ar.edu.unlar.programacion3.service;

import ar.edu.unlar.programacion3.dto.ProductoRequest;
import ar.edu.unlar.programacion3.exception.ResourceNotFoundException;
import ar.edu.unlar.programacion3.model.Producto;

import java.util.List;

/**
 * Contrato del servicio de productos.
 *
 * <p>Define las operaciones de negocio disponibles para la gestión
 * de productos del inventario.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public interface ProductoService {

    /**
     * Retorna todos los productos del sistema.
     *
     * @return lista con todos los productos; lista vacía si no hay ninguno
     */
    List<Producto> findAll();

    /**
     * Busca un producto por su identificador único.
     *
     * @param id identificador del producto
     * @return el producto encontrado
     * @throws ResourceNotFoundException si no existe producto con ese ID
     */
    Producto findById(Long id);

    /**
     * Crea un nuevo producto en el sistema.
     *
     * @param request datos del producto a crear
     * @return el producto creado con su ID asignado
     * @throws ResourceNotFoundException si no existe la categoría indicada
     */
    Producto crear(ProductoRequest request);

    /**
     * Actualiza un producto existente.
     *
     * @param id      identificador del producto a actualizar
     * @param request nuevos datos del producto
     * @return el producto actualizado
     * @throws ResourceNotFoundException si no existe producto o categoría con ese ID
     */
    Producto actualizar(Long id, ProductoRequest request);

    /**
     * Elimina un producto del sistema.
     *
     * @param id identificador del producto a eliminar
     * @throws ResourceNotFoundException si no existe producto con ese ID
     */
    void eliminar(Long id);

    /**
     * Busca productos cuyo nombre contenga el texto dado (case-insensitive).
     *
     * @param texto texto a buscar dentro del nombre del producto
     * @return lista de productos que coinciden; lista vacía si no hay ninguno
     */
    List<Producto> buscarPorNombre(String texto);

    /**
     * Retorna todos los productos ordenados por un campo y dirección dados.
     *
     * @param campo campo por el que ordenar: {@code nombre}, {@code precio} o {@code stock}
     * @param orden dirección del ordenamiento: {@code asc} o {@code desc}
     * @return lista de productos ordenada
     */
    List<Producto> listarOrdenados(String campo, String orden);

    /**
     * Filtra productos por categoría, rango de precio y disponibilidad de stock.
     *
     * @param categoriaId identificador de la categoría (puede ser {@code null})
     * @param precioMin   precio mínimo del filtro (puede ser {@code null})
     * @param precioMax   precio máximo del filtro (puede ser {@code null})
     * @param enStock     si es {@code true}, retorna solo productos con stock mayor a 0
     * @return lista de productos que cumplen los filtros
     */
    List<Producto> filtrar(Long categoriaId, Double precioMin, Double precioMax, Boolean enStock);
}