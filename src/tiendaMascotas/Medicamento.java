package tiendaMascotas;

import java.util.Date;

/**
 * Clase Medicamento.
 * Representa un medicamento para mascotas con fecha de caducidad.
 */
public class Medicamento extends Producto implements Caducable {
    private String nombreProfesional;
    private Date fechaCaducidad;

    /**
     * Constructor de la clase Medicamento.
     * @param nombre Nombre comercial del medicamento.
     * @param nombreProfesional Nombre profesional (componente activo).
     * @param precio Precio del medicamento.
     * @param cantidad cantidad del medicamento.
     * @param fechaCaducidad Fecha de caducidad del medicamento.
     */
    public Medicamento(String nombre, String nombreProfesional, double precio, int cantidad, Date fechaCaducidad) {
        super(nombre, precio, cantidad);
        this.nombreProfesional = nombreProfesional;
        this.fechaCaducidad = fechaCaducidad;
    }

    /**
     * Obtiene el nombre profesional del medicamento.
     * @return Nombre profesional.
     */
    public String getNombreProfesional() {
        return nombreProfesional;
    }

    public void setNombreProfesional(String nombreProfesional) {
        this.nombreProfesional = nombreProfesional;
    }

    /**
     * Obtiene la fecha de caducidad del medicamento.
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
     * Verifica si el medicamento está por vencer (menos de 7 días).
     * @return true si está por vencer, false en caso contrario.
     */
    @Override
    public boolean estaPorVencer() {
        long diferencia = fechaCaducidad.getTime() - new Date().getTime();
        return diferencia <= (7 * 24 * 60 * 60 * 1000); // 7 días en milisegundos
    }

    /**
     * Muestra la información del medicamento.
     */
    @Override
    public void mostrarInformacion() {
        System.out.println("Medicamento: " + nombre + " - Precio: $" + precio);
        System.out.println("Cantidad disponible: " + cantidad);
        System.out.println("Componente activo: " + nombreProfesional);
        System.out.println("Fecha de caducidad: " + fechaCaducidad);

        if (estaPorVencer()) {
            System.out.println("Advertencia: Este medicamento está próximo a vencer.");
        }
    }
}
