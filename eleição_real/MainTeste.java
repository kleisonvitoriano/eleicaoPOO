package eleição_real;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainTeste {
    // Lists to store voters by segment
    private static List<Aluno> alunos = new ArrayList<>();
    private static List<Docente> docentes = new ArrayList<>();
    private static List<TecnicoADM> tecnicosAdm = new ArrayList<>();

    
    private static void adicionarEleitores() {
    
        alunos.add(new Aluno("João Silva", 12345678, 2020001, LocalDate.of(2000, 5, 15), "Computação"));
        alunos.add(new Aluno("Maria Santos", 87654321, 2020002, LocalDate.of(2001, 8, 22), "Engenharia"));

       
        docentes.add(new Docente("Carlos Oliveira", 11223344, 1001, LocalDate.of(1975, 3, 10), 
                                  "Campus Principal", Titulacao.DOUTOR, true, 10, LocalDate.now()));
        docentes.add(new Docente("Ana Pereira", 44332211, 1002, LocalDate.of(1980, 7, 20), 
                                  "Campus Principal", Titulacao.MESTRE, true, 8, LocalDate.now()));


        tecnicosAdm.add(new TecnicoADM("Pedro Souza", 55667788, 2001, LocalDate.of(1985, 11, 5), 
                                        "Campus Principal", Titulacao.GRADUADO, true, 7, LocalDate.now()));
        tecnicosAdm.add(new TecnicoADM("Mariana Costa", 99887766, 2002, LocalDate.of(1990, 4, 12), 
                                        "Campus Principal", Titulacao.ESPECIALISTA, true, 6, LocalDate.now()));
    }

   
    private static void gerarListaEleitores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("lista_eleitores.txt"))) {
          
            writer.write("DISCENTES:\n");
            for (Aluno aluno : alunos) {
                writer.write(String.format("%d - %s\n", aluno.getMatricula(), aluno.getNome()));
            }
            
          
            writer.write("\nDOCENTES:\n");
            for (Docente docente : docentes) {
                writer.write(String.format("%d - %s\n", docente.getMatricula(), docente.getNome()));
            }
            
            
            writer.write("\nTÉCNICOS-ADMINISTRATIVOS:\n");
            for (TecnicoADM tecnico : tecnicosAdm) {
                writer.write(String.format("%d - %s\n", tecnico.getMatricula(), tecnico.getNome()));
            }
            
            System.out.println("Lista de eleitores gerada com sucesso em lista_eleitores.txt");
        } catch (IOException e) {
            System.err.println("Erro ao gerar lista de eleitores: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        adicionarEleitores();
        gerarListaEleitores();
    }
}
