import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FacturaManager facturaManager = new FacturaManager();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Listar Facturas");
            System.out.println("2. Agregar Factura");
            System.out.println("3. Modificar Factura");
            System.out.println("4. Eliminar Factura");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir la nueva línea después de leer el entero

            switch (opcion) {
                case 1:
                    listarFacturas(facturaManager);
                    break;
                case 2:
                    agregarFactura(facturaManager, scanner);
                    break;
                case 3:
                    modificarFactura(facturaManager, scanner);
                    break;
                case 4:
                    eliminarFactura(facturaManager, scanner);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (opcion != 5);

        scanner.close();
    }

    // Listar todas las facturas
    private static void listarFacturas(FacturaManager facturaManager) {
        List<Factura> facturas = facturaManager.getFacturas();
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas registradas.");
        } else {
            System.out.println("\nListado de Facturas:");
            for (int i = 0; i < facturas.size(); i++) {
                Factura factura = facturas.get(i);
                System.out.println((i + 1) + ". " + factura);
            }
        }
    }

    // Agregar una nueva factura
    private static void agregarFactura(FacturaManager facturaManager, Scanner scanner) {
        System.out.println("Agregar Factura:");
        System.out.print("Código de Producto: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre del Producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Impuesto: ");
        double impuesto = scanner.nextDouble();

        Factura nuevaFactura = new Factura(codigo, nombre, cantidad, precio, impuesto);
        facturaManager.agregarFactura(nuevaFactura);
        System.out.println("Factura agregada exitosamente.");
    }

    // Modificar una factura existente
    private static void modificarFactura(FacturaManager facturaManager, Scanner scanner) {
        System.out.println("Modificar Factura:");
        listarFacturas(facturaManager);
        System.out.print("Ingrese el número de la factura a modificar: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();  // Consumir la nueva línea

        if (index >= 0 && index < facturaManager.getFacturas().size()) {
            Factura factura = facturaManager.getFacturas().get(index);

            System.out.print("Nuevo Código de Producto (actual: " + factura.getCodigo() + "): ");
            String codigo = scanner.nextLine();
            System.out.print("Nuevo Nombre del Producto (actual: " + factura.getNombre() + "): ");
            String nombre = scanner.nextLine();
            System.out.print("Nueva Cantidad (actual: " + factura.getCantidad() + "): ");
            int cantidad = scanner.nextInt();
            System.out.print("Nuevo Precio (actual: " + factura.getPrecio() + "): ");
            double precio = scanner.nextDouble();
            System.out.print("Nuevo Impuesto (actual: " + factura.getImpuesto() + "): ");
            double impuesto = scanner.nextDouble();

            Factura facturaModificada = new Factura(codigo, nombre, cantidad, precio, impuesto);
            facturaManager.modificarFactura(index, facturaModificada);
            System.out.println("Factura modificada exitosamente.");
        } else {
            System.out.println("Factura no encontrada.");
        }
    }

    // Eliminar una factura
    private static void eliminarFactura(FacturaManager facturaManager, Scanner scanner) {
        System.out.println("Eliminar Factura:");
        listarFacturas(facturaManager);
        System.out.print("Ingrese el número de la factura a eliminar: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < facturaManager.getFacturas().size()) {
            facturaManager.eliminarFactura(index);
            System.out.println("Factura eliminada exitosamente.");
        } else {
            System.out.println("Factura no encontrada.");
        }
    }
}
