package parte6;

import java.util.concurrent.BlockingQueue;

public class Consumidor implements Runnable{
    private final BlockingQueue<Integer> fila;
    private final int id;               // identificador do consumidor

    public Consumidor(BlockingQueue<Integer> fila, int id) {
        this.fila = fila;               // inicializa a fila compartilhada
        this.id = id;                   // inicializa o ID do consumidor
    }

    @Override
    public void run() {
        try {
            while (true) {
                int item = fila.take();  // retira um elemento da fila
                System.out.println("Consumidor " + id + " consumiu: " + item);
                Thread.sleep(1000); // simula tempo de consumo
                System.out.println("--------------------------");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
