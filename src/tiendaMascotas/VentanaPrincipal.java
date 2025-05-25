package tiendaMascotas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JTextField tfNombre, tfCantidad, tfPrecioCompra, tfPrecioVenta;
    private JComboBox<String> cbTipoProducto;
    private JButton btnAgregar, btnEliminar, btnEditar;
    private JTable tablaInventario;
    private DefaultTableModel modeloTabla;

    public VentanaPrincipal() {
        setTitle("Inventario Tienda de Mascotas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(7, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelFormulario.add(new JLabel("Nombre:"));
        tfNombre = new JTextField();
        panelFormulario.add(tfNombre);

        panelFormulario.add(new JLabel("Cantidad:"));
        tfCantidad = new JTextField();
        panelFormulario.add(tfCantidad);

        panelFormulario.add(new JLabel("Tipo de Producto:"));
        cbTipoProducto = new JComboBox<>(new String[] {"Alimento", "Accesorio", "Medicamento"});
        panelFormulario.add(cbTipoProducto);

        panelFormulario.add(new JLabel("Precio Compra:"));
        tfPrecioCompra = new JTextField();
        panelFormulario.add(tfPrecioCompra);

        panelFormulario.add(new JLabel("Precio Venta:"));
        tfPrecioVenta = new JTextField();
        panelFormulario.add(tfPrecioVenta);

        btnAgregar = new JButton("Agregar");
        panelFormulario.add(btnAgregar);

        btnEliminar = new JButton("Eliminar");
        panelFormulario.add(btnEliminar);

        btnEditar = new JButton("Editar");
        panelFormulario.add(btnEditar);

        add(panelFormulario, BorderLayout.NORTH);

        String[] columnas = {"Nombre", "Tipo", "Cantidad", "Precio Compra", "Precio Venta", "Fecha Vencimiento"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaInventario = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaInventario);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void agregarListenerAgregar(ActionListener listener) {
        btnAgregar.addActionListener(listener);
    }

    public void agregarListenerEliminar(ActionListener listener) {
        btnEliminar.addActionListener(listener);
    }

    public void agregarListenerEditar(ActionListener listener) {
        btnEditar.addActionListener(listener);
    }

    public String getNombre() {
        return tfNombre.getText().trim();
    }

    public int getCantidad() {
        try {
            return Integer.parseInt(tfCantidad.getText().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String getTipoProducto() {
        return (String) cbTipoProducto.getSelectedItem();
    }

    public double getPrecioCompra() {
        try {
            return Double.parseDouble(tfPrecioCompra.getText().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public double getPrecioVenta() {
        try {
            return Double.parseDouble(tfPrecioVenta.getText().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public int getFilaSeleccionada() {
        return tablaInventario.getSelectedRow();
    }

    public Object getValorTabla(int fila, int columna) {
        return modeloTabla.getValueAt(fila, columna);
    }


    public void setModoEdicion(boolean editar) {
    if (editar) {
            btnAgregar.setText("Guardar Cambios");
    } else {
            btnAgregar.setText("Agregar");
        }
    }


    public void cargarDatosEnFormulario(String nombre, int cantidad, String tipo, double precioCompra, double precioVenta) {
        tfNombre.setText(nombre);
        tfCantidad.setText(String.valueOf(cantidad));
        cbTipoProducto.setSelectedItem(tipo);
        tfPrecioCompra.setText(String.valueOf(precioCompra));
        tfPrecioVenta.setText(String.valueOf(precioVenta));
    }

    public void actualizarInventario(java.util.List<Producto> productos) {
        modeloTabla.setRowCount(0);
        for (Producto p : productos) {
            Object[] fila = {
                p.getNombre(),
                p.getTipo(),
                p.getCantidad(),
                p.getPrecioCompra(),
                p.getPrecioVenta(),
                p.getFechaVencimiento() != null ? p.getFechaVencimiento().toString() : "N/A"
            };
            modeloTabla.addRow(fila);
        }
    }

    public void limpiarFormulario() {
        tfNombre.setText("");
        tfCantidad.setText("");
        tfPrecioCompra.setText("");
        tfPrecioVenta.setText("");
        cbTipoProducto.setSelectedIndex(0);
    }

    public String getProductoSeleccionado() {
        int fila = tablaInventario.getSelectedRow();
        if (fila >= 0) {
            return (String) modeloTabla.getValueAt(fila, 0);
        }
        return null;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
