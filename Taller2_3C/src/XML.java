import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
public class XML {
    private String rutaArchivo;

    public XML(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public ArrayList<Producto> leerProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        try {
            File archivo = new File(rutaArchivo);
            if (!archivo.exists()) return productos;

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(archivo);

            NodeList listaProductos = doc.getElementsByTagName("producto");

            for (int i = 0; i < listaProductos.getLength(); i++) {
                Node nodo = listaProductos.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;

                    String codigo = elemento.getElementsByTagName("codigo").item(0).getTextContent();
                    String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                    double precio = Double.parseDouble(elemento.getElementsByTagName("precio").item(0).getTextContent());
                    String categoria = elemento.getElementsByTagName("categoria").item(0).getTextContent();

                    productos.add(new Producto(codigo, nombre, precio, categoria));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }

    public void guardarProductos(ArrayList<Producto> productos) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element raiz = doc.createElement("productos");
            doc.appendChild(raiz);

            for (Producto producto : productos) {
                Element productoElem = doc.createElement("producto");

                Element codigo = doc.createElement("codigo");
                codigo.appendChild(doc.createTextNode(producto.getCodigo()));

                Element nombre = doc.createElement("nombre");
                nombre.appendChild(doc.createTextNode(producto.getNombre()));

                Element precio = doc.createElement("precio");
                precio.appendChild(doc.createTextNode(String.valueOf(producto.getPrecio())));

                Element categoria = doc.createElement("categoria");
                categoria.appendChild(doc.createTextNode(producto.getCategoria()));

                productoElem.appendChild(codigo);
                productoElem.appendChild(nombre);
                productoElem.appendChild(precio);
                productoElem.appendChild(categoria);

                raiz.appendChild(productoElem);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(rutaArchivo));

            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
