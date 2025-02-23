

import java.time.LocalDate;
// import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class CadastroEleitores {
    
    private Scanner scanner;

    public CadastroEleitores() {
        this.scanner = new Scanner(System.in);
    }

    public void cadastrarEleitores(List<Aluno> alunos, List<Docente> docentes, List<TecnicoAdministrativo> tecnicoADM) {
       
      
       
       
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

            System.out.print("Campus: ");
            String campus = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            
            System.out.print("Data de Nascimento (AAAA-MM-DD): ");
            LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());
           
            System.out.print("Matrícula: ");
            String matricula = scanner.nextLine();
           

            

            switch (opcao) {
                case 1: // Cadastro de Aluno
                    System.out.print("Curso: ");
                    String curso = scanner.nextLine();
                    alunos.add(new Aluno(nome,campus, cpf, dataNascimento, matricula, curso));
                    break;
                
                case 2: // Cadastro de Docente
                    Titulacao titulacao = perguntarTitulacao();

                    
                    boolean efetivo = perguntarEfetivo();
                    
                    
                    System.out.print("Tempo de efetivado (em anos): ");
                    int tempoEfetivoServico = scanner.nextInt();
                    
                    System.out.print("Tempo de  cargo na gestão  (em anos): ");
                    int tempoCargoGestao = scanner.nextInt();

                    

                    docentes.add(new Docente(nome, campus, cpf, dataNascimento, matricula,titulacao, efetivo,tempoEfetivoServico , tempoCargoGestao));
                    break;
                
                case 3: // Cadastro de Técnico Administrativo
                    
                Titulacao titulacaoTec = perguntarTitulacao();
                
                    
                
                
                boolean efetivoTec = perguntarEfetivo();
                
                System.out.print("Tempo de efetivado (em anos): ");
                int tempoEfetivoServicotec = scanner.nextInt();
                
                System.out.print("Tempo de  cargo na gestão  (em anos): ");
                int tempoCargoGestaotec = scanner.nextInt();

                RegimeTrabalho regimeTrabalho = perguntarRegimeTrabalho();

                     

                    tecnicoADM.add(new TecnicoAdministrativo(nome, campus, cpf, dataNascimento, matricula, titulacaoTec, efetivoTec, tempoEfetivoServicotec, tempoCargoGestaotec, regimeTrabalho));
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



    private boolean perguntarEfetivo() {
        while (true) {
            System.out.print("É Efetivo? (sim/não): ");
            String resposta = scanner.nextLine().trim().toLowerCase();

            if (resposta.equals("sim")) {
                return true;
            } else if (resposta.equals("não") || resposta.equals("nao")) {
                return false;
            } else {
                System.out.println("Resposta inválida! Digite 'sim' ou 'não'.");
            }
        }
    }

    private RegimeTrabalho perguntarRegimeTrabalho() {
        while (true) {
            System.out.print("Regime de trabalho (PRESENCIAL ou TELETRABALHO): ");
            String resposta = scanner.nextLine().trim().toUpperCase();
    
            if (resposta.equals("PRESENCIAL")) {
                return RegimeTrabalho.PRESENCIAL;
            } else if (resposta.equals("TELETRABALHO")) {
                return RegimeTrabalho.TELETRABALHO;
            } else {
                System.out.println("Resposta inválida! Digite 'PRESENCIAL' ou 'TELETRABALHO'.");
            }
        }
    }

}
