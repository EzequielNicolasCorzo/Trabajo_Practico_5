package ar.edu.unlar.programacion3.service;

import ar.edu.unlar.programacion3.dto.ProductoRequest;
import ar.edu.unlar.programacion3.exception.ResourceNotFoundException;
import ar.edu.unlar.programacion3.model.Categoria;
import ar.edu.unlar.programacion3.model.Producto;
import ar.edu.unlar.programacion3.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * Implementación del servicio de productos.
 *
 * <p>Orquesta las operaciones CRUD sobre productos, búsqueda textual,
 * ordenamiento y filtrado, aplicando las reglas de negocio correspondientes.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    /** Repositorio de productos. */
    private final ProductoRepository productoRepository;

    /** Servicio de categorías para validar existencia al crear o actualizar productos. */
    private final CategoriaService categoriaService;

    /**
     * Construye el servicio inyectando las dependencias necesarias.
     *
     * @param productoRepository repositorio de productos
     * @param categoriaService   servicio de categorías
     */
    public ProductoServiceImpl(ProductoRepository productoRepository,
                                CategoriaService categoriaService) {
        this.productoRepository = productoRepository;
        this.categoriaService = categoriaService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró el producto con id: " + id
                ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Producto crear(ProductoRequest request) {
        Categoria categoria = categoriaService.findById(request.categoriaId());
        Producto producto = new Producto(
                request.nombre(),
                request.descripcion(),
                request.precio(),
                request.stockInicial(),
                categoria
        );
        return productoRepository.save(producto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Producto actualizar(Long id, ProductoRequest request) {
        Producto producto = findById(id);
        Categoria categoria = categoriaService.findById(request.categoriaId());
        producto.setNombre(request.nombre());
        producto.setDescripcion(request.descripcion());
        producto.setPrecio(request.precio());
        producto.setCategoria(categoria);
        return productoRepository.save(producto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminar(Long id) {
        findById(id);
        productoRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Producto> buscarPorNombre(String texto) {
        return productoRepository.buscarPorNombre(texto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Producto> listarOrdenados(String campo, String orden) {
        List<Producto> productos = productoRepository.findAll();

        Comparator<Producto> comparator = switch (campo.toLowerCase()) {
            case "precio" -> Comparator.comparingDouble(Producto::getPrecio);
            case "stock"  -> Comparator.comparingInt(Producto::getStock);
            default       -> Comparator.comparing(Producto::getNombre);
        };

        if ("desc".equalsIgnoreCase(orden)) {
            comparator = comparator.reversed();
        }

        productos.sort(comparator);
        return productos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Producto> filtrar(Long categoriaId, Double precioMin,
                                   Double precioMax, Boolean enStock) {
        return productoRepository.findAll().stream()
                .filter(p -> categoriaId == null ||
                             p.getCategoria().getId().equals(categoriaId))
                .filter(p -> precioMin == null || p.getPrecio() >= precioMin)
                .filter(p -> precioMax == null || p.getPrecio() <= precioMax)
                .filter(p -> enStock == null || !enStock || p.getStock() > 0)
                .toList();
    }
}