package org.teiacoltec.poo.ClassesDAO;

import org.teiacoltec.poo.Classes.Agencia;
import org.teiacoltec.poo.conexao.Conexao;
import org.teiacoltec.poo.conexao.FalhaConexaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AgenciaDAO {

    private static final int NUMERO_POSICAO_TABELA = 1;
    private static final int NOME_POSICAO_TABELA = 2;
    private static final int ENDERECO_POSICAO_TABELA = 3;
    private static final int TELEFONE_POSICAO_TABELA = 4;
    private static final int CNPJBANCO_POSICAO_TABELA = 5;

    static final int DUPLICATE_KEY_ERROR_CODE = 1062;

    public static void criaTabelaAgencia() throws FalhaConexaoException {
        try {
            // Estabelece conexao
            Connection conexao = Conexao.obtemConexao();

            // Faz a consulta
            Statement stmt = conexao.createStatement();
            stmt.execute("CREATE SCHEMA IF NOT EXISTS `coltec` DEFAULT CHARACTER SET utf8;");
            stmt.execute("CREATE TABLE IF NOT EXISTS `coltec`.`Agencia` (" +
                    "`num` INT NOT NULL," +
                    "`nome` VARCHAR(255) NOT NULL," +
                    "`endereco` VARCHAR(255) NOT NULL," +
                    "`telefone` VARCHAR(20) NOT NULL," +
                    "`cnpj_banco` VARCHAR(20) NOT NULL," +
                    "PRIMARY KEY (`num`)," +
                    "INDEX `fk_Agencia_Banco_idx` (`cnpj_banco` ASC)," +
                    "CONSTRAINT `fk_Agencia_Banco` FOREIGN KEY (`cnpj_banco`) " +
                    "REFERENCES `coltec`.`Banco` (`cnpj`) ON DELETE CASCADE) ENGINE = InnoDB;");
        } catch (SQLException e) {
            throw new Error(e.getMessage());
        }
    }

    public static Agencia obtemAgenciaPorNumero(int numero) throws AgenciaNaoEncontradaException, FalhaConexaoException {

        try {
            // estabelece conexao
            Connection conexao = Conexao.obtemConexao();

            // consulta
            Statement stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * from Agencia WHERE numero = " + numero + ";");

            if (resultado.next()) {
                // obter dados
                return new Agencia(resultado.getInt(NUMERO_POSICAO_TABELA),
                        resultado.getString(NOME_POSICAO_TABELA),
                        resultado.getString(ENDERECO_POSICAO_TABELA),
                        resultado.getString(TELEFONE_POSICAO_TABELA),
                        BancoDAO.obtemBancoPorCnpj(resultado.getString(CNPJBANCO_POSICAO_TABELA)) // Vai ter que fazer uma outra consulta
                );
            }

            // se chegar aqui eh pq nao tem agencia com esse numero
            throw new AgenciaNaoEncontradaException(numero);
        } catch (SQLException | BancoNaoEncontradoException e) {
            throw new Error(e.getMessage());
        }
    }

    public static void insere(Agencia agencia) throws AgenciaExistenteException, FalhaConexaoException {

        try {
            // estabelece conexao
            Connection conexao = Conexao.obtemConexao();

            // consulta
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO Agencia VALUES(?,?,?,?,?);");
            stmt.setInt(1, agencia.getNum());
            stmt.setString(2, agencia.getNome());
            stmt.setString(3, agencia.getEndereco());
            stmt.setString(4, agencia.getTelefone());
            stmt.setString(5, agencia.getBanco().getCnpj());
            stmt.execute();

        } catch (SQLException e) {
            if (e.getErrorCode() == DUPLICATE_KEY_ERROR_CODE) throw new AgenciaExistenteException(agencia.getNum());
            throw new Error(e.getMessage());
        }
    }

    public static void atualiza(Agencia agencia) throws AgenciaNaoEncontradaException, FalhaConexaoException {

        try {
            // estabelece conexao
            Connection conexao = Conexao.obtemConexao();

            // consulta
            PreparedStatement stmt = conexao.prepareStatement("UPDATE Agencia SET nome = ?, endereco = ?, telefone = ?, cnpj_banco = ? WHERE numero = ?;");
            stmt.setString(1, agencia.getNome());
            stmt.setString(2, agencia.getEndereco());
            stmt.setString(3, agencia.getTelefone());
            stmt.setString(4, agencia.getBanco().getCnpj());
            stmt.setInt(5, agencia.getNum());

            // verifica quantos registros alterados tem
            int nLinhasAlteradas = stmt.executeUpdate();

            // se nao alterou nenhuma linha eh pq nao tem agencia com esse numero
            if (nLinhasAlteradas == 0) throw new AgenciaNaoEncontradaException(agencia.getNum());

        } catch (SQLException e) {
            throw new Error(e.getMessage());
        }
    }

    public static void remove(Agencia agencia) throws AgenciaNaoEncontradaException, FalhaConexaoException {

        try {
            // estabelece conexao
            Connection conexao = Conexao.obtemConexao();

            // consulta
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM Agencia WHERE numero = ?;");
            stmt.setInt(1, agencia.getNum());

            // verifica quantos registros alterados tem
            int nLinhasAlteradas = stmt.executeUpdate();

            // se nao alterou nenhuma linha eh pq nao tem agencia com esse numero
            if (nLinhasAlteradas == 0) throw new AgenciaNaoEncontradaException(agencia.getNum());

        } catch (SQLException e) {
            throw new Error(e.getMessage());
        }
    }

    public static List<Agencia> obtemListaAgencia() throws FalhaConexaoException {

        try {
            // estabelece conexao
            Connection conexao = Conexao.obtemConexao();

            // consulta
            Statement stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * from Agencia ORDER BY nome;");

            // lista
            List<Agencia> lista = new ArrayList<>();

            while(resultado.next()) {
                // obter dados
                Agencia agenciaTmp = new Agencia(resultado.getInt(NUMERO_POSICAO_TABELA),
                        resultado.getString(NOME_POSICAO_TABELA),
                        resultado.getString(ENDERECO_POSICAO_TABELA),
                        resultado.getString(TELEFONE_POSICAO_TABELA),
                        BancoDAO.obtemBancoPorCnpj(resultado.getString(CNPJBANCO_POSICAO_TABELA)));
                // adiciona na lista
                lista.add(agenciaTmp);
            }

            // retorna a lista << preenchida >>
            return lista;

        } catch (SQLException | BancoNaoEncontradoException e) {
            throw new Error(e.getMessage());
        }
    }
}