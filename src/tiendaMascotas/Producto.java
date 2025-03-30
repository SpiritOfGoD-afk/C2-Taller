package tiendaMascotas;

/**
 * Clase abstracta Producto.
 * Representa un producto genérico en la tienda de mascotas.
 */
public abstract class Producto {
    protected String nombre;
    protected double precio;
    protected int cantidad;

    /**
     * Constructor de Producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param cantidad Cantidad disponible del producto.
     */
    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Métodos Get y Set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Método abstracto que será implementado en las clases hijas.
     */
    public abstract void mostrarInformacion();
}
