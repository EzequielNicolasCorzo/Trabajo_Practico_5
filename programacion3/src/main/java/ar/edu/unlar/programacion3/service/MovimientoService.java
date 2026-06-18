package ar.edu.unlar.programacion3.service;

import ar.edu.unlar.programacion3.dto.MovimientoRequest;
import ar.edu.unlar.programacion3.exception.InsufficientStockException;
import ar.edu.unlar.programacion3.exception.ResourceNotFoundException;
import ar.edu.unlar.programacion3.model.MovimientoInventario;

import java.util.List;

/**
 * Contrato del servicio de movimientos de inventario.
 *
 * <p>Define las operaciones para registrar entradas y salidas de stock
 * y consultar el historial de movimientos por producto.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public interface MovimientoService {

    /**
     * Registra un nuevo movimiento de inventario (entrada o salida).
     *
     * @param request datos del movimiento a registrar
     * @return el movimiento registrado con su ID asignado
     * @throws ResourceNotFoundException  si no existe el producto indicado
     * @throws InsufficientStockException si es una salida y el stock es insuficiente
     */
    MovimientoInventario registrar(MovimientoRequest request);

    /**
     * Retorna el historial de movimientos de un producto específico.
     *
     * @param productoId identificador del producto a consultar
     * @return lista de movimientos del producto; lista vacía si no hay ninguno
     * @throws ResourceNotFoundException si no existe el producto indicado
     */
    List<MovimientoInventario> findByProductoId(Long productoId);
}