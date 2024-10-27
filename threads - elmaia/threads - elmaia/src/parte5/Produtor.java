package parte5;

import java.util.concurrent.BlockingQueue;

public class Produtor implements Runnable{
    private final BlockingQueue<Integer> fila;      // fila compartilhada para armazenar elementos

    public Produtor(BlockingQueue<Integer> fila) {
        this.fila = fila;
    }

    @Override
    public void run() {
        try {
            int contador = 0;
            while (true) {
                fila.put(contador);                 // adiciona um elemento a fila
                System.out.println("Produzidos: " + contador);
                contador++;
                Thread.sleep(500);             // simula tempo de producao
                System.out.println("--------------------------");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
