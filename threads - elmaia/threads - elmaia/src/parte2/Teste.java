package parte2;

public class Teste {
    public static void main(String[] args) {
        // cria duas instancias das classes que implementam o run()
        PrintPares imprimePares = new PrintPares();
        PrintImpares imprimeImpares = new PrintImpares();

        // adiciona as instancias em novas Thread
        Thread threadPares = new Thread(imprimePares);
        Thread threadImpares = new Thread(imprimeImpares);

        // inicia as threads
        threadPares.start();
        threadImpares.start();
    }
}
