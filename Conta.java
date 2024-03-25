public class Conta {
    String dono;
    double saldo;
    int numero;
    String agencia;
    double valor;

    boolean depositar (double valor) {
        if (valor > 0.0) {
            this.saldo += valor;
            return true;
        } else {
            return false;
        }
    }

    boolean sacar (double valor) {
        if (valor <= saldo) {
            this.saldo -= valor;
            return true;
        } else {
            return false;
        }
    }
}
