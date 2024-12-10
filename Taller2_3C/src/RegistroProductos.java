import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RegistroProductos extends JFrame {
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private XML xmlManager;
    private ArrayList<Producto> listaProductos;

    public RegistroProductos() {
        xmlManager = new XML("productos.xml");
        listaProductos = xmlManager.leerProductos();

        setTitle("Registro de Productos");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tabla
        modeloTabla = new DefaultTableModel(new String[]{"Código", "Nombre", "Precio", "Categoría"}, 0);
        tablaProductos = new JTable(modeloTabla);
        cargarTabla();

        JScrollPane scrollPane = new JScrollPane(tablaProductos);

        // Botones
        JButton btnAgregar = new JButton("Agregar");
        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminar = new JButton("Eliminar");

        btnAgregar.addActionListener(e -> agregarProducto());
        btnModificar.addActionListener(e -> modificarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        for (Producto p : listaProductos) {
            modeloTabla.addRow(new Object[]{p.getCodigo(), p.getNombre(), p.getPrecio(), p.getCategoria()});
        }
    }

    private void agregarProducto() {
        Producto producto = FormularioProducto.mostrarDialogo(this, null);
        if (producto != null) {
            listaProductos.add(producto);
            xmlManager.guardarProductos(listaProductos);
            cargarTabla();
        }
    }

    private void modificarProducto() {
        int filaSeleccionada = tablaProductos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            Producto productoActual = listaProductos.get(filaSeleccionada);
            Producto productoModificado = FormularioProducto.mostrarDialogo(this, productoActual);

            if (productoModificado != null) {
                listaProductos.set(filaSeleccionada, productoModificado);
                xmlManager.guardarProductos(listaProductos);
                cargarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para modificar.");
        }
    }

    private void eliminarProducto() {
        int filaSeleccionada = tablaProductos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            listaProductos.remove(filaSeleccionada);
            xmlManager.guardarProductos(listaProductos);
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistroProductos().setVisible(true));
    }
}
