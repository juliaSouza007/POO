package org.teiacoltec.poo.Classes;

import java.util.ArrayList;

public class Agencia {
    //variaveis
    final private int num;
    private String nome;
    private String telefone;
    private String endereco;
    final private Banco banco;
    private ArrayList<Conta> contas;
    private int proximoNumConta = 1;

    public Agencia(int num, String nome, String telefone, String endereco, Banco banco) {
        this.num = num;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.banco = banco;
        this.contas = new ArrayList<Conta>();
        this.proximoNumConta = 1;
    }

    private int proximoNum() {
        return this.proximoNumConta++;
    }

    // associacao e desassociacao entre tais entidades
    public void criaConta() {
        Conta conta = new Conta(proximoNum(), this);
        this.contas.add(conta);
    }

    public void removeConta(int numero) {
        for (Conta conta: contas ) {
            if (conta.getNum() == numero) {
                this.contas.remove(conta.getNum());
                break;
            }
        }
    }

    // Getters e Setters
    public int getNum() { return this.num; }

    public Banco getBanco() { return this.banco; }

    public void setNome(String nome) { this.nome = nome; }
    public String getNome() { return this.nome; }

    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getEndereco() { return this.endereco; }

    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getTelefone() { return this.telefone; }

    public ArrayList<Conta> getContas() { return this.contas; }
}