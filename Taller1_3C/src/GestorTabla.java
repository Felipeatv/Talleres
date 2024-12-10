import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class GestorTabla {
    private JTable tabla;

    public GestorTabla(JTable tabla) {
        this.tabla = tabla;
        configurarTabla();
    }

    private void configurarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"Código", "Nombre", "Precio", "Categoría"}, 0
        );
        tabla.setModel(modelo);
    }

    public void cargarProductosEnTabla(ArrayList<Producto> productos) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0); // Limpiar tabla

        for (Producto producto : productos) {
            modelo.addRow(new Object[]{
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getCategoria()
            });
        }
    }
}
