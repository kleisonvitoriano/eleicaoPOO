import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileWriter;    // Abertura de arquivo de texto para escrita
import java.io.IOException;   // Tramento de exceção (exigido)
import java.io.PrintWriter;   // Escrita de texto em arquivo aberto

import java.io.BufferedReader;   // leitura de conteúdo do texto
import java.io.FileReader;       // abertura de arquivo de texto para leitura

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        Scanner scan = new Scanner(System.in);
        int opcao;

        System.out.print(" Entrar dados (1) ou ler de arquivo (2): ");
        opcao = scan.nextInt();

        if(opcao == 1) {
            // entrada de dados pelo usuário
            int nAlunos, media;
            String cpf, nome;

            System.out.print(" Digite número de alunos: ");
            nAlunos = scan.nextInt();
            scan.nextLine();

            for(int i = 1; i <= nAlunos; i++) {
                System.out.println("\n ** ALUNO " + i + " ** ");
                System.out.print(" Digite o CPF: ");
                cpf = scan.nextLine();

                System.out.print(" Digite o Nome: ");
                nome = scan.nextLine();

                System.out.print(" Digite a Media: ");
                media = scan.nextInt();
                scan.nextLine();

                alunos.add(new Aluno(cpf,nome,media));
            }

            System.out.print(" Digite o nome do arquivo: ");
            salvarArquivo(alunos,scan.next());
        
        } else {
            // entrada de dados por arquivo
            System.out.print(" Digite o nome do arquivo: ");
            alunos = leituraArquivo(scan.next());

            imprimirAlunos(alunos);
        }

        scan.close();
    }

    public static void salvarArquivo(ArrayList<Aluno> alunos, String nomeArquivo) throws IOException {
        FileWriter arquivo = new FileWriter(nomeArquivo);   
        PrintWriter gravarArq = new PrintWriter(nomeArquivo);

        for (Aluno aluno : alunos) {
            gravarArq.println(aluno.toString());
        }

        gravarArq.close();
        arquivo.close();        
    }

    public static ArrayList<Aluno> leituraArquivo(String nomeArquivo) {
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();

        try {  
            FileReader arq = new FileReader(nomeArquivo);         
            BufferedReader lerArq = new BufferedReader(arq);
      
            String linha = lerArq.readLine();
            String [] itensLinha;

            while(linha != null) {              
                itensLinha = linha.split(";");        

                String cpf = itensLinha[0]; 
                String nome = itensLinha[1];
                int media = Integer.parseInt(itensLinha[2]);

                alunos.add(new Aluno(cpf,nome,media));

                linha = lerArq.readLine();
            } 

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());
        }

        return alunos;
    }

    public static void imprimirAlunos(ArrayList<Aluno> alunos) {
        System.out.println("\n ** LISTAGEM DE ALUNOS ** ");
        for(Aluno aluno : alunos) {
            aluno.imprimir();
            System.out.println();
        }
    }
}
