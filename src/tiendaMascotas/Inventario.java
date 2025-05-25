package tiendaMascotas;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class Inventario {
    private Map<String, Producto> productos;
    private static final String ARCHIVO_CSV = "inventario.csv";
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ISO_LOCAL_DATE;

    public Inventario() {
        productos = new HashMap<>();
    }

    public boolean agregarProducto(Producto nuevoProducto) {
        String nombre = nuevoProducto.getNombre().toLowerCase();
        if (productos.containsKey(nombre)) {
            Producto existente = productos.get(nombre);
            existente.agregarCantidad(nuevoProducto.getCantidad());
            return false; // Ya existía
        } else {
            productos.put(nombre, nuevoProducto);
            return true; // Nuevo producto
        }
    }

    public boolean eliminarProducto(String nombre) {
        String nombreNormalizado = nombre.trim().toLowerCase();
        return productos.remove(nombreNormalizado) != null;
    }

    public Producto buscarProducto(String nombre) {
        return productos.get(nombre.trim().toLowerCase());
    }

    public List<Producto> obtenerTodos() {
        return new ArrayList<>(productos.values());
    }

    private LocalDate calcularFechaVencimiento(String tipo) {
        LocalDate hoy = LocalDate.now();
        switch (tipo.toLowerCase()) {
            case "alimento":
                return hoy.plusMonths(2);
            case "medicamento":
                return hoy.plusMonths(10);
            case "accesorio":
                return null; // No tiene vencimiento
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        if (productos.isEmpty()) {
            return "Inventario vacío.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Inventario actual:\n\n");
        for (Producto p : productos.values()) {
            sb.append("- Nombre: ").append(p.getNombre())
              .append("\n  Tipo: ").append(p.getTipo())
              .append("\n  Cantidad: ").append(p.getCantidad())
              .append("\n  Precio compra: $").append(p.getPrecioCompra())
              .append("\n  Precio venta: $").append(p.getPrecioVenta())
              .append("\n  Fecha vencimiento: ").append(
                  p.getFechaVencimiento() != null ? p.getFechaVencimiento() : "N/A"
              )
              .append("\n\n");
        }
        return sb.toString();
    }

    // Método para editar un producto existente
    public boolean editarProducto(String nombreOriginal, Producto productoActualizado) {
        String clave = nombreOriginal.trim().toLowerCase();
        if (productos.containsKey(clave)) {
            productos.put(clave, productoActualizado);
            return true;
        }
        return false;
    }

    public void guardarEnArchivo() {
        System.out.println("Guardando inventario en archivo...");
        System.out.println("Ruta absoluta archivo inventario.csv: " + new File(ARCHIVO_CSV).getAbsolutePath());
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_CSV))) {
            for (Producto p : productos.values()) {
                String fecha = p.getFechaVencimiento() != null ? p.getFechaVencimiento().format(FORMATO_FECHA) : "N/A";
                pw.printf(Locale.US, "%s,%d,%s,%.2f,%.2f,%s%n",
                    escapeCsv(p.getNombre()),
                    p.getCantidad(),
                    escapeCsv(p.getTipo()),
                    p.getPrecioCompra(),
                    p.getPrecioVenta(),
                    fecha);
            }
            System.out.println("Inventario guardado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar inventario: " + e.getMessage());
        }
    }

    public void cargarDesdeArchivo() {
        System.out.println("Intentando cargar inventario desde archivo...");
        System.out.println("Ruta absoluta archivo inventario.csv: " + new File(ARCHIVO_CSV).getAbsolutePath());
        productos.clear();
        File archivo = new File(ARCHIVO_CSV);
        if (!archivo.exists()) {
            System.out.println("Archivo inventario.csv no existe, cargando inventario vacío.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println("Leyendo línea: " + linea);
                String[] partes = linea.split(",", -1);
                if (partes.length != 6) continue;

                String nombre = partes[0];
                int cantidad = Integer.parseInt(partes[1]);
                String tipo = partes[2];
                double precioCompra = Double.parseDouble(partes[3]);
                double precioVenta = Double.parseDouble(partes[4]);
                LocalDate fechaVencimiento = partes[5].equals("N/A") ? null : LocalDate.parse(partes[5], FORMATO_FECHA);

                Producto p = new Producto(nombre, cantidad, tipo, fechaVencimiento, precioCompra, precioVenta);
                productos.put(nombre.toLowerCase(), p);
            }
            System.out.println("Inventario cargado correctamente desde archivo.");
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar inventario: " + e.getMessage());
        }
    }

    private String escapeCsv(String texto) {
        if (texto.contains(",") || texto.contains("\"")) {
            return "\"" + texto.replace("\"", "\"\"") + "\"";
        }
        return texto;
    }
}
