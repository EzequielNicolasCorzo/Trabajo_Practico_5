package ar.edu.unlar.programacion3.controller;

import ar.edu.unlar.programacion3.dto.AlertaStockResponse;
import ar.edu.unlar.programacion3.service.AlertaService;
import ar.edu.unlar.programacion3.service.PerformanceReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para alertas de stock y reporte de performance.
 *
 * <p>Expone el endpoint de alertas bajo {@code /api/alertas} y el reporte
 * administrativo bajo {@code /api/admin}. Delega toda la lógica
 * a {@link AlertaService} y {@link PerformanceReportService}.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
@RestController
public class AlertaController {

    /** Servicio de alertas de stock. */
    private final AlertaService alertaService;

    /** Servicio de reporte de performance. */
    private final PerformanceReportService performanceReportService;

    /**
     * Construye el controlador inyectando los servicios necesarios.
     *
     * @param alertaService            servicio de alertas
     * @param performanceReportService servicio de reporte de performance
     */
    public AlertaController(AlertaService alertaService,
                             PerformanceReportService performanceReportService) {
        this.alertaService = alertaService;
        this.performanceReportService = performanceReportService;
    }

    /**
     * Retorna todos los productos cuyo stock está por debajo del umbral mínimo.
     *
     * @return {@code 200 OK} con la lista de productos en alerta,
     *         lista vacía si todos tienen stock suficiente
     */
    @GetMapping("/api/alertas/stock-bajo")
    public ResponseEntity<List<AlertaStockResponse>> obtenerStockBajo() {
        return ResponseEntity.ok(alertaService.obtenerProductosEnAlerta());
    }

    /**
     * Genera y retorna el reporte de performance con tiempos de ejecución
     * medidos para datasets de 1k, 10k y 100k registros.
     *
     * @return {@code 200 OK} con el reporte JSON de complejidades y tiempos
     */
    @GetMapping("/api/admin/performance-report")
    public ResponseEntity<Map<String, Object>> performanceReport() {
        return ResponseEntity.ok(performanceReportService.generarReporte());
    }
}