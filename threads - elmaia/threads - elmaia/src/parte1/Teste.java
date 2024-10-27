package parte1;

public class Teste {
    public static void main(String[] args) {
        // cria uma instancia da classe que implementa o run()
        FirstThread myThread = new FirstThread();

        // adiciona a instancia em uma nova Thread
        Thread thread = new Thread(myThread);

        // inicia a thread
        thread.start();
    }
}
