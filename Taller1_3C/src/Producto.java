public class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    private String categoria;

    public Producto(String codigo, String nombre, double precio, String categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public String toCSV() {
        return codigo + "," + nombre + "," + precio + "," + categoria;
    }

    public static Producto fromCSV(String linea) {
        String[] datos = linea.split(",");
        return new Producto(datos[0], datos[1], Double.parseDouble(datos[2]), datos[3]);
    }
}
