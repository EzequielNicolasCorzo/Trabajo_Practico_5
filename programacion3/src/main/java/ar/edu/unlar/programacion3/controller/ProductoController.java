package ar.edu.unlar.programacion3.controller;

import ar.edu.unlar.programacion3.dto.CategoriaResponse;
import ar.edu.unlar.programacion3.dto.ProductoRequest;
import ar.edu.unlar.programacion3.dto.ProductoResponse;
import ar.edu.unlar.programacion3.model.Producto;
import ar.edu.unlar.programacion3.service.ProductoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controlador REST para la gestión de productos del inventario.
 *
 * <p>Expone los endpoints CRUD, búsqueda textual, ordenamiento y filtrado
 * bajo la ruta base {@code /api/productos}. Delega toda la lógica de negocio
 * a {@link ProductoService} y convierte entidades a DTOs antes de responder.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    /** Servicio de productos que contiene la lógica de negocio. */
    private final ProductoService productoService;

    /**
     * Construye el controlador inyectando el servicio de productos.
     *
     * @param productoService servicio de productos
     */
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Retorna todos los productos, con filtros opcionales.
     *
     * @param categoriaId filtra por categoría (opcional)
     * @param precioMin   filtra por precio mínimo (opcional)
     * @param precioMax   filtra por precio máximo (opcional)
     * @param enStock     si es {@code true}, retorna solo productos con stock &gt; 0 (opcional)
     * @return {@code 200 OK} con la lista de productos filtrada
     */
    @GetMapping
    public ResponseEntity<List<ProductoResponse>> listarTodos(
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) Double precioMin,
            @RequestParam(required = false) Double precioMax,
            @RequestParam(required = false) Boolean enStock) {
        List<ProductoResponse> response = productoService
                .filtrar(categoriaId, precioMin, precioMax, enStock)
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    /**
     * Retorna un producto por su identificador único.
     *
     * @param id identificador del producto
     * @return {@code 200 OK} con el producto encontrado,
     *         o {@code 404 Not Found} si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(toResponse(productoService.findById(id)));
    }

    /**
     * Crea un nuevo producto en el sistema.
     *
     * @param request datos del producto a crear (validados con {@code @Valid})
     * @return {@code 201 Created} con el producto creado y header {@code Location},
     *         o {@code 404 Not Found} si la categoría indicada no existe
     */
    @PostMapping
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody ProductoRequest request) {
        Producto producto = productoService.crear(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(producto.getId())
                .toUri();
        return ResponseEntity.created(location).body(toResponse(producto));
    }

    /**
     * Actualiza un producto existente.
     *
     * @param id      identificador del producto a actualizar
     * @param request nuevos datos del producto (validados con {@code @Valid})
     * @return {@code 200 OK} con el producto actualizado,
     *         o {@code 404 Not Found} si no existe el producto o la categoría
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequest request) {
        return ResponseEntity.ok(toResponse(productoService.actualizar(id, request)));
    }

    /**
     * Elimina un producto del sistema.
     *
     * @param id identificador del producto a eliminar
     * @return {@code 204 No Content} si fue eliminado,
     *         o {@code 404 Not Found} si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Busca productos cuyo nombre contenga el texto dado (case-insensitive).
     *
     * @param q texto a buscar (obligatorio, no puede estar vacío)
     * @return {@code 200 OK} con la lista de productos que coinciden,
     *         o {@code 400 Bad Request} si {@code q} está vacío
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoResponse>> buscar(
            @RequestParam @NotBlank(message = "El parámetro de búsqueda no puede estar vacío") String q) {
        List<ProductoResponse> response = productoService.buscarPorNombre(q)
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    /**
     * Retorna todos los productos ordenados por un campo y dirección dados.
     *
     * @param campo campo por el que ordenar: {@code nombre}, {@code precio} o {@code stock}
     *              (por defecto {@code nombre})
     * @param orden dirección: {@code asc} o {@code desc} (por defecto {@code asc})
     * @return {@code 200 OK} con la lista de productos ordenada
     */
    @GetMapping("/ordenados")
    public ResponseEntity<List<ProductoResponse>> listarOrdenados(
            @RequestParam(defaultValue = "nombre") String campo,
            @RequestParam(defaultValue = "asc") String orden) {
        List<ProductoResponse> response = productoService.listarOrdenados(campo, orden)
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    /**
     * Convierte una entidad {@link Producto} a su DTO de respuesta.
     *
     * @param producto la entidad a convertir
     * @return el DTO {@link ProductoResponse} correspondiente
     */
    private ProductoResponse toResponse(Producto producto) {
        CategoriaResponse categoriaResponse = new CategoriaResponse(
                producto.getCategoria().getId(),
                producto.getCategoria().getNombre(),
                producto.getCategoria().getDescripcion()
        );
        return new ProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                categoriaResponse
        );
    }
}