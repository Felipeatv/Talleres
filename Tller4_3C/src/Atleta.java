import java.util.Random;

public class Atleta extends Thread {
    private String nombre;
    private int velocidad; // km/s
    private int distanciaRecorrida = 0;
    private static final int META = 10000;
    private static boolean carreraTerminada = false;

    public Atleta(String nombre) {
        this.nombre = nombre;
        this.velocidad = new Random().nextInt(1000) + 1; // Velocidad aleatoria entre 1 y 1000 km/s
    }

    public String getNombre() {
        return nombre;
    }

    public int getVelocidad() {
        return velocidad;
    }

    @Override
    public void run() {
        System.out.println(nombre + " comienza a correr con una velocidad de " + velocidad + " km/s.");
        while (distanciaRecorrida < META && !carreraTerminada) {
            distanciaRecorrida += velocidad;
            System.out.println(nombre + " ha recorrido " + distanciaRecorrida + " km.");
            try {
                Thread.sleep(1000); // Pausa de 1 segundo
            } catch (InterruptedException e) {
                System.out.println("El hilo fue interrumpido.");
            }
        }
        if (!carreraTerminada) {
            carreraTerminada = true; // Marca el final de la carrera
            System.out.println(nombre + " ha ganado la carrera recorriendo los " + META + " km.");
        }
    }
}
