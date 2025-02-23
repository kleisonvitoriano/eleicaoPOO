import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
   
    Scanner scanner = new Scanner(System.in);
        
        // Criando listas separadas para armazenar os eleitores
        List<Aluno> alunos = new ArrayList<>();
        List<Docente> docentes = new ArrayList<>();
        List<TecnicoAdministrativo> tecnicoADM = new ArrayList<>();

        // Criando a instância do CadastroEleitores e chamando o método de cadastro
        CadastroEleitores cadastro = new CadastroEleitores();
        cadastro.cadastrarEleitores(alunos, docentes, tecnicoADM);

        // Lista unificada para a urna
        List<Pessoa> eleitores = new ArrayList<>();
        eleitores.addAll(alunos);
        eleitores.addAll(docentes);
        eleitores.addAll(tecnicoADM);

        // Criando a urna com um responsável (um dos docentes)
        if (!docentes.isEmpty()) {
            UrnaEletronica urna = new UrnaEletronica(docentes.get(0));

            // Gerando a lista de eleitores
            urna.gerarListaEleitores(eleitores);

            // Iniciando a eleição
            urna.iniciarEleicao(eleitores);

            // Simulando candidaturas e votos (exemplo básico)
            if (!docentes.isEmpty()) {
                Pre_Candidato preCand1 = new Pre_Candidato(docentes.get(0));
                Candidato cand1 = preCand1.oficializarCandidatura();
                if (cand1 != null) {
                    urna.registrarCandidato(cand1);
                }
            }

            if (!tecnicoADM.isEmpty()) {
                Pre_Candidato preCand2 = new Pre_Candidato(tecnicoADM.get(0));
                Candidato cand2 = preCand2.oficializarCandidatura();
                if (cand2 != null) {
                    urna.registrarCandidato(cand2);
                }
            }

            // Finalizando a eleição
            urna.finalizarEleicao();
        } else {
            System.out.println("Nenhum docente cadastrado. A eleição não pode ser iniciada.");
        }

        scanner.close();
   
   
   
   
   
   
   
   
   
        //     // Criação de eleitores
    //     List<Pessoa> eleitores = new ArrayList<>();

    //     // Alunos
    //     eleitores.add(new Aluno("João", "Nova Cruz", "77777777 ", LocalDate.of(2006,03,11), "7676767", "TI"));
    //     eleitores.add(new Aluno("maria", "Nova Cruz", "8888888 ", LocalDate.of(2006,03,11), "5656565", "quimica"));

    //     // Docentes
    //     Docente docente1 = new Docente("Carlos", "Noca Cruz", "444444", LocalDate.of(1987,04,11), "666666", Titulacao.ESPECIALIZACAO, true,7, 2  );
    //     Docente docente2 = new Docente("jose", "Noca Cruz", "4456444", LocalDate.of(1987,04,11), "645666", Titulacao.ESPECIALIZACAO, true,7, 2  );
    //     eleitores.add(docente1);
    //     eleitores.add(docente2);

    //     // Técnico Administrativo
    //     TecnicoAdministrativo tecnico1 = new TecnicoAdministrativo("Ronaldo", "Nova Cruz", "888-999", LocalDate.of(2000, 12,01), "34534", Titulacao.ESPECIALIZACAO, true, 2, 5, RegimeTrabalho.PRESENCIAL);
    //     eleitores.add(tecnico1);

    //     // Cria a urna com um responsável (pode ser qualquer Servidor)
    //     Docente responsavel = new Docente("Carlos", "Noca Cruz", "444444", LocalDate.of(1987,04,11), "666666", Titulacao.ESPECIALIZACAO, true,7, 2  );
    //     UrnaEletronica urna = new UrnaEletronica(responsavel);

    //     // Gera o arquivo com a lista de eleitores
    //     urna.gerarListaEleitores(eleitores);

    //     // Inicia a eleição
    //     urna.iniciarEleicao(eleitores);

    //     // Faz pré-candidatura do docente1
    //     Pre_Candidato preCand1 = new Pre_Candidato(docente1);
    //     Candidato cand1 = preCand1.oficializarCandidatura();
    //     if (cand1 != null) {
    //         urna.registrarCandidato(cand1);
    //     }

    //     // Faz pré-candidatura do tecnico1
    //     Pre_Candidato preCand2 = new Pre_Candidato(tecnico1);
    //     Candidato cand2 = preCand2.oficializarCandidatura();
    //     if (cand2 != null) {
    //         urna.registrarCandidato(cand2);
    //     }

    //     // Simula votos
    //     // - matricula, numero do candidato (0 = branco)
    //     if (cand1 != null) {
    //         urna.registrarVoto("A001", cand1.getNumero()); // João vota no cand1
    //     }
    //     if (cand2 != null) {
    //         urna.registrarVoto("A002", cand2.getNumero()); // Maria vota no cand2
    //     }
    //     if (cand1 != null) {
    //         urna.registrarVoto("S001", cand1.getNumero()); // Carlos (docente1) vota nele mesmo
    //     }
    //     if (cand2 != null) {
    //         urna.registrarVoto("S002", cand2.getNumero()); // jose vota no cand2
    //     }
    //     if (cand2 != null) {
    //         urna.registrarVoto("S003", cand2.getNumero()); // ronaldo vota no cand2
    //     }

    //     // Finaliza a eleição
    //     urna.finalizarEleicao();
    }
}
