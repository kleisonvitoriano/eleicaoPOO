package eleicao_real;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainTeste {
   
    private static List<Aluno> alunos = new ArrayList<>();
    private static List<Docente> docentes = new ArrayList<>();
    private static List<TecnicoADM> tecnicosAdm = new ArrayList<>();

    public static void main(String[] args) {
       
        
        Scanner scanner = new Scanner(System.in);

        // Coletando dados do Servidor
        System.out.println("=== Cadastro de Pré-Candidato ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Matrícula: ");
        int matricula = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        System.out.print("Data de Nascimento (YYYY-MM-DD): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());

        System.out.print("Campus: ");
        String campus = scanner.nextLine();

        System.out.print("Titulação (GRADUACAO, ESPECIALIZACAO, MESTRADO, DOUTORADO): ");
        Titulacao titulacao = Titulacao.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Início da Carreira (YYYY-MM-DD): ");
        LocalDate inicioCarreira = LocalDate.parse(scanner.nextLine());

        System.out.print("Efetivo (true/false): ");
        boolean efetivo = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("Regime de Trabalho (PRESENCIAL ou TELETRABALHO): ");
        String regime = scanner.nextLine().toUpperCase();
        

        

        Pre_Candidato preCandidato = new Pre_Candidato(servidor[0]);
        
        if (preCandidato.validarInscricao()) {
            System.out.println("\nInscrição válida! Oficializando candidatura...");
            Candidato candidato = preCandidato.oficializarCandidatura();
            System.out.println("Candidato Oficializado!");
            System.out.println("Número: " + candidato.getNumero());
            System.out.println("Cor da Campanha: " + candidato.getCor());
        } else {
            System.out.println("\nInscrição inválida! Não atende aos critérios.");
        }
        scanner.close();
        
        
        
        
        
        
        
        
        
        adicionarEleitores();

        /* Gerador de arquivo txt */
        gerarArquivoTxt();
        
    }


    // Move method outside of main
    private static void adicionarEleitores() {
        alunos.add(new Aluno("João Silva", "12345678987", 2020001, LocalDate.of(2000, 5, 15), "Computação"));
        alunos.add(new Aluno("Maria Santos", "87654321987", 2020002, LocalDate.of(2001, 8, 22), "Engenharia"));
        
        docentes.add(new Docente("Carlos Oliveira", "11223344987", 1001, LocalDate.of(1975, 3, 10),"Campus Principal", Titulacao.DOUTORADO, true, 10, LocalDate.now()));

        docentes.add(new Docente("Ana Pereira", "44332211984", 1002, LocalDate.of(1980, 7, 20),"Campus Principal", Titulacao.MESTRADO, true, 8, LocalDate.now()));

        tecnicosAdm.add(new TecnicoADM("Pedro Souza", "55667788987", 2001, LocalDate.of(1985, 11, 5),"Campus Principal", Titulacao.GRADUACAO, LocalDate.of(2006, 2,12) ,true, 7, LocalDate.now(), RegimeTrabalho.PRESENCIAL));

        tecnicosAdm.add(new TecnicoADM("Mariana Costa"," 99887766987", 2002, LocalDate.of(1990, 4, 12),"Campus Principal", Titulacao.ESPECIALIZACAO,LocalDate.of(2007, 6, 2) ,true, 6, LocalDate.now(), RegimeTrabalho.TELETRABALHO));

       
    }

    public static void gerarArquivoTxt() {
        Escritora escritora = new Escritora("eleitores_votantes.txt", true);
        try {
            escritora.escrever("\nDISCENTES:\n");
        for (Aluno aluno : alunos) {
            escritora.escrever(String.format("%d - %s\n", aluno.getMatricula(), aluno.getNome()));
        }
        
      
        escritora.escrever("\nDOCENTES:\n");
        for (Docente docente : docentes) {
            escritora.escrever(String.format("%d - %s\n", docente.getMatricula(), docente.getNome()));
        }
        
        
        escritora.escrever("\nTÉCNICOS-ADMINISTRATIVOS:\n");
        for (TecnicoADM tecnico : tecnicosAdm) {
            escritora.escrever(String.format("%d - %s\n", tecnico.getMatricula(), tecnico.getNome()));
            System.out.println("\n Lista de eleitores gerada com sucesso!");
        }
        } catch (Exception e) {
            System.out.println("Erro ao gerar o arquivo de eleitores: " + e.getMessage());
        }
    }
}