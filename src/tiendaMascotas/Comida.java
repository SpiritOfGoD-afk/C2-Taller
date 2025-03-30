package tiendaMascotas;

import java.util.Date;

/**
 * Clase Comida.
 * Representa comida para mascotas con fecha de caducidad.
 */
public class Comida extends Producto implements Caducable {
    private Date fechaCaducidad;

    /**
     * Constructor de la clase Comida.
     * @param nombre Nombre de la comida.
     * @param precio Precio de la comida.
     * @param fechaCaducidad Fecha de caducidad de la comida.
     * @param cantidad Cantidad de comida.
     */
    public Comida(String nombre, double precio, int cantidad, Date fechaCaducidad) {
        super(nombre, precio, cantidad);
        this.fechaCaducidad = fechaCaducidad;
    }

    /**
     * Obtiene la fecha de caducidad de la comida.
     * @return Fecha de caducidad.
     */
    @Override
    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    /**
     * Verifica si la comida está por vencer (menos de 10 días).
     * @return true si está por vencer, false en caso contrario.
     */
    @Override
    public boolean estaPorVencer() {
        long diferencia = fechaCaducidad.getTime() - new Date().getTime();
        return diferencia <= (10 * 24 * 60 * 60 * 1000); // 10 días en milisegundos
    }

    /**
     * Muestra la información de la comida.
     */
    @Override
    public void mostrarInformacion() {
        System.out.println("Comida: " + nombre + " - Precio: $" + precio);
        System.out.println("Cantidad disponible: " + cantidad);
        System.out.println("Fecha de caducidad: " + fechaCaducidad);

        if (estaPorVencer()) {
            System.out.println("Advertencia: Esta comida está próxima a vencer.");
            System.out.println("Se recomienda regalarla para evitar desperdicio.");
        }
    }
}
