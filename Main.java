public class Main {
    public static void main(String[] args) {

        Conta minhaConta = new Conta();

        minhaConta.dono = "João";
        minhaConta.agencia = "222-3";
        minhaConta.numero = 1234;
        minhaConta.saldo = 200;

        boolean depositoRealizado = minhaConta.depositar(250.0);

        if (depositoRealizado) {
            System.out.println("Deposito realizado com sucesso!");
            System.out.println("Saldo atual: " +minhaConta.saldo);
        } else {
            System.out.println("Erro ao depositar...");
        }

        boolean saqueRealizadp = minhaConta.sacar(300.0);

        if (saqueRealizadp) {
            System.out.println("Saque realizado com sucesso!");
            System.out.println("Saldo atual: " +minhaConta.saldo);
        } else {
            System.out.println("Erro ao sacar...");
        }
    }
}
