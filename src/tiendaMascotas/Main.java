package tiendaMascotas;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Inventario modelo = new Inventario();
            VentanaPrincipal vista = new VentanaPrincipal();
            new ControladorInventario(modelo, vista);
            vista.setVisible(true);
        });
    }
}
