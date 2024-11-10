package org.teiacoltec.poo.Classes;

import java.util.ArrayList;

public class Cliente {
    private String cpf_cnpj;
    private String nome;
    private String telefone;
    private String endereco;
    private float saldo;
    private ArrayList<Conta> contas;
    private int proximoNumConta;

    public Cliente(String cpf_cnpj, String nome,  String telefone, String endereco, float saldo) {
        this.cpf_cnpj = cpf_cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.saldo = saldo;
        this.contas = new ArrayList<Conta>();
        this.proximoNumConta = 1;
    }

    private int proximoNum() {
        return this.proximoNumConta++;
    }

    // associacao e desassociacao entre entidades
    public void criaConta(Agencia agencia) {
        Conta conta = new Conta(proximoNum(), agencia);
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
    public String getCpf_cnpj() { return this.cpf_cnpj; }

    public void setNome(String nome) { this.nome = nome; }
    public String getNome() { return this.nome; }

    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getEndereco() { return this.endereco; }

    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getTelefone() { return this.telefone; }

    public void setSaldo(float saldo) { this.saldo = saldo; }
    public float getSaldo() { return this.saldo; }

    public ArrayList<Conta> getContas() { return this.contas; }
}