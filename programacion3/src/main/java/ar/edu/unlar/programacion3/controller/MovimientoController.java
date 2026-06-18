package ar.edu.unlar.programacion3.controller;

import ar.edu.unlar.programacion3.dto.MovimientoRequest;
import ar.edu.unlar.programacion3.dto.MovimientoResponse;
import ar.edu.unlar.programacion3.model.MovimientoInventario;
import ar.edu.unlar.programacion3.service.MovimientoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controlador REST para el registro y consulta de movimientos de inventario.
 *
 * <p>Expone los endpoints bajo la ruta base {@code /api/movimientos}.
 * Delega toda la lógica de negocio a {@link MovimientoService}.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    /** Servicio de movimientos que contiene la lógica de negocio. */
    private final MovimientoService movimientoService;

    /**
     * Construye el controlador inyectando el servicio de movimientos.
     *
     * @param movimientoService servicio de movimientos
     */
    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    /**
     * Registra un nuevo movimiento de inventario (entrada o salida).
     *
     * @param request datos del movimiento a registrar (validados con {@code @Valid})
     * @return {@code 201 Created} con el movimiento registrado y header {@code Location},
     *         {@code 404 Not Found} si el producto no existe,
     *         {@code 409 Conflict} si es una salida con stock insuficiente
     */
    @PostMapping
    public ResponseEntity<MovimientoResponse> registrar(@Valid @RequestBody MovimientoRequest request) {
        MovimientoInventario movimiento = movimientoService.registrar(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(movimiento.getId())
                .toUri();
        return ResponseEntity.created(location).body(toResponse(movimiento));
    }

    /**
     * Retorna el historial de movimientos de un producto específico.
     *
     * @param id identificador del producto a consultar
     * @return {@code 200 OK} con la lista de movimientos del producto,
     *         o {@code 404 Not Found} si el producto no existe
     */
    @GetMapping("/producto/{id}")
    public ResponseEntity<List<MovimientoResponse>> obtenerPorProducto(@PathVariable Long id) {
        List<MovimientoResponse> response = movimientoService.findByProductoId(id)
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    /**
     * Convierte una entidad {@link MovimientoInventario} a su DTO de respuesta.
     *
     * @param movimiento la entidad a convertir
     * @return el DTO {@link MovimientoResponse} correspondiente
     */
    private MovimientoResponse toResponse(MovimientoInventario movimiento) {
        return new MovimientoResponse(
                movimiento.getId(),
                movimiento.getProducto().getId(),
                movimiento.getProducto().getNombre(),
                movimiento.getTipo(),
                movimiento.getCantidad(),
                movimiento.getStockResultante(),
                movimiento.getMotivo(),
                movimiento.getFecha()
        );
    }
}