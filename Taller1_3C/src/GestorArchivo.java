import java.io.*;
import java.util.ArrayList;

public class GestorArchivo {
    private String rutaArchivo;

    public GestorArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardarProducto(Producto producto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            bw.write(producto.toCSV());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar el producto: " + e.getMessage());
        }
    }

    public ArrayList<Producto> leerProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                productos.add(Producto.fromCSV(linea));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return productos;
    }
}
