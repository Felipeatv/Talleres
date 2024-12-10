import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaProducto extends JFrame {
    private JTextField txtCodigo, txtNombre, txtPrecio, txtCategoria;
    private JButton btnGuardar, btnCargar;
    private JTable tablaProductos;

    private GestorArchivo gestorArchivo;
    private GestorTabla gestorTabla;

    public VentanaProducto() {
        gestorArchivo = new GestorArchivo("productos.txt");
        tablaProductos = new JTable();
        gestorTabla = new GestorTabla(tablaProductos);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Gestión de Productos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(20, 20, 80, 25);
        add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(100, 20, 150, 25);
        add(txtCodigo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 60, 80, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 60, 150, 25);
        add(txtNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(20, 100, 80, 25);
        add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(100, 100, 150, 25);
        add(txtPrecio);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(20, 140, 80, 25);
        add(lblCategoria);

        txtCategoria = new JTextField();
        txtCategoria.setBounds(100, 140, 150, 25);
        add(txtCategoria);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(20, 180, 100, 25);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });
        add(btnGuardar);

        btnCargar = new JButton("Cargar");
        btnCargar.setBounds(150, 180, 100, 25);
        btnCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarProductos();
            }
        });
        add(btnCargar);

        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        scrollPane.setBounds(300, 20, 250, 300);
        add(scrollPane);
    }

    private void guardarProducto() {
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        String categoria = txtCategoria.getText();

        Producto producto = new Producto(codigo, nombre, precio, categoria);
        gestorArchivo.guardarProducto(producto);

        JOptionPane.showMessageDialog(this, "Producto guardado con éxito.");
    }

    private void cargarProductos() {
        ArrayList<Producto> productos = gestorArchivo.leerProductos();
        gestorTabla.cargarProductosEnTabla(productos);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaProducto().setVisible(true));
    }
}
