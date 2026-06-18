package ar.edu.unlar.programacion3.exception;

/**
 * Excepción lanzada cuando no se encuentra una entidad solicitada por su ID.
 *
 * <p>Es manejada por {@link GlobalExceptionHandler} y resulta en una
 * respuesta HTTP {@code 404 Not Found}.</p>
 *
 * @author Grupo — Inventario Inteligente
 * @since 1.0
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Construye la excepción con un mensaje descriptivo.
     *
     * @param mensaje descripción del recurso no encontrado
     */
    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}