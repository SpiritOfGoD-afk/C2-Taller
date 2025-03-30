package tiendaMascotas;

/**
 * Clase Juguete.
 * Representa un juguete para mascotas.
 */
public class Juguete extends Producto {

    /**
     * Constructor de la clase Juguete.
     * @param nombre Nombre del juguete.
     * @param precio Precio del juguete.
     * @param cantidad Cantidad disponible del juguete.
     */
    public Juguete(String nombre, double precio, int cantidad) {
        super(nombre, precio, cantidad);
    }

    /**
     * Muestra la información del juguete.
     */
    @Override
    public void mostrarInformacion() {
        System.out.println("Juguete: " + nombre + " - Precio: $" + precio);
        System.out.println("Cantidad disponible: " + cantidad);
        System.out.println("Este producto no tiene fecha de caducidad.");
    }
}
