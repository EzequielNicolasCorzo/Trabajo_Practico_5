package ar.edu.unlar.programacion3.config;

import ar.edu.unlar.programacion3.model.Categoria;
import ar.edu.unlar.programacion3.model.Producto;
import ar.edu.unlar.programacion3.repository.CategoriaRepository;
import ar.edu.unlar.programacion3.repository.ProductoRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Inicializador de datos de prueba que se ejecuta al arrancar la aplicación.
 *
 * <p>Implementa {@link ApplicationRunner} para cargar categorías y productos
 * de ejemplo en el almacenamiento en memoria, permitiendo probar la API
 * desde el primer arranque sin necesidad de cargar datos manualmente.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
@Component
public class DataInitializer implements ApplicationRunner {

    /** Repositorio de categorías para persistir las categorías iniciales. */
    private final CategoriaRepository categoriaRepository;

    /** Repositorio de productos para persistir los productos iniciales. */
    private final ProductoRepository productoRepository;

    /**
     * Construye el inicializador inyectando los repositorios necesarios.
     *
     * @param categoriaRepository repositorio de categorías
     * @param productoRepository  repositorio de productos
     */
    public DataInitializer(CategoriaRepository categoriaRepository,
                           ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }

    /**
     * Ejecuta la carga de datos iniciales al iniciar la aplicación.
     *
     * <p>Crea tres categorías y cinco productos de ejemplo con distintos
     * niveles de stock para poder observar el sistema de alertas desde el inicio.</p>
     *
     * @param args argumentos de la aplicación (no utilizados)
     */
    @Override
    public void run(ApplicationArguments args) {

        // --- Categorías ---
        Categoria electronica = categoriaRepository.save(
                new Categoria("Electrónicos", "Dispositivos y equipos electrónicos")
        );
        Categoria alimentos = categoriaRepository.save(
                new Categoria("Alimentos", "Productos alimenticios y bebidas")
        );
        Categoria limpieza = categoriaRepository.save(
                new Categoria("Limpieza", "Productos de limpieza e higiene")
        );

        // --- Productos con stock normal ---
        productoRepository.save(new Producto(
                "Notebook Dell XPS 15",
                "Laptop de alto rendimiento",
                1599.99, 25, electronica
        ));
        productoRepository.save(new Producto(
                "Monitor Samsung 27\"",
                "Monitor Full HD 27 pulgadas",
                399.99, 15, electronica
        ));

        // --- Producto con stock bajo (entre critico y minimo) ---
        productoRepository.save(new Producto(
                "Teclado Mecánico",
                "Teclado mecánico con switches Cherry MX",
                89.99, 7, electronica
        ));

        // --- Producto con stock crítico ---
        productoRepository.save(new Producto(
                "Arroz Integral 1kg",
                "Arroz integral orgánico",
                2.50, 2, alimentos
        ));

        // --- Producto con stock normal ---
        productoRepository.save(new Producto(
                "Detergente 500ml",
                "Detergente líquido multiuso",
                1.80, 50, limpieza
        ));
    }
}