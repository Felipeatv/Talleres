import javax.swing.*;  // Para componentes de la interfaz gráfica como JFrame, JLabel, JTextField, JButton, etc.
import java.awt.*;     // Para el diseño del formulario (GridLayout y otros layouts).
import java.awt.event.*; // Para manejar eventos, como clics de botones.

public class FormularioProducto extends JDialog {
    private JTextField txtCodigo, txtNombre, txtPrecio, txtCategoria;
    private boolean confirmado = false;
    private Producto producto;

    public FormularioProducto(JFrame owner, Producto producto) {
        super(owner, "Formulario de Producto", true);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        add(txtPrecio);

        add(new JLabel("Categoría:"));
        txtCategoria = new JTextField();
        add(txtCategoria);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> {
            confirmado = true;
            dispose();
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());

        add(btnAceptar);
        add(btnCancelar);

        if (producto != null) {
            txtCodigo.setText(producto.getCodigo());
            txtNombre.setText(producto.getNombre());
            txtPrecio.setText(String.valueOf(producto.getPrecio()));
            txtCategoria.setText(producto.getCategoria());
            this.producto = producto;
        }

        pack();
        setLocationRelativeTo(owner);
    }

    public static Producto mostrarDialogo(JFrame owner, Producto producto) {
        FormularioProducto dialogo = new FormularioProducto(owner, producto);
        dialogo.setVisible(true);

        if (dialogo.confirmado) {
            return new Producto(
                    dialogo.txtCodigo.getText(),
                    dialogo.txtNombre.getText(),
                    Double.parseDouble(dialogo.txtPrecio.getText()),
                    dialogo.txtCategoria.getText()
            );
        }

        return null;
    }
}
