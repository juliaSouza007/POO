package parte3;

public class Teste {
    public static boolean printNow = false;       // define que os impares comecam a ser impressos

    public static void main(String[] args) {
        Object lock = new Object();     // usado para sincronizar o acesso entre as threads

        // cria duas instancias das classes que implementam o run()
        PrintPares imprimePares = new PrintPares(lock);
        PrintImpares imprimeImpares = new PrintImpares(lock);

        // adiciona as instancias em novas Thread
        Thread threadPares = new Thread(imprimePares);
        Thread threadImpares = new Thread(imprimeImpares);

        // inicia as threads
        threadPares.start();
        threadImpares.start();
    }
}
