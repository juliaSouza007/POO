package parte2;

import java.util.Random;

public class PrintPares implements Runnable{
    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i <= 10; i += 2) {          // imprime num pares de 0 a 10
            System.out.println("Par: " + i);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {      // tratamento de excecao
                Thread.currentThread().interrupt();
                System.out.println("Thread de pares interrompida.");
            }
        }
    }
}
