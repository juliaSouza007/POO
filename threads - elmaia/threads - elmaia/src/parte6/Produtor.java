package parte6;

import java.util.concurrent.BlockingQueue;

public class Produtor implements Runnable{
    private final BlockingQueue<Integer> fila;
    private final int id;               // identificador do produtor

    public Produtor(BlockingQueue<Integer> fila, int id) {
        this.fila = fila;               // inicializa a fila compartilhada
        this.id = id;                   // inicializa o ID do produtor
    }

    @Override
    public void run() {
        try {
            int contador = 0;
            while (true) {
                fila.put(contador);     // adiciona um elemento a fila
                System.out.println("Produtor " + id + " produziu: " + contador);
                contador++;
                Thread.sleep(500); // simula tempo de producao
                System.out.println("--------------------------");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
