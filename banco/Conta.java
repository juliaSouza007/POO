package banco;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import java.util.Arrays;

public class Conta {

    private Cliente donoConta = new Cliente();
    private Operacao[] operacoes = new Operacao[0];

    private double saldo = 15000;
    private double limite;
    private int numero;
    private String agencia;
    public double valor;

    boolean depositar (double valor) {
        if (valor > 0.0 && (saldo+valor) <= limite) {
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

    void imprimir () {
        System.out.println("Cliente:\n ");
        donoConta.imprimirDados();
        System.out.println("Numero da conta: " +numero);
        System.out.println("Saldo atual: " +saldo);
        System.out.println("Limite: " +limite);

        System.out.println();
    }

    void extrato () {
        for (int i = 0; i < operacoes.length; i++){
            System.out.println(operacoes[i].getData()+"\t"+operacoes[i].getTipo()+"\t\t"+operacoes[i].getValor());
        }
    }

    //getters
    public double getSaldo() {
        return saldo;
    }

    public double getLimite(){
        return limite;
    }

    public int getNumero(){
        return numero;
    }

    public String getAgencia(){
        return agencia;
    }

    public Cliente getDonoConta(){
        return this.donoConta;
    }

    public Operacao[] getOperacoes(){
        return this.operacoes;
    }

    //setters
    public void setLimite(double limite){
        this.limite = limite;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void setDonoConta(Cliente donoConta) {
        this.donoConta = donoConta;
    }

    public void setOperacoes(Operacao operacao) {
        Operacao[] novoArray = Arrays.copyOf(this.operacoes, this.operacoes.length + 1);
        novoArray[this.operacoes.length] = operacao;
        this.operacoes = novoArray;
    }
}

