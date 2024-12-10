import javax.swing.table.AbstractTableModel;
import java.util.List;

public class FacturaTableModel extends AbstractTableModel {
    private final List<Factura> facturas;
    private final String[] columnas = {"Código", "Nombre", "Cantidad", "Precio", "Impuesto", "Total"};

    public FacturaTableModel(List<Factura> facturas) {
        this.facturas = facturas;
    }

    @Override
    public int getRowCount() {
        return facturas.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Factura factura = facturas.get(rowIndex);
        switch (columnIndex) {
            case 0: return factura.getCodigoProducto();
            case 1: return factura.getNombreProducto();
            case 2: return factura.getCantidad();
            case 3: return factura.getPrecio();
            case 4: return factura.getImpuesto();
            case 5: return factura.getTotal();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    public void refresh() {
        fireTableDataChanged();
    }
}
