package ar.edu.unlar.programacion3.repository;

import ar.edu.unlar.programacion3.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementación en memoria del repositorio de productos.
 *
 * <p>Extiende {@link GenericInMemoryRepository} heredando el comportamiento
 * CRUD base e implementa las queries específicas de productos mediante
 * {@link java.util.stream.Stream#filter(java.util.function.Predicate)}
 * sobre {@code dataStore.values()} → complejidad O(n).</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
@Repository
public class InMemoryProductoRepository
        extends GenericInMemoryRepository<Producto, Long>
        implements ProductoRepository {

    /**
     * Persiste un producto nuevo o actualiza uno existente.
     *
     * <p>Si el producto no tiene ID, se le asigna uno automáticamente
     * mediante el generador atómico.</p>
     *
     * @param producto el producto a guardar o actualizar
     * @return el producto persistido con su ID asignado
     */
    @Override
    public Producto save(Producto producto) {
        if (producto.getId() == null) {
            producto.setId(idGenerator.getAndIncrement());
        }
        dataStore.put(producto.getId(), producto);
        return producto;
    }

    /**
     * Busca todos los productos pertenecientes a una categoría específica.
     *
     * <p>Itera todos los productos del mapa → O(n).</p>
     *
     * @param categoriaId identificador de la categoría a filtrar
     * @return lista de productos de esa categoría; lista vacía si no hay ninguno
     */
    @Override
    public List<Producto> findByCategoria(Long categoriaId) {
        return dataStore.values().stream()
                .filter(p -> p.getCategoria().getId().equals(categoriaId))
                .toList();
    }

    /**
     * Busca productos cuyo nombre contenga el texto dado (case-insensitive).
     *
     * <p>Itera todos los productos aplicando {@link String#contains(CharSequence)}
     * → complejidad O(n·m) donde m es la longitud del texto buscado.</p>
     *
     * @param texto texto a buscar dentro del nombre del producto
     * @return lista de productos que coinciden; lista vacía si no hay ninguno
     */
    @Override
    public List<Producto> buscarPorNombre(String texto) {
        String lower = texto.toLowerCase();
        return dataStore.values().stream()
                .filter(p -> p.getNombre().toLowerCase().contains(lower))
                .toList();
    }
}