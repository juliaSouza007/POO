package Sidebar;

import javax.swing.*;

public class ExemploJOptionPane {
    public static void main(String[] args) {
        // Inicializa um diálogo de informação
        JOptionPane.showMessageDialog(null, "Hello, my friend!");

        // diálogo de erro
        JOptionPane.showMessageDialog(null, "Exemplo de diálogo de erro","Título do erro", JOptionPane.ERROR_MESSAGE);

        // diálogo de confirmação
        JOptionPane.showConfirmDialog(null, "Você confirma?", "YES OR NO?", JOptionPane.YES_NO_OPTION);

        //diálogo de requisição de dados
        String inputValue = JOptionPane.showInputDialog("Qual o seu nome?");

        // diálogo de requisição com dados previamente definidos
        Object[] opcoes = {"20", "18", "19"};
            String s = (String) JOptionPane.showInputDialog(null,
                                                            "Qual desses números é ímpar?",
                                                            "Escolha o número",
                                                            JOptionPane.PLAIN_MESSAGE,
                                                            null,
                                                            opcoes,
                                                            "20");
    }
}
