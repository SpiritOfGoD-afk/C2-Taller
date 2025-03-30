package tiendaMascotas;

import java.util.Date;

/**
 * Interfaz Caducable.
 * Define el comportamiento de productos que pueden caducar.
 */
public interface Caducable {
    /**
     * Obtiene la fecha de caducidad del producto.
     * @return Fecha de caducidad.
     */
    Date getFechaCaducidad();

    /**
     * Verifica si el producto está próximo a vencer.
     * @return true si está por vencer, false en caso contrario.
     */
    boolean estaPorVencer();
}
