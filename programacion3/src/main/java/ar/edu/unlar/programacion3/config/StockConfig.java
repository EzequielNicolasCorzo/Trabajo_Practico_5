package ar.edu.unlar.programacion3.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * Configuración de umbrales de stock leída desde {@code application.yml}.
 *
 * <p>Si no se definen valores en el archivo de configuración, se aplican
 * los valores por defecto: {@code minimo = 10} y {@code critico = 3}.</p>
 *
 * <p>Un producto cuyo stock sea menor al {@code minimo} se considera en alerta
 * {@code BAJO}. Si además es menor al {@code critico}, la alerta es {@code CRITICO}.</p>
 *
 * @param minimo  umbral mínimo de stock; por debajo → alerta BAJO (default: 10)
 * @param critico umbral crítico de stock; por debajo → alerta CRITICO (default: 3)
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
@ConfigurationProperties(prefix = "inventario.stock")
public record StockConfig(
        @DefaultValue("10") int minimo,
        @DefaultValue("3")  int critico
) {}