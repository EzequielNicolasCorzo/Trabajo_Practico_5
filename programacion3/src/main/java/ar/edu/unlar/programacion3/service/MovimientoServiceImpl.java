package ar.edu.unlar.programacion3.service;

import ar.edu.unlar.programacion3.dto.MovimientoRequest;
import ar.edu.unlar.programacion3.exception.InsufficientStockException;
import ar.edu.unlar.programacion3.model.MovimientoInventario;
import ar.edu.unlar.programacion3.model.Producto;
import ar.edu.unlar.programacion3.model.TipoMovimiento;
import ar.edu.unlar.programacion3.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementación del servicio de movimientos de inventario.
 *
 * <p>Valida las reglas de negocio para entradas y salidas de stock,
 * actualiza el stock del producto de forma atómica y persiste el movimiento.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
@Service
public class MovimientoServiceImpl implements MovimientoService {

    /** Repositorio de movimientos. */
    private final MovimientoRepository movimientoRepository;

    /** Servicio de productos para obtener y actualizar el stock. */
    private final ProductoService productoService;

    /**
     * Construye el servicio inyectando las dependencias necesarias.
     *
     * @param movimientoRepository repositorio de movimientos
     * @param productoService      servicio de productos
     */
    public MovimientoServiceImpl(MovimientoRepository movimientoRepository,
                                  ProductoService productoService) {
        this.movimientoRepository = movimientoRepository;
        this.productoService = productoService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovimientoInventario registrar(MovimientoRequest request) {
        Producto producto = productoService.findById(request.productoId());

        if (request.tipo() == TipoMovimiento.SALIDA) {
            if (producto.getStock() < request.cantidad()) {
                throw new InsufficientStockException(
                        producto.getId(),
                        request.cantidad(),
                        producto.getStock()
                );
            }
            producto.decrementarStock(request.cantidad());
        } else {
            producto.incrementarStock(request.cantidad());
        }

        int stockResultante = producto.getStock();

        MovimientoInventario movimiento = new MovimientoInventario(
                producto,
                request.tipo(),
                request.cantidad(),
                stockResultante,
                request.motivo(),
                LocalDateTime.now()
        );

        return movimientoRepository.save(movimiento);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MovimientoInventario> findByProductoId(Long productoId) {
        productoService.findById(productoId);
        return movimientoRepository.findByProductoId(productoId);
    }
}