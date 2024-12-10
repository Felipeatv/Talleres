import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FacturaManager {
    private static final String FILE_PATH = "facturas.json";
    private List<Factura> facturas;
    private Gson gson;

    public FacturaManager() {
        gson = new Gson();
        facturas = cargarFacturas();
    }

    // Cargar facturas desde JSON
    private List<Factura> cargarFacturas() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Factura>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Guardar facturas en JSON
    public void guardarFacturas() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(facturas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void agregarFactura(Factura factura) {
        facturas.add(factura);
        guardarFacturas();
    }

    public void modificarFactura(int index, Factura factura) {
        facturas.set(index, factura);
        guardarFacturas();
    }

    public void eliminarFactura(int index) {
        facturas.remove(index);
        guardarFacturas();
    }
}
