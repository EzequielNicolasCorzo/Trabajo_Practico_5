package ar.edu.unlar.programacion3.controller;

import ar.edu.unlar.programacion3.dto.CategoriaRequest;
import ar.edu.unlar.programacion3.dto.CategoriaResponse;
import ar.edu.unlar.programacion3.model.Categoria;
import ar.edu.unlar.programacion3.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controlador REST para la gestión de categorías.
 *
 * <p>Expone los endpoints CRUD bajo la ruta base {@code /api/categorias}.
 * Delega toda la lógica de negocio a {@link CategoriaService} y convierte
 * las entidades del modelo a DTOs antes de retornarlas al cliente.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    /** Servicio de categorías que contiene la lógica de negocio. */
    private final CategoriaService categoriaService;

    /**
     * Construye el controlador inyectando el servicio de categorías.
     *
     * @param categoriaService servicio de categorías
     */
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    /**
     * Retorna todas las categorías del sistema.
     *
     * @return {@code 200 OK} con la lista de categorías
     */
    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarTodas() {
        List<CategoriaResponse> response = categoriaService.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    /**
     * Retorna una categoría por su identificador único.
     *
     * @param id identificador de la categoría
     * @return {@code 200 OK} con la categoría encontrada,
     *         o {@code 404 Not Found} si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(toResponse(categoriaService.findById(id)));
    }

    /**
     * Crea una nueva categoría en el sistema.
     *
     * @param request datos de la categoría a crear (validados con {@code @Valid})
     * @return {@code 201 Created} con la categoría creada y header {@code Location}
     */
    @PostMapping
    public ResponseEntity<CategoriaResponse> crear(@Valid @RequestBody CategoriaRequest request) {
        Categoria categoria = categoriaService.crear(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri();
        return ResponseEntity.created(location).body(toResponse(categoria));
    }

    /**
     * Actualiza una categoría existente.
     *
     * @param id      identificador de la categoría a actualizar
     * @param request nuevos datos de la categoría (validados con {@code @Valid})
     * @return {@code 200 OK} con la categoría actualizada,
     *         o {@code 404 Not Found} si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequest request) {
        return ResponseEntity.ok(toResponse(categoriaService.actualizar(id, request)));
    }

    /**
     * Elimina una categoría del sistema.
     *
     * @param id identificador de la categoría a eliminar
     * @return {@code 204 No Content} si fue eliminada,
     *         {@code 404 Not Found} si no existe,
     *         {@code 409 Conflict} si tiene productos asociados
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Convierte una entidad {@link Categoria} a su DTO de respuesta.
     *
     * @param categoria la entidad a convertir
     * @return el DTO {@link CategoriaResponse} correspondiente
     */
    private CategoriaResponse toResponse(Categoria categoria) {
        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion()
        );
    }
}