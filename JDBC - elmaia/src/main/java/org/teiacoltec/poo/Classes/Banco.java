package org.teiacoltec.poo.Classes;

import java.util.ArrayList;

public class Banco {
    final private String cnpj;
    private String nome;
    private ArrayList<Agencia> agencias;
    private int proximoNumAgencia;

    public Banco(String cnpj, String nome) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.agencias = new ArrayList<Agencia>();
        this.proximoNumAgencia = 1;
    }

    private int proximoNum() {
        return this.proximoNumAgencia++;
    }

    // Associacao e desassociacao entre tais entidades
    public void criaAgencia (String nome, String endereco, String telefone) {
        Agencia agencia = new Agencia(proximoNum(), nome, endereco, telefone, this);
        this.agencias.add(agencia);
    }

    public void removeAgencia(int numero) {
        for (Agencia agencia: agencias ) {
            if (agencia.getNum() == numero) {
                agencias.remove(agencia.getNum());
                break;
            }
        }
    }

    // Getters e Setters
    public String getCnpj() { return this.cnpj; }

    public void setNome(String nome) { this.nome = nome; }
    public String getNome() { return this.nome; }

    public ArrayList<Agencia> getAgencias() { return this.agencias; }

}
