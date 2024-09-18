import java.util.concurrent.Semaphore;

/**
 * Clase que implementa la exclusión mutua utilizando semáforos.
 * Este programa simula un recurso compartido (una sección crítica)
 * donde varios hilos (threads) intentan acceder. Se controla el acceso 
 * con un semáforo, regulando la entrada y salida de los hilos.
 */
public class ExclusionMutua {
    
    // Semáforo para controlar el acceso a la sección crítica.
    private static Semaphore semaphore = new Semaphore(1);  // Semáforo binario
    
    /**
     * Clase interna que representa un hilo que intenta acceder
     * a la sección crítica.
     */
    static class RecursoCompartido extends Thread {
        private String nombre;

        // Constructor que asigna el nombre al hilo.
        public RecursoCompartido(String nombre) {
            this.nombre = nombre;
        }

        // Método que contiene el código que el hilo ejecuta cuando es iniciado.
        @Override
        public void run() {
            try {
                System.out.println(nombre + " está intentando acceder a la sección crítica...");
                
                // Intentar adquirir el semáforo
                System.out.println(nombre + " está esperando el semáforo...");
                semaphore.acquire();  // Adquiere el semáforo, disminuye su contador
                System.out.println(nombre + " ha adquirido el semáforo (permiso otorgado).");

                // Simulación de la sección crítica (puede ser cualquier tarea)
                System.out.println(nombre + " ha entrado en la sección crítica.");
                Thread.sleep(2000);  // Simula trabajo en la sección crítica

                System.out.println(nombre + " ha salido de la sección crítica.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // Liberar el semáforo, permitiendo que otros hilos accedan a la sección crítica.
                semaphore.release();  // Incrementa el contador del semáforo
                System.out.println(nombre + " ha liberado el semáforo.");
                System.out.println("Permisos disponibles del semáforo: " + semaphore.availablePermits());
            }
        }
    }

    /**
     * Método principal que inicia varios hilos.
     * Cada hilo intenta acceder a la sección crítica controlada por el semáforo.
     */
    public static void main(String[] args) {
        // Crear e iniciar varios hilos (representando procesos que compiten por el recurso)
        RecursoCompartido hilo1 = new RecursoCompartido("Hilo 1");
        RecursoCompartido hilo2 = new RecursoCompartido("Hilo 2");
        RecursoCompartido hilo3 = new RecursoCompartido("Hilo 3");
        RecursoCompartido hilo4 = new RecursoCompartido("Hilo 4");
        RecursoCompartido hilo5 = new RecursoCompartido("Hilo 5");

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
    }
}
