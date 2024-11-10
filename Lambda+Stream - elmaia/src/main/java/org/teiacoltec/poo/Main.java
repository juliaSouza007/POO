package org.teiacoltec.poo;

import org.teiacoltec.poo.conexao.FalhaConexaoException;

import java.util.List;

public class Main {

    public static void main(String[] args) throws FalhaConexaoException {

        MetodosFacilitadores metodos = new MetodosFacilitadores();

        System.out.println("<< Clientes com saldo negativo: >>");
        metodos.listaClienteSaldoNegativo();
        System.out.println("----------------------------------\n");

        System.out.println("<< Contas da agencia 1: >>");
        metodos.listaContasAgencia(1);
        System.out.println("----------------------------------\n");

        System.out.println("<< Telefone dos clientes: >>");
        metodos.listaTelefoneClientes();
        System.out.println("----------------------------------\n");

        System.out.println("<< Numero de contas da agencia 1: " + metodos.obtemNumContasAgencia(1) + " >>");
        System.out.println("----------------------------------\n");

        System.out.println("<< Numeros das agencias do banco 1: >>");
        List<Integer> numerosAgencia = metodos.obtemNumeAgenciasBanco("10.100.100/9999-10");
        for (int num : numerosAgencia) {
            System.out.println(num);
        }
        System.out.println("----------------------------------\n");
    }
}
