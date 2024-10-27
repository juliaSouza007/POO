package parte4;

public class Incrementador implements Runnable{
    private final Contador contador;
    private final int id;

    public Incrementador(Contador contador, int id) {
        this.contador = contador;
        this.id = id;
    }

    @Override
    public void run() {
        // cada thread incrementa o contador 100.000 vezes
        for (int i = 0; i < 100_000; i++) {
            contador.incrementar();
        }
        // imprime mensagem ao terminar
        System.out.println("<< Thread " + id + " terminou de incrementar >>");
    }
}
