import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FacturForm extends JFrame {
    private JTextField txtCodigo, txtNombre, txtCantidad, txtPrecio, txtImpuesto;
    private JButton btnGuardar;

    public FacturForm(FacturaManager manager, JTable table, boolean isEdit, int rowIndex) {
        setTitle(isEdit ? "Modificar Factura" : "Agregar Factura");
        setLayout(null);
        setSize(300, 400);

        JLabel lblCodigo = new JLabel("Código Producto:");
        lblCodigo.setBounds(20, 20, 120, 25);
        add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(150, 20, 120, 25);
        add(txtCodigo);

        JLabel lblNombre = new JLabel("Nombre Producto:");
        lblNombre.setBounds(20, 60, 120, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 60, 120, 25);
        add(txtNombre);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(20, 100, 120, 25);
        add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(150, 100, 120, 25);
        add(txtCantidad);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(20, 140, 120, 25);
        add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(150, 140, 120, 25);
        add(txtPrecio);

        JLabel lblImpuesto = new JLabel("Impuesto:");
        lblImpuesto.setBounds(20, 180, 120, 25);
        add(lblImpuesto);

        txtImpuesto = new JTextField();
        txtImpuesto.setBounds(150, 180, 120, 25);
        add(txtImpuesto);

        btnGuardar = new JButton(isEdit ? "Modificar" : "Guardar");
        btnGuardar.setBounds(90, 250, 100, 30);
        add(btnGuardar);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = txtCodigo.getText();
                String nombre = txtNombre.getText();
                int cantidad = Integer.parseInt(txtCantidad.getText());
                double precio = Double.parseDouble(txtPrecio.getText());
                double impuesto = Double.parseDouble(txtImpuesto.getText());

                Factura factura = new Factura(codigo, nombre, cantidad, precio, impuesto);
                if (isEdit) {
                    manager.modificarFactura(rowIndex, factura);
                } else {
                    manager.agregarFactura(factura);
                }

                ((FacturaTableModel) table.getModel()).refresh();
                dispose();
            }
        });

        if (isEdit) {
            Factura factura = manager.getFacturas().get(rowIndex);
            txtCodigo.setText(factura.getCodigoProducto());
            txtNombre.setText(factura.getNombreProducto());
            txtCantidad.setText(String.valueOf(factura.getCantidad()));
            txtPrecio.setText(String.valueOf(factura.getPrecio()));
            txtImpuesto.setText(String.valueOf(factura.getImpuesto()));
        }

        setVisible(true);
    }
}

