package parte3;

public class PrintImpares implements Runnable{
    // trava para controle de sincronizacao entre as threads
    private final Object lock;

    // recebe o objeto de sincronizacao e o estado inicial de vezPares
    public PrintImpares(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 9; i += 2) {
            synchronized (lock) {
                while (Teste.printNow) {       // espera ate que seja a vez dos impares
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Impar: " + i);
                Teste.printNow = true;        // passa a vez para os pares
                lock.notify();          // notifica a outra thread (dos pares)
            }
        }
    }
}
