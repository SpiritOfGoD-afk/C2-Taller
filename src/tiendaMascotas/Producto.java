package tiendaMascotas;

import java.time.LocalDate;

public class Producto {
    private String nombre;
    private int cantidad;
    private String tipo;
    private double precioCompra;
    private double precioVenta;
    private LocalDate fechaVencimiento;

    public Producto(String nombre, int cantidad, String tipo, LocalDate fechaVencimiento, double precioCompra, double precioVenta) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.fechaVencimiento = fechaVencimiento;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void agregarCantidad(int extra) {
        this.cantidad += extra;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    // Método para convertir un Producto en línea CSV
    public String toCSV() {
        // si fechaVencimiento es null, guardamos "N/A"
        String fechaStr = (fechaVencimiento != null) ? fechaVencimiento.toString() : "N/A";
        return String.format("%s,%d,%s,%.2f,%.2f,%s",
            nombre, cantidad, tipo, precioCompra, precioVenta, fechaStr);
    }

    // Método estático para crear Producto desde una línea CSV
    public static Producto fromCSV(String linea) {
        String[] partes = linea.split(",", -1);
        if (partes.length != 6) {
            throw new IllegalArgumentException("Línea CSV inválida para Producto: " + linea);
        }

        String nombre = partes[0];
        int cantidad = Integer.parseInt(partes[1]);
        String tipo = partes[2];
        double precioCompra = Double.parseDouble(partes[3]);
        double precioVenta = Double.parseDouble(partes[4]);

        LocalDate fechaVencimiento = null;
        if (!partes[5].equals("N/A")) {
            fechaVencimiento = LocalDate.parse(partes[5]);
        }

        return new Producto(nombre, cantidad, tipo, fechaVencimiento, precioCompra, precioVenta);
    }
}
