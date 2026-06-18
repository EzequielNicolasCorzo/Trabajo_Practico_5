package ar.edu.unlar.programacion3.service;

import ar.edu.unlar.programacion3.model.Categoria;
import ar.edu.unlar.programacion3.model.Producto;
import ar.edu.unlar.programacion3.repository.ProductoRepository;
import ar.edu.unlar.programacion3.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Servicio que genera un reporte de performance con los tiempos de ejecución
 * reales de las operaciones principales del sistema.
 *
 * <p>Mide con {@link System#nanoTime()} el tiempo de cada operación
 * para datasets de 1k, 10k y 100k registros, permitiendo validar
 * empíricamente las complejidades Big O teóricas.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
@Service
public class PerformanceReportService {

    /** Repositorio de productos utilizado para las mediciones. */
    private final ProductoRepository productoRepository;

    /** Repositorio de categorías utilizado para poblar datos de prueba. */
    private final CategoriaRepository categoriaRepository;

    /**
     * Construye el servicio inyectando los repositorios necesarios.
     *
     * @param productoRepository  repositorio de productos
     * @param categoriaRepository repositorio de categorías
     */
    public PerformanceReportService(ProductoRepository productoRepository,
                                     CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * Genera el reporte completo de performance para los tamaños de dataset
     * 1.000, 10.000 y 100.000 registros.
     *
     * <p>Para cada tamaño carga los datos, ejecuta cada operación, mide
     * el tiempo en nanosegundos y limpia los datos antes del siguiente ciclo.</p>
     *
     * @return mapa con los resultados organizados por tamaño de dataset y operación
     */
    public Map<String, Object> generarReporte() {
        Map<String, Object> reporte = new HashMap<>();

        int[] tamanios = {1_000, 10_000, 100_000};

        for (int n : tamanios) {
            reporte.put("dataset_" + n, medirOperaciones(n));
        }

        return reporte;
    }

    /**
     * Carga {@code n} productos de prueba, mide cada operación y limpia los datos.
     *
     * @param n cantidad de registros a utilizar en la medición
     * @return mapa con el tiempo en nanosegundos de cada operación medida
     */
    private Map<String, Object> medirOperaciones(int n) {
        cargarDatos(n);

        Map<String, Object> mediciones = new HashMap<>();

        // findAll → O(n)
        long inicio = System.nanoTime();
        productoRepository.findAll();
        mediciones.put("findAll_ns", System.nanoTime() - inicio);

        // findById → O(1)
        inicio = System.nanoTime();
        productoRepository.findById(1L);
        mediciones.put("findById_ns", System.nanoTime() - inicio);

        // save (insert) → O(1)
        Categoria cat = categoriaRepository.findById(1L).orElseThrow();
        Producto nuevo = new Producto("Test", "Test", 1.0, 10, cat);
        inicio = System.nanoTime();
        productoRepository.save(nuevo);
        mediciones.put("save_ns", System.nanoTime() - inicio);

        // buscarPorNombre → O(n·m)
        inicio = System.nanoTime();
        productoRepository.buscarPorNombre("Producto");
        mediciones.put("buscarPorNombre_ns", System.nanoTime() - inicio);

        // sort → O(n log n)
        inicio = System.nanoTime();
        productoRepository.findAll().sort(
                java.util.Comparator.comparing(Producto::getNombre)
        );
        mediciones.put("sort_ns", System.nanoTime() - inicio);

        limpiarDatos();

        return mediciones;
    }

    /**
     * Carga {@code n} productos de prueba en el repositorio.
     *
     * <p>Crea una categoría base si no existe y genera productos con nombres
     * y precios variados para simular un dataset realista.</p>
     *
     * @param n cantidad de productos a cargar
     */
    private void cargarDatos(int n) {
        Categoria categoria = categoriaRepository.save(
                new Categoria("Performance Test", "Categoría para pruebas")
        );
        for (int i = 0; i < n; i++) {
            productoRepository.save(new Producto(
                    "Producto " + i,
                    "Descripción " + i,
                    i * 1.5,
                    i + 1,
                    categoria
            ));
        }
    }

    /**
     * Elimina todos los productos y categorías cargados durante las mediciones.
     *
     * <p>Itera sobre todas las entidades y las elimina una a una para
     * dejar el repositorio en su estado previo.</p>
     */
    private void limpiarDatos() {
        productoRepository.findAll()
                .forEach(p -> productoRepository.deleteById(p.getId()));
        categoriaRepository.findAll()
                .forEach(c -> categoriaRepository.deleteById(c.getId()));
    }
}