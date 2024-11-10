package org.teiacoltec.poo.ClassesDAO;

import org.teiacoltec.poo.Classes.Banco;
import org.teiacoltec.poo.conexao.Conexao;
import org.teiacoltec.poo.conexao.FalhaConexaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BancoDAO {

    private static final int CNPJ_POSICAO_TABELA = 1;
    private static final int NOME_POSICAO_TABELA = 2;

    static final int DUPLICATE_KEY_ERROR_CODE = 1062;

    public static void criaTabelaBanco() throws FalhaConexaoException {
        try {
            // Estabelece conexao
            Connection conexao = Conexao.obtemConexao();

            // Faz a consulta
            Statement stmt = conexao.createStatement();
            stmt.execute("CREATE SCHEMA IF NOT EXISTS `coltec` DEFAULT CHARACTER SET utf8;");
            stmt.execute("CREATE TABLE IF NOT EXISTS `coltec`.`Banco` (" +
                    "`cnpj` VARCHAR(20) NOT NULL," +
                    "`nome` VARCHAR(255) NOT NULL," +
                    "PRIMARY KEY (`cnpj`)) ENGINE = InnoDB;");
        } catch (SQLException e) {
            throw new Error(e.getMessage());
        }
    }

    public static Banco obtemBancoPorCnpj(String cnpj) throws BancoNaoEncontradoException, FalhaConexaoException {

        try {
            // estabelece conexao
            Connection conexao = Conexao.obtemConexao();

            // consulta
            Statement stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * from Banco WHERE cnpj = '" + cnpj + "';");

            if (resultado.next()) {
                // obter dados
                return new Banco(resultado.getString(CNPJ_POSICAO_TABELA),
                        resultado.getString(NOME_POSICAO_TABELA)
                );
            }

            // se chegou aqui eh pq nao tem banco com esse cnpj
            throw new BancoNaoEncontradoException(cnpj);
        } catch (SQLException e) {
            throw new Error(e.getMessage());
        }
    }

    public static void insere(Banco banco) throws BancoExistenteException, FalhaConexaoException {

        try {
            // estabelece conexao
            Connection conexao = Conexao.obtemConexao();

            // consulta
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO Banco VALUES(?,?);");
            stmt.setString(1, banco.getCnpj());
            stmt.setString(2, banco.getNome());
            stmt.execute();

        } catch (SQLException e) {
            if (e.getErrorCode() == DUPLICATE_KEY_ERROR_CODE) throw new BancoExistenteException(banco.getCnpj());
            throw new Error(e.getMessage());
        }
    }

    public static void atualiza(Banco banco) throws BancoNaoEncontradoException, FalhaConexaoException {

        try {
            // estabelece conexao
            Connection conexao = Conexao.obtemConexao();

            // consulta
            PreparedStatement stmt = conexao.prepareStatement("UPDATE Banco SET nome = ? WHERE cnpj = ?;");
            stmt.setString(1, banco.getNome());
            stmt.setString(2, banco.getCnpj());

            // verifica quantos registros alterados tem
            int nLinhasAlteradas = stmt.executeUpdate();

            // se nao alterou nenhuma linha eh pq nao tem banco com esse cnpj
            if (nLinhasAlteradas == 0) throw new BancoNaoEncontradoException(banco.getCnpj());

        } catch (SQLException e) {
            throw new Error(e.getMessage());
        }
    }

    public static void remove(Banco banco) throws BancoNaoEncontradoException, FalhaConexaoException {

        try {
            // estabelece conexao
            Connection conexao = Conexao.obtemConexao();

            // consulta
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM Banco WHERE cnpj = ?;");
            stmt.setString(1, banco.getCnpj());

            // verifica quantos registros alterados tem
            int nLinhasAlteradas = stmt.executeUpdate();

            // se nao alterou nenhuma linha eh pq nao tem banco com esse cnpj
            if (nLinhasAlteradas == 0) throw new BancoNaoEncontradoException(banco.getCnpj());

        } catch (SQLException e) {
            throw new Error(e.getMessage());
        }
    }

    public static List<Banco> obtemListaBanco() throws FalhaConexaoException {

        try {
            // estabelece conexao
            Connection conexao = Conexao.obtemConexao();

            // consulta
            Statement stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * from Banco ORDER BY nome;");

            // lista
            List<Banco> lista = new ArrayList<>();

            while(resultado.next()) {
                // obter dados
                Banco bancoTmp = new Banco(resultado.getString(CNPJ_POSICAO_TABELA),
                        resultado.getString(NOME_POSICAO_TABELA));
                // adiciona na lista
                lista.add(bancoTmp);
            }

            // retorna a lista << preenchida >>
            return lista;

        } catch (SQLException e) {
            throw new Error(e.getMessage());
        }
    }
}