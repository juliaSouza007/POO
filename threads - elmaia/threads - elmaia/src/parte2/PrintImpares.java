package parte2;

import java.util.Random;

public class PrintImpares implements Runnable{
    @Override
    public void run() {
        Random random = new Random();
        for (int i = 1; i <= 10; i += 2) {          // imprime num impares de 1 a 10
            System.out.println("Impar: " + i);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {      // tratamemto de excecao
                Thread.currentThread().interrupt();
                System.out.println("Thread de impares interrompida.");
            }
        }
    }
}
