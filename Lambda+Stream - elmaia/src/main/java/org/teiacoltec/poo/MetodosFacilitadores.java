package org.teiacoltec.poo;

import org.teiacoltec.poo.Classes.*;
import org.teiacoltec.poo.ClassesDAO.*;
import org.teiacoltec.poo.conexao.FalhaConexaoException;

import java.util.List;
import java.util.stream.Collectors;

public class MetodosFacilitadores {
    private List<Agencia> agencias;
    private List<Conta> contas;
    private List<Cliente> clientes;
    private List<Banco> bancos;

    public MetodosFacilitadores() throws FalhaConexaoException {
        // obter os bancos do BD
        bancos = BancoDAO.obtemListaBanco();

        // obter as agencias do BD
        agencias = AgenciaDAO.obtemListaAgencia();

        // obter as contas do BD
        contas = ContaDAO.obtemListaConta();

        // obter os cliente do BD
        clientes = ClienteDAO.obtemListaCliente();
    }

    public void listaContasAgencia(int numAgencia) {
         contas.stream().filter(c -> c.getAgencia().getNum() == numAgencia).forEach(c -> System.out.println(c.getNum()));
    }

    public void listaClienteSaldoNegativo() {
        clientes.stream().filter(c -> c.getSaldo() < 0).forEach(c -> System.out.println(c.getCpf_cnpj()));
    }

    public List<Integer> obtemNumeAgenciasBanco(String cnpjBanco) {
        List<Integer> listanumeros = agencias.stream().
                                     filter(a -> a.getBanco().getCnpj().equals(cnpjBanco)).
                                     map(a -> a.getNum()).collect(Collectors.toList());
        return listanumeros;

    }

    public void listaTelefoneClientes() {
        clientes.stream().forEach(c -> System.out.println(c.getTelefone()));
    }

    public int obtemNumContasAgencia(int numAgencia) {
        int numConta = (int) contas.stream().
                             filter(c -> c.getAgencia().getNum() == numAgencia).
                             count();
        return numConta;

    }

}
