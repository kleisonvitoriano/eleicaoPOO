import java.io.FileWriter;    // Abertura de arquivo de texto para escrita
import java.io.IOException;   // Tramento de exceção (exigido)
import java.io.PrintWriter;   // Escrita de texto em arquivo aberto
import java.util.Scanner;     // Entrada de dados do usuário

public class Exemplo {

  public static void main(String[] args) throws IOException {
    Scanner scan = new Scanner(System.in);
    int i, n;

    // entrada do usuário: tabuada desejada
    System.out.print(" => Tabuada de qual número? ");
    n = scan.nextInt();

    // abertura/criação de arquivo (realiza sobrescrita)
    FileWriter arq = new FileWriter("tabuada.txt");   // false, true append
        
    // objeto para gravação de texto em arquivo aberto
    PrintWriter gravarArq = new PrintWriter(arq);

    // escrevendo texto em arquivo (printf, println, print)
    gravarArq.println("+--Resultado--+");
    for (i=1; i<=10; i++) {
      gravarArq.printf("| %2d X %d = %2d |%n", i, n, (i*n));
      //gravarArq.printf(i + " X " + n + " = " + i*n);
    }
    gravarArq.println("+-------------+");

    gravarArq.close();
    arq.close();
    scan.close();

    System.out.printf("\nTabuada do %d foi gravada com sucesso em \"tabuada.txt\".\n", n);
  }

}
