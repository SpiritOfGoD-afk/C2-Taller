package tiendaMascotas;

import java.util.Calendar;

/**
 * Clase Main.
 * Ejecuta el programa de gestión del inventario de la tienda de mascotas.
 */
public class Main {
    public static void main(String[] args) {
        // Crear inventario
        Inventario inventario = new Inventario();
        
        // Crear juguetes
        Juguete juguete1 = new Juguete("Pelota para perros", 15.99, 5);
        Juguete juguete2 = new Juguete("Ratón de peluche", 9.50, 3);

        // Crear medicamentos (con fecha de caducidad)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 5); // Caduca en 5 días
        Medicamento medicamento1 = new Medicamento("Antiparasitario", "Ivermectina", 25.00, 2, calendar.getTime());

        calendar.add(Calendar.DAY_OF_MONTH, 15); // Caduca en 20 días
        Medicamento medicamento2 = new Medicamento("Antibiótico", "Amoxicilina", 30.00, 4, calendar.getTime());

        // Crear comida (con fecha de caducidad)
        calendar.add(Calendar.DAY_OF_MONTH, -10); // Cerca a vencer
        Comida comida1 = new Comida("Croquetas para gatos", 20.99, 10, calendar.getTime());

        calendar.add(Calendar.DAY_OF_MONTH, 20); // Caduca en 10 días
        Comida comida2 = new Comida("Alimento húmedo para perros", 12.50, 7, calendar.getTime());

        calendar.add(Calendar.DAY_OF_MONTH, -10); // Caduca en 5 días
        Comida comida3 = new Comida("Galletas para perros", 5.99, 6, calendar.getTime());

        // Agregar productos al inventario
        inventario.agregarProducto(juguete1);
        inventario.agregarProducto(juguete2);
        inventario.agregarProducto(medicamento1);
        inventario.agregarProducto(medicamento2);
        inventario.agregarProducto(comida1);
        inventario.agregarProducto(comida2);
        inventario.agregarProducto(comida3);
 
        // Mostrar cantidad de productos por categoría
        System.out.println("\nResumen de productos por categoría:");
        inventario.mostrarCantidadPorCategoria();

        // Mostrar inventario
        inventario.mostrarInventario();

        // Mostrar productos próximos a vencer
        inventario.mostrarProductosPorVencer();
    }
}