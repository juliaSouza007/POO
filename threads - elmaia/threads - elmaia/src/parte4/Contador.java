package parte4;

public class Contador {
    private int valor = 0;

    // metodo sincronizado para incrementar o valor do contador
    public synchronized void incrementar() {
        valor++;
    }

    // metodo para obter o valor atual do contador
    public int getValor() {
        return valor;
    }
}
