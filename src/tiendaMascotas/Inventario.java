package tiendaMascotas;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Inventario.
 * Gestiona los productos en la tienda de mascotas.
 */
public class Inventario {
    private List<Producto> productos;

    /**
     * Constructor de Inventario.
     * Inicializa la lista de productos.
     */
    public Inventario() {
        this.productos = new ArrayList<>();
    }

    /**
     * Agrega un producto al inventario.
     * @param producto Producto a agregar.
     */
    public void agregarProducto(Producto nuevoProducto) {
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nuevoProducto.getNombre())) {
                producto.setCantidad(producto.getCantidad() + nuevoProducto.getCantidad());
                System.out.println("Cantidad actualizada para: " + producto.getNombre() + " - Nueva cantidad: " + producto.getCantidad());
                return;
            }
        }
        productos.add(nuevoProducto);
        System.out.println("Producto agregado: " + nuevoProducto.getNombre() + " - Cantidad: " + nuevoProducto.getCantidad());
    }
    
    /**
     * Muestra todos los productos del inventario.
     */
    public void mostrarInventario() {
        System.out.println("\nInventario de la tienda:");
        for (Producto producto : productos) {
            producto.mostrarInformacion();
            System.out.println("----------------------------");
        }
    }

    public void mostrarCantidadPorCategoria() {
        int cantidadJuguetes = 0;
        int cantidadComida = 0;
        int cantidadMedicamentos = 0;
    
        for (Producto producto : productos) {
            if (producto instanceof Juguete) {
                cantidadJuguetes += producto.getCantidad();
            } else if (producto instanceof Comida) {
                cantidadComida += producto.getCantidad();
            } else if (producto instanceof Medicamento) {
                cantidadMedicamentos += producto.getCantidad();
            }
        }
    
        System.out.println("\nCantidad de productos en inventario:");
        System.out.println("Juguetes: " + cantidadJuguetes);
        System.out.println("Comida: " + cantidadComida);
        System.out.println("Medicamentos: " + cantidadMedicamentos);
        System.out.println("----------------------------");
    }
    
    /**
     * Muestra productos que están por vencer.
     */
    public void mostrarProductosPorVencer() {
        System.out.println("\nProductos próximos a vencer:");
        boolean hayProductosPorVencer = false;

        for (Producto producto : productos) {
            if (producto instanceof Caducable) {
                Caducable caducable = (Caducable) producto;
                if (caducable.estaPorVencer()) {
                    producto.mostrarInformacion();
                    System.out.println("----------------------------");
                    hayProductosPorVencer = true;
                }
            }
        }

        if (!hayProductosPorVencer) {
            System.out.println("No hay productos próximos a vencer.");
        }
    }
}
