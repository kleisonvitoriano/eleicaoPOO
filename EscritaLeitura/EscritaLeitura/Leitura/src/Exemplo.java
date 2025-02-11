import java.io.BufferedReader;   // leitura de conteúdo do texto
import java.io.FileReader;       // abertura de arquivo de texto para leitura
import java.io.IOException;      // tratamento de exceção
import java.util.Scanner;        // entrada dados do usuário

public class Exemplo {

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        // entrada do nome do arquivo
        System.out.print("Informe o nome de arquivo texto: ");
        String nome = ler.nextLine();

        System.out.printf("\nConteúdo do arquivo texto:\n");
        try {  // tratamento de exceção

            // abertura de arquivo de texto
            FileReader arq = new FileReader(nome);
            
            // leitura de informação de arquivo de texto aberto
            BufferedReader lerArq = new BufferedReader(arq);

            // lê a primeira linha do arquivo
            String linha = lerArq.readLine(); 
        
            // a variável "linha" recebe o valor "null" quando o processo
            // de repetição atingir o final do arquivo texto
            while (linha != null) {
                //System.out.printf("%s\n", linha);
                System.out.println(linha);

                linha = lerArq.readLine(); // lê da segunda até a última linha
            }

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());
        }
        
        System.out.println();
        ler.close();        
    }
}
