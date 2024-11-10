package org.teiacoltec.poo.ClassesDAO;

import org.teiacoltec.poo.Classes.Conta;
import org.teiacoltec.poo.conexao.Conexao;
import org.teiacoltec.poo.conexao.FalhaConexaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {

    private static final int NUMERO_POSICAO_TABELA = 1;
    private static final int NUMEROAGENCIA_POSICAO_TABELA = 2;

    static final int DUPLICATE_KEY_ERROR_CODE = 1062;

    public static void criaTabelaConta() throws FalhaConexaoException {
        try {
            // estabelece conexao
            Connection conexao = Conexao.obtemConexao();

            // consulta
            Statement stmt = conexao.createStatement();
            stmt.execute("CREATE SCHEMA IF NOT EXISTS `coltec` DEFAULT CHARACTER SET utf8;");
            stmt.execute("CREATE TABLE IF NOT EXISTS `coltec`.`Conta` (" +
                    "`num` INT NOT NULL," +
                    "`num_agencia` INT NOT NULL," +
                    "PRIMARY KEY (`num`)," +
                    "INDEX `fk_Conta_Agencia1_idx` (`num_agencia` ASC)," +
                    "CONSTRAINT `fk_Conta_Agencia1` FOREIGN KEY (`num_agencia`) " +
                    "REFERENCES `coltec`.`Agencia` (`num`) ON DELETE CASCADE) ENGINE = InnoDB;");
        } catch (SQLException e) {
            throw new Error(e.getMessage());
        }
    }

    public static Conta obtemContaPorNumero(int numero) throws ContaNaoEncontradaException, FalhaConexaoException {

        try {
            // estabelece conexao
            Connection conexao = Conexao.obtemConexao();

            // consulta
            Statement stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * from Conta WHERE numero = " + numero + ";");

            if (resultado.next()) {
                // obter dados
                return new Conta(resultado.getInt(NUMERO_POSICAO_TABELA),
                        AgenciaDAO.obtemAgenciaPorNumero(resultado.getInt(NUMEROAGENCIA_POSICAO_TABELA))
                );
            }

            // se chegou aqui eh pq nao tem agencia com esse numero
            throw new ContaNaoEncontradaException(numero);
        } catch (SQLException | AgenciaNaoEncontradaException e) {
            throw new Error(e.getMessage());
        }
    }

    public static void insere(Conta conta) throws ContaExistenteException, FalhaConexaoException {

        try {
            // estabelece conexao
            Connection conexao = Conexao.obtemConexao();

            // consulta
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO Conta VALUES(?,?);");
            stmt.setInt(1, conta.getNum());
            stmt.setInt(2, conta.getAgencia().getNum());
            stmt.execute();

        } catch (SQLException e) {
            if (e.getErrorCode() == DUPLICATE_KEY_ERROR_CODE) throw new ContaExistenteException(conta.getNum());
            throw new Error(e.getMessage());
        }
    }

    public static void atualiza(Conta conta) throws ContaNaoEncontradaException, FalhaConexaoException {

        try {
            // estabelece conexao
            Connection conexao = Conexao.obtemConexao();

            // consulta
            PreparedStatement stmt = conexao.prepareStatement("UPDATE Conta SET numero_agencia = ? WHERE numero = ?;");
            stmt.setInt(1, conta.getAgencia().getNum());
            stmt.setInt(2, conta.getNum());

            // verifica quantos registros alterados tem
            int nLinhasAlteradas = stmt.executeUpdate();

            // se nao alterou nenhuma linha eh pq nao tem conta com esse numero
            if (nLinhasAlteradas == 0) throw new ContaNaoEncontradaException(conta.getNum());

        } catch (SQLException e) {
            throw new Error(e.getMessage());
        }
    }

    public static void remove(Conta conta) throws ContaNaoEncontradaException, FalhaConexaoException {

        try {
            // estabelece conexao
            Connection conexao = Conexao.obtemConexao();

            // consulta
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM Conta WHERE numero = ?;");
            stmt.setInt(1, conta.getNum());

            // verifica quentos registros alterados tem
            int nLinhasAlteradas = stmt.executeUpdate();

            // se nao alterou nenhuma linha eh pq nao tem conta com esse numero
            if (nLinhasAlteradas == 0) throw new ContaNaoEncontradaException(conta.getNum());

        } catch (SQLException e) {
            throw new Error(e.getMessage());
        }
    }

    public static List<Conta> obtemListaConta() throws FalhaConexaoException {

        try {
            // estabelece conexao
            Connection conexao = Conexao.obtemConexao();

            // consulta
            Statement stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * from Conta ORDER BY numero;");

            // lista
            List<Conta> lista = new ArrayList<>();

            while(resultado.next()) {
                // obter dados
                Conta contaTmp = new Conta(resultado.getInt(NUMERO_POSICAO_TABELA),
                        AgenciaDAO.obtemAgenciaPorNumero(resultado.getInt(NUMEROAGENCIA_POSICAO_TABELA)));

                // adiciona na lista
                lista.add(contaTmp);
            }

            // retorna a lista << preenchida >>
            return lista;

        } catch (SQLException | AgenciaNaoEncontradaException e) {
            throw new Error(e.getMessage());
        }
    }
}
