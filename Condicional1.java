//Parte 2-a:
//importar a classe Scanner
import java.util.Scanner;

//Classe ImparPar - Scanner
class ImparPar {
    //Bloco main
	public static void main (String[] args) {
	    System.out.println("Impar ou par?");
	    
	    //variavel para guardar o numero 
	    int numero;
		
		  //criar um objeto para o scanner coletar o valor
      Scanner input = new Scanner(System.in);
        
      //pedir ao usuario para digitar um numero:
      System.out.print("Digite um numero inteiro qualquer: ");
      numero = input.nextInt(); //lê o número fornecido pelo usuário, e armazena em 'numero'
        
      //descobrir se eh impar ou par
      if(numero % 2 == 0) {
          System.out.println("eh par!");
      } else {
          System.out.println("eh impar!");
      }
        
      //fechar o scanner para evitar vazamentos de dados
      input.close();
	}
}
