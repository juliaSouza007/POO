package parte5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Teste {
    public static void main(String[] args) {
        // fila com capacidade para 10 elementos
        BlockingQueue<Integer> fila = new ArrayBlockingQueue<>(10);

        Thread produtor = new Thread(new Produtor(fila));
        Thread consumidor = new Thread(new Consumidor(fila));

        produtor.start();       // inicia a thread do produtor
        consumidor.start();     // inicia a thread do consumidor
    }
}
