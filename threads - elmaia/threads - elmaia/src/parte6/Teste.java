package parte6;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Teste {
    public static void main(String[] args) {
        // fila com capacidade para 10 elementos
        BlockingQueue<Integer> fila = new ArrayBlockingQueue<>(10);

        // cria e inicia multiplos produtores (no caso 3)
        for (int i = 1; i <= 3; i++) {
            new Thread(new Produtor(fila, i)).start();
        }

        // cria e inicia multiplos consumidores (no caso 2)
        for (int i = 1; i <= 2; i++) {
            new Thread(new Consumidor(fila, i)).start();
        }
    }
}
