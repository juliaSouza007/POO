package parte5;

import java.util.concurrent.BlockingQueue;

public class Consumidor implements Runnable{
    private final BlockingQueue<Integer> fila;           // fila compartilhada armazenar elementos

    public Consumidor(BlockingQueue<Integer> fila) {
        this.fila = fila;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int item = fila.take();                 // retira um elemento da fila
                System.out.println("Consumidos: " + item);
                Thread.sleep(1000);                // simula tempo de consumo
                System.out.println("--------------------------");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
