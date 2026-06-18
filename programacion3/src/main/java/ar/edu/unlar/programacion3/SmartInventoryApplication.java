package ar.edu.unlar.programacion3;

import ar.edu.unlar.programacion3.config.StockConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Clase principal de la aplicación Smart Inventory.
 *
 * <p>Punto de entrada de la aplicación Spring Boot. Habilita la configuración
 * de propiedades mediante {@link StockConfig} para los umbrales de stock.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
@SpringBootApplication
@EnableConfigurationProperties(StockConfig.class)
public class SmartInventoryApplication {

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(SmartInventoryApplication.class, args);
    }
}