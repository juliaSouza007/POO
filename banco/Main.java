package banco;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Conta minhaConta = new Conta();

        minhaConta.setAgencia("222-3");
        minhaConta.setNumero(123);
        minhaConta.setLimite(25000);

        int executar;

        Scanner input = new Scanner(System.in);

        System.out.println("SITE OFICIAL BANCO JMS\n Cadastrar Cliente:");
        Cliente cliente = new Cliente();
        cliente.cadastrarCliente();
        minhaConta.setDonoConta(cliente);

        do {
            System.out.println("\n\nSITE OFICIAL BANCO JMS\n O que deseja fazer?");
            System.out.println("[1] Deposito\n[2] Saque\n[3] Infos Pessoais\n[4] Operacoes\n[5] Extrato\n[0] Sair");

            executar = input.nextInt();

            switch (executar) {
                case 1:
                    double valorDep;
                    char d='d';

                    System.out.println("\nQual o valor do deposito?");
                    valorDep = input.nextInt();

                    boolean depositoRealizado = minhaConta.depositar(valorDep);

                    if (depositoRealizado) {
                        minhaConta.setOperacoes(new Operacao(d, valorDep));
                        System.out.println("Deposito realizado com sucesso!");
                        System.out.println("Saldo atual: " +minhaConta.getSaldo());
                    } else {
                        System.out.println("Erro ao depositar...");
                    }
                    break;

                case 2:
                    double valorSaq;
                    char s='s';

                    System.out.println("\nQual o valor do saque?");
                    valorSaq = input.nextInt();

                    boolean saqueRealizado = minhaConta.sacar(valorSaq);

                    if (saqueRealizado) {
                        minhaConta.setOperacoes(new Operacao(s, valorSaq));
                        System.out.println("Saque realizado com sucesso!");
                        System.out.println("Saldo atual: " +minhaConta.getSaldo());
                    } else {
                        System.out.println("Erro ao sacar...");
                    }
                    break;

                case 3:
                    System.out.println("\nImprimindo informacoes:");
                    minhaConta.imprimir();
                    break;

                case 4:
                    System.out.println("\n Imprimindo operacoes realizadas:");
                    for (int i = 0; i < Operacao.totalOperacoes; i++){
                        minhaConta.getOperacoes()[i].operacoesDados();
                    }

                    break;

                case 5:
                    System.out.println("\n Imprimindo extrato da conta");
                    minhaConta.extrato();
                    break;

                case 0:
                    break;
            }

            if (Operacao.totalOperacoes == 1000) executar = 0;

        } while (executar!=0);
    }
}

