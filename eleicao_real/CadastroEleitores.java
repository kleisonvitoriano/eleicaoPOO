package eleicao_real;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CadastroEleitores {
    
    private Scanner scanner;

    public CadastroEleitores() {
        this.scanner = new Scanner(System.in);
    }

    public void cadastrarEleitores(List<Aluno> alunos, List<Docente> docentes, List<TecnicoADM> tecnicosAdm) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== Cadastro de Usuário ===");
            System.out.println("Escolha uma categoria: ");
            System.out.println("1 - Aluno");
            System.out.println("2 - Docente");
            System.out.println("3 - Técnico Administrativo");
            System.out.println("4 - Finalizar Cadastro");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            if (opcao == 4) {
                continuar = false;
                break;
            }

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("Matrícula: ");
            int matricula = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            System.out.print("Data de Nascimento (AAAA-MM-DD): ");
            LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());

            System.out.print("Campus: ");
            String campus = scanner.nextLine();

            switch (opcao) {
                case 1: // Cadastro de Aluno
                    System.out.print("Curso: ");
                    String curso = scanner.nextLine();
                    alunos.add(new Aluno(nome, cpf, matricula, dataNascimento, curso));
                    break;
                
                case 2: // Cadastro de Docente
                    Titulacao titulacao = perguntarTitulacao();

                    System.out.print("Efetivo (true/false): ");
                    boolean efetivo = scanner.nextBoolean();
                    scanner.nextLine();
                    
                    
                    System.out.print("Data de Início da Carreira (YYYY-MM-DD): ");
                    LocalDate inicioCarreira = LocalDate.parse(scanner.nextLine());
                    
                    int tempoCarreira = LocalDate.now().getYear() - inicioCarreira.getYear();

                    docentes.add(new Docente(nome, cpf, matricula, dataNascimento, campus, titulacao, efetivo, tempoCarreira, inicioCarreira));
                    break;
                
                case 3: // Cadastro de Técnico Administrativo
                    
                Titulacao titulacaoTec = perguntarTitulacao();
                
                    

                    System.out.print("Data de Início da Carreira (YYYY-MM-DD): ");
                    LocalDate inicioCarreiraTec = LocalDate.parse(scanner.nextLine());

                    System.out.print("Efetivo (sim/não): ");
                    boolean efetivoTec = scanner.nextBoolean();
                    scanner.nextLine();

                    System.out.print("Regime de Trabalho (PRESENCIAL ou TELETRABALHO): ");
                    RegimeTrabalho regimeTrabalho = RegimeTrabalho.valueOf(scanner.nextLine().toUpperCase());

                    int tempoCarreiraTec = LocalDate.now().getYear() - inicioCarreiraTec.getYear();

                    tecnicosAdm.add(new TecnicoADM(nome, cpf, matricula, dataNascimento, campus, titulacaoTec, inicioCarreiraTec, efetivoTec, tempoCarreiraTec, LocalDate.now(), regimeTrabalho));
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }


    // 🔹 Método para exibir a titulação e capturar a escolha do usuário
    private Titulacao perguntarTitulacao() {
        System.out.println("Escolha a titulação:");
        System.out.println("1 - GRADUAÇÃO");
        System.out.println("2 - ESPECIALIZAÇÃO");
        System.out.println("3 - MESTRADO");
        System.out.println("4 - DOUTORADO");
        System.out.print("Opção: ");

        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        switch (escolha) {
            case 1:
                return Titulacao.GRADUACAO;
            case 2:
                return Titulacao.ESPECIALIZACAO;
            case 3:
                return Titulacao.MESTRADO;
            case 4:
                return Titulacao.DOUTORADO;
            default:
                System.out.println("Opção inválida! Usando padrão: GRADUAÇÃO.");
                return Titulacao.GRADUACAO;
        }
    }

}
