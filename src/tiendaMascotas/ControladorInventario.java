package tiendaMascotas;

import java.time.LocalDate;

public class ControladorInventario {
    private Inventario modelo;
    private VentanaPrincipal vista;
    private boolean modoEdicion = false;
    private String nombreProductoOriginal = null;

    public ControladorInventario(Inventario modelo, VentanaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Cargar inventario al iniciar la aplicación
        modelo.cargarDesdeArchivo();

        System.out.println("Productos cargados:");
        for (Producto p : modelo.obtenerTodos()) {
            System.out.println(p.getNombre() + " - Cantidad: " + p.getCantidad());
        }

        // Listener para botón Agregar / Guardar Cambios
        this.vista.agregarListenerAgregar(_ -> {
            String nombre = vista.getNombre();
            int cantidad = vista.getCantidad();
            String tipo = vista.getTipoProducto();
            double precioCompra = vista.getPrecioCompra();
            double precioVenta = vista.getPrecioVenta();

            if (nombre.isEmpty() || cantidad <= 0 || precioCompra < 0 || precioVenta < 0) {
                vista.mostrarMensaje("Por favor ingresa todos los datos correctamente.");
                return;
            }

            LocalDate fechaVencimiento = calcularFechaVencimiento(tipo);
            Producto producto = new Producto(nombre, cantidad, tipo, fechaVencimiento, precioCompra, precioVenta);

            if (!modoEdicion) {
                // Modo agregar
                boolean agregado = modelo.agregarProducto(producto);
                modelo.guardarEnArchivo();

                if (agregado) {
                    vista.mostrarMensaje("Producto agregado exitosamente.");
                } else {
                    vista.mostrarMensaje("Producto ya existía. Cantidad acumulada.");
                }
            } else {
                // Modo edición (guardar cambios)
                boolean editado = modelo.editarProducto(nombreProductoOriginal, producto);
                if (editado) {
                    modelo.guardarEnArchivo();
                    vista.mostrarMensaje("Producto editado exitosamente.");
                    modoEdicion = false;
                    nombreProductoOriginal = null;
                    vista.setModoEdicion(false); // Cambia botón a "Agregar"
                } else {
                    vista.mostrarMensaje("No se pudo editar el producto.");
                    return;
                }
            }

            vista.actualizarInventario(modelo.obtenerTodos());
            vista.limpiarFormulario();
        });

        // Listener para eliminar producto
        this.vista.agregarListenerEliminar(_ -> {
            String nombreSeleccionado = vista.getProductoSeleccionado();
            if (nombreSeleccionado == null) {
                vista.mostrarMensaje("Por favor selecciona un producto en la tabla para eliminar.");
                return;
            }

            boolean eliminado = modelo.eliminarProducto(nombreSeleccionado);
            if (eliminado) {
                modelo.guardarEnArchivo();
                vista.mostrarMensaje("Producto eliminado exitosamente.");
                vista.actualizarInventario(modelo.obtenerTodos());
            } else {
                vista.mostrarMensaje("No se pudo eliminar el producto.");
            }
        });

        // Listener para iniciar edición: carga datos del producto en el formulario
        this.vista.agregarListenerEditar(_ -> {
            int filaSeleccionada = vista.getFilaSeleccionada();
            if (filaSeleccionada < 0) {
                vista.mostrarMensaje("Por favor selecciona un producto en la tabla para editar.");
                return;
            }

            String nombre = (String) vista.getValorTabla(filaSeleccionada, 0);
            String tipo = (String) vista.getValorTabla(filaSeleccionada, 1);
            int cantidad = (int) vista.getValorTabla(filaSeleccionada, 2);
            double precioCompra = (double) vista.getValorTabla(filaSeleccionada, 3);
            double precioVenta = (double) vista.getValorTabla(filaSeleccionada, 4);

            vista.cargarDatosEnFormulario(nombre, cantidad, tipo, precioCompra, precioVenta);

            nombreProductoOriginal = nombre;
            modoEdicion = true;
            vista.setModoEdicion(true); // Cambia texto botón a "Guardar Cambios"
        });

        // Inicializar tabla con datos
        vista.actualizarInventario(modelo.obtenerTodos());
    }

    private LocalDate calcularFechaVencimiento(String tipo) {
        LocalDate hoy = LocalDate.now();
        switch (tipo) {
            case "Alimento":
                return hoy.plusMonths(2);
            case "Medicamento":
                return hoy.plusMonths(10);
            case "Accesorio":
            default:
                return null;
        }
    }
}
