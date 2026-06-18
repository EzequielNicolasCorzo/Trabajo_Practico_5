package ar.edu.unlar.programacion3.service;

import ar.edu.unlar.programacion3.config.StockConfig;
import ar.edu.unlar.programacion3.dto.AlertaStockResponse;
import ar.edu.unlar.programacion3.model.NivelAlerta;
import ar.edu.unlar.programacion3.model.Producto;
import ar.edu.unlar.programacion3.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio de alertas de stock.
 *
 * <p>Aplica el patrón Strategy mediante {@link StockConfig} para determinar
 * el nivel de alerta de cada producto según los umbrales configurados,
 * permitiendo modificar el comportamiento sin alterar el código fuente (OCP).</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
@Service
public class AlertaServiceImpl implements AlertaService {

    /** Repositorio de productos para consultar el stock actual. */
    private final ProductoRepository productoRepository;

    /** Configuración de umbrales de stock mínimo y crítico. */
    private final StockConfig stockConfig;

    /**
     * Construye el servicio inyectando las dependencias necesarias.
     *
     * @param productoRepository repositorio de productos
     * @param stockConfig        configuración de umbrales de stock
     */
    public AlertaServiceImpl(ProductoRepository productoRepository,
                              StockConfig stockConfig) {
        this.productoRepository = productoRepository;
        this.stockConfig = stockConfig;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AlertaStockResponse> obtenerProductosEnAlerta() {
        return productoRepository.findAll().stream()
                .filter(p -> p.getStock() < stockConfig.minimo())
                .map(this::construirAlerta)
                .toList();
    }

    /**
     * Construye una {@link AlertaStockResponse} para un producto dado,
     * determinando su nivel de alerta según los umbrales configurados.
     *
     * @param producto el producto a evaluar
     * @return la respuesta de alerta con el nivel calculado
     */
    private AlertaStockResponse construirAlerta(Producto producto) {
        NivelAlerta nivel = producto.getStock() < stockConfig.critico()
                ? NivelAlerta.CRITICO
                : NivelAlerta.BAJO;

        return new AlertaStockResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getStock(),
                nivel,
                producto.getCategoria().getNombre()
        );
    }
}