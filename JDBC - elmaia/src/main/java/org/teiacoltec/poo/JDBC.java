package org.teiacoltec.poo;

import org.teiacoltec.poo.Classes.*;
import org.teiacoltec.poo.ClassesDAO.*;
import org.teiacoltec.poo.conexao.FalhaConexaoException;

public class JDBC {

    static void inserir() {
        try {
            // cria Banco
            Banco banco = new Banco("10.100.100/9999-10", "Banco JMSZ");
            BancoDAO.insere(banco);

            Banco bancoRecuperado = BancoDAO.obtemBancoPorCnpj("10.100.100/9999-10");

            // mostra e compara infos
            System.out.println("<< Banco: " + banco.getCnpj() + " >>");
            System.out.println("<< Banco recuperado: " + bancoRecuperado.getCnpj() + " >>");

            // cria agencia, conta e cliente
            banco.criaAgencia("Agencia JMSZ - Centro-Sul", "Avenida Sulcentro, 1.112 - Centro-Sul, Beaga - MG", "(31) 1234-5555");
            AgenciaDAO.insere(banco.getAgencias().getFirst());

            banco.getAgencias().getFirst().criaConta();
            ContaDAO.insere(banco.getAgencias().getFirst().getContas().getFirst());

            banco.getAgencias().getFirst().getContas().getFirst().criaCliente("123.456.789-00", "Ze das Couves", "Rua das Verduras, 780, Bairro Cidade Verde, Beaga - MG", "(31) 99221-9876", 5000);
            ClienteDAO.insere(banco.getAgencias().getFirst().getContas().getFirst().getClientes().getFirst());

            // adciona na tabela Conta_Cliente a relacao entre a conta e o cliente que foram criados
            Conta_ClienteDAO.insere(banco.getAgencias().getFirst().getContas().getFirst().getNum(), banco.getAgencias().getFirst().getContas().getFirst().getClientes().getFirst().getCpf_cnpj());

        } catch (BancoExistenteException | FalhaConexaoException | BancoNaoEncontradoException |
                 AgenciaExistenteException | ContaExistenteException | ClienteExistenteException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws FalhaConexaoException {

        // cria as tabelas (if necessario)
        BancoDAO.criaTabelaBanco();
        AgenciaDAO.criaTabelaAgencia();
        ContaDAO.criaTabelaConta();
        ClienteDAO.criaTabelaCliente();

        inserir();
    }
}
