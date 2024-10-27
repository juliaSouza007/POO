package parte3;

public class PrintPares implements Runnable{
    // trava para controle de sincronizacao entre as threads
    private final Object lock;

    // recebe o objeto de sincronizacao e o estado inicial de vezPares
    public PrintPares(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 2; i <= 10; i += 2) {
            synchronized (lock) {
                while (!Teste.printNow) {         // espera ate que seja a vez dos pares
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Par: " + i);
                Teste.printNow = false;           // passa a vez para os impares
                lock.notify();              // notifica a outra thread (dos impares)
            }
        }
    }
}
