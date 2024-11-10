package org.teiacoltec.poo.ClassesDAO;

public class ClienteNaoEncontradoException extends Exception {

    public ClienteNaoEncontradoException(String cpf_cnpj) {
        super("<< Cliente de cpf/cnpj " + cpf_cnpj + " nao encontrado >>");
    }

}
