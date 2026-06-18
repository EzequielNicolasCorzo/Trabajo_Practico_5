package ar.edu.unlar.programacion3.service;

import ar.edu.unlar.programacion3.dto.CategoriaRequest;
import ar.edu.unlar.programacion3.exception.BusinessRuleException;
import ar.edu.unlar.programacion3.exception.ResourceNotFoundException;
import ar.edu.unlar.programacion3.model.Categoria;
import ar.edu.unlar.programacion3.repository.CategoriaRepository;
import ar.edu.unlar.programacion3.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio de categorías.
 *
 * <p>Orquesta las operaciones CRUD sobre categorías aplicando
 * las reglas de negocio correspondientes.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    /** Repositorio de categorías. */
    private final CategoriaRepository categoriaRepository;

    /** Repositorio de productos, usado para validar integridad referencial. */
    private final ProductoRepository productoRepository;

    /**
     * Construye el servicio inyectando los repositorios necesarios.
     *
     * @param categoriaRepository repositorio de categorías
     * @param productoRepository  repositorio de productos
     */
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository,
                                 ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró la categoría con id: " + id
                ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Categoria crear(CategoriaRequest request) {
        Categoria categoria = new Categoria(request.nombre(), request.descripcion());
        return categoriaRepository.save(categoria);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Categoria actualizar(Long id, CategoriaRequest request) {
        Categoria categoria = findById(id);
        categoria.setNombre(request.nombre());
        categoria.setDescripcion(request.descripcion());
        return categoriaRepository.save(categoria);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminar(Long id) {
        findById(id);
        boolean tieneProductos = !productoRepository.findByCategoria(id).isEmpty();
        if (tieneProductos) {
            throw new BusinessRuleException(
                    "No se puede eliminar la categoría con id " + id +
                    " porque tiene productos asociados."
            );
        }
        categoriaRepository.deleteById(id);
    }
}