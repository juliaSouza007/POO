package banco;
import java.util.Scanner;

public class Cliente {

    private String nome;
    private String cpf;
    private String endereco;
    private int idade;
    private char sexo;

    public static int totalDeContas;

    Scanner input = new Scanner(System.in);

    void cadastrarCliente(){
        System.out.print("Nome: ");
        nome = input.nextLine();

        System.out.print("CPF: ");
        cpf = input.nextLine();

        System.out.print("Endereco: ");
        endereco = input.nextLine();

        System.out.print("Idade: ");
        idade = input.nextInt();

        System.out.print("Sexo(m ou f): ");
        sexo = input.next().charAt(0);

        this.totalDeContas++;
    }

    void imprimirDados(){
        System.out.println("Nome: " +nome);
        System.out.println("CPF: " +cpf);
        System.out.println("Endereco: " +endereco);
        System.out.println("Idade: " +idade);
        System.out.println("Sexo: " +sexo+ "\n");
    }

    //getters
    public String getNome(){
        return nome;
    }

    public String getCpf(){
        return cpf;
    }

    public String getEndereco(){
        return endereco;
    }

    public int getIdade(){
        return idade;
    }

    public char getSexo(){
        return sexo;
    }

    //setters
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    public void setSexo(char sexo){
        this.sexo = sexo;
    }

}




