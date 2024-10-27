package parte4;

public class Teste {
    public static void main(String[] args) {
        Contador contador = new Contador();     // instancia do contador compartilhado
        Thread[] threads = new Thread[5];       // array para armazenar as threads

        // cria e inicia 5 threads
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(new Incrementador(contador, i + 1));
            threads[i].start();
        }

        // join -  permite que uma thread aguarde a conclusao de outra thread
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // imprime o valor final do contador apos todas as threads terminarem
        System.out.println("Valor final do contador: " + contador.getValor());
    }
}
