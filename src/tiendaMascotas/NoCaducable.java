package tiendaMascotas;

/**
 * Interfaz NoCaducable.
 * Marca productos que no tienen fecha de caducidad.
 */
public interface NoCaducable {
    /**
     * Indica que este producto no caduca.
     */
    default void mostrarInformacionDeCaducidad() {
        System.out.println("Este producto no tiene fecha de caducidad.");
    }
}
