package ar.edu.unlar.programacion3.exception;

/**
 * Excepción lanzada cuando se viola una regla de negocio del sistema.
 *
 * <p>Ejemplos de uso:</p>
 * <ul>
 *   <li>Intentar eliminar una categoría que tiene productos asociados.</li>
 *   <li>Crear un producto con stock inicial negativo.</li>
 * </ul>
 *
 * <p>Es manejada por {@link GlobalExceptionHandler} y resulta en una
 * respuesta HTTP {@code 409 Conflict}.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public class BusinessRuleException extends RuntimeException {

    /**
     * Construye la excepción con un mensaje descriptivo de la regla violada.
     *
     * @param mensaje descripción de la regla de negocio que fue violada
     */
    public BusinessRuleException(String mensaje) {
        super(mensaje);
    }
}