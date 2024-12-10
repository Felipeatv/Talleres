public class Carrera {
    public void iniciarCarrera() {
        Atleta[] atletas = new Atleta[5];
        String[] nombres = {"Atleta 1", "Atleta 2", "Atleta 3", "Atleta 4", "Atleta 5"};

        for (int i = 0; i < atletas.length; i++) {
            atletas[i] = new Atleta(nombres[i]);
        }

        System.out.println("¡La carrera está por comenzar!");

        for (Atleta atleta : atletas) {
            atleta.start();
        }

        for (Atleta atleta : atletas) {
            try {
                atleta.join(); // Espera a que cada hilo termine
            } catch (InterruptedException e) {
                System.out.println("Hubo un problema al esperar el hilo: " + atleta.getNombre());
            }
        }

        System.out.println("¡La carrera ha terminado!");
    }
}

