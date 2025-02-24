import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {
    // Método auxiliar para ler uma String não vazia
    private static String lerString(Scanner scanner, String mensagem) {
        String valor;
        do {
            System.out.print(mensagem);
            valor = scanner.nextLine().trim();
            if (valor.isEmpty()) {
                System.out.println("Entrada inválida! Tente novamente.");
            }
        } while (valor.isEmpty());
        return valor;
    }
    
    // Método auxiliar para ler um inteiro com validação
    private static int lerInt(Scanner scanner, String mensagem) {
        int num = 0;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print(mensagem);
                num = Integer.parseInt(scanner.nextLine().trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Valor numérico inválido! Tente novamente.");
            }
        }
        return num;
    }
    
    // Método auxiliar para ler uma data
    private static LocalDate lerData(Scanner scanner, String mensagem) {
        LocalDate data = null;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print(mensagem);
                data = LocalDate.parse(scanner.nextLine().trim());
                valido = true;
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Formato esperado: yyyy-MM-dd.");
            }
        }
        return data;
    }
    
    // Adicionar após os métodos auxiliares e antes do main
    private static void cadastrarCandidatosPredefinidos(List<Pessoa> eleitores, UrnaEletronica urna) {
        Random random = new Random();
        // Gerar 3 números aleatórios únicos entre 1 e 100
        Set<Integer> numerosUsados = new HashSet<>();
        int num1, num2, num3;
        
        do {
            num1 = random.nextInt(100) + 1;
        } while (numerosUsados.contains(num1));
        numerosUsados.add(num1);
        
        do {
            num2 = random.nextInt(100) + 1;
        } while (numerosUsados.contains(num2));
        numerosUsados.add(num2);
        
        do {
            num3 = random.nextInt(100) + 1;
        } while (numerosUsados.contains(num3));
        numerosUsados.add(num3);
        
        // Candidato 1 - Docente
        Docente candidato1 = new Docente(
            "João Silva", "11111111111", 
            LocalDate.of(1980, 1, 1), String.valueOf(num1), 
            "Campus Central", Titulacao.DOUTORADO, 
            true, 10, 5);
        eleitores.add(candidato1);
        Candidato cand1 = new Candidato(candidato1, num1, "Azul");
        urna.registrarCandidato(cand1);
        
        // Candidato 2 - Docente
        Docente candidato2 = new Docente(
            "Maria Santos", "22222222222", 
            LocalDate.of(1975, 6, 15), String.valueOf(num2), 
            "Campus Central", Titulacao.DOUTORADO, 
            true, 15, 8);
        eleitores.add(candidato2);
        Candidato cand2 = new Candidato(candidato2, num2, "Verde");
        urna.registrarCandidato(cand2);
        
        // Candidato 3 - Técnico Administrativo
        TecnicoAdministrativo candidato3 = new TecnicoAdministrativo(
            "Pedro Oliveira", "33333333333", 
            LocalDate.of(1978, 3, 20), String.valueOf(num3), 
            "Campus Central", Titulacao.MESTRADO, 
            true, 12, 6);
        eleitores.add(candidato3);
        Candidato cand3 = new Candidato(candidato3, num3, "Vermelho");
        urna.registrarCandidato(cand3);
        
        System.out.println("\n--- Candidatos Disponíveis ---");
        System.out.println(num1 + " - João Silva (Cor: Azul)");
        System.out.println(num2 + " - Maria Santos (Cor: Verde)");
        System.out.println(num3 + " - Pedro Oliveira (Cor: Vermelho)");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Pessoa> eleitores = new ArrayList<>();
        
        // Cadastro do responsável primeiro
        System.out.println("--- Cadastro do Responsável ---");
        String nomeResp = lerString(scanner, "Digite o nome do responsável: ");
        String cpfResp = lerString(scanner, "Digite o CPF do responsável: ");
        LocalDate dataResp = lerData(scanner, "Digite a data de nascimento (yyyy-MM-dd): ");
        String matriculaResp = lerString(scanner, "Digite a matrícula do responsável: ");
        String campusResp = lerString(scanner, "Digite o campus do responsável: ");
        int titResp = lerInt(scanner, "Digite a titulação (1: GRADUACAO, 2: ESPECIALIZACAO, 3: MESTRADO, 4: DOUTORADO): ");
        Titulacao titulacaoResp = Titulacao.GRADUACAO;
        if (titResp == 2) titulacaoResp = Titulacao.ESPECIALIZACAO;
        else if (titResp == 3) titulacaoResp = Titulacao.MESTRADO;
        else if (titResp == 4) titulacaoResp = Titulacao.DOUTORADO;
        boolean efetivoResp = Boolean.parseBoolean(lerString(scanner, "É efetivo? (true/false): "));
        int tempoEfetivoResp = lerInt(scanner, "Digite o tempo de serviço efetivo (em anos): ");
        int tempoGestaoResp = lerInt(scanner, "Digite o tempo em cargo de gestão (em anos): ");
        Docente respEleicao = new Docente(nomeResp, cpfResp, dataResp, matriculaResp, campusResp, titulacaoResp, efetivoResp, tempoEfetivoResp, tempoGestaoResp);
        
        // Adiciona o responsável à lista
        eleitores.add(respEleicao);
        
        // Cria a urna com o responsável
        UrnaEletronica urna = new UrnaEletronica(respEleicao);
        
        // Cadastra os candidatos pré-definidos
        cadastrarCandidatosPredefinidos(eleitores, urna);
        
        // Cadastro interativo de pessoas (remover opção 4 - Candidato)
        System.out.println("\n--- Cadastro de Pessoas ---");
        String resp = lerString(scanner, "Deseja cadastrar uma pessoa? (S/N): ");
        while (resp.equalsIgnoreCase("S")) {
            int tipo = lerInt(scanner, "Digite 1 para Aluno, 2 para Docente, 3 para Técnico Administrativo: ");
            String nome = lerString(scanner, "Digite o nome: ");
            String cpf = lerString(scanner, "Digite o CPF: ");
            LocalDate dataNascimento = lerData(scanner, "Digite a data de nascimento (yyyy-MM-dd): ");
            String matricula = lerString(scanner, "Digite a matrícula: ");
            
            if (tipo == 1) { // Aluno
                String curso = lerString(scanner, "Digite o curso: ");
                eleitores.add(new Aluno(nome, cpf, dataNascimento, matricula, curso));
            } else if (tipo == 2) { // Docente
                String campus = lerString(scanner, "Digite o campus: ");
                int tit = lerInt(scanner, "Digite a titulação (1: GRADUACAO, 2: ESPECIALIZACAO, 3: MESTRADO, 4: DOUTORADO): ");
                Titulacao titulacao = Titulacao.GRADUACAO;
                if (tit == 2) titulacao = Titulacao.ESPECIALIZACAO;
                else if (tit == 3) titulacao = Titulacao.MESTRADO;
                else if (tit == 4) titulacao = Titulacao.DOUTORADO;
                boolean efetivo = Boolean.parseBoolean(lerString(scanner, "É efetivo? (true/false): "));
                int tempoEfetivo = lerInt(scanner, "Digite o tempo de serviço efetivo (em anos): ");
                int tempoGestao = lerInt(scanner, "Digite o tempo em cargo de gestão (em anos): ");
                eleitores.add(new Docente(nome, cpf, dataNascimento, matricula, campus, titulacao, efetivo, tempoEfetivo, tempoGestao));
            } else if (tipo == 3) { // Técnico Administrativo
                String campus = lerString(scanner, "Digite o campus: ");
                int tit = lerInt(scanner, "Digite a titulação (1: GRADUACAO, 2: ESPECIALIZACAO, 3: MESTRADO, 4: DOUTORADO): ");
                Titulacao titulacao = Titulacao.GRADUACAO;
                if (tit == 2) titulacao = Titulacao.ESPECIALIZACAO;
                else if (tit == 3) titulacao = Titulacao.MESTRADO;
                else if (tit == 4) titulacao = Titulacao.DOUTORADO;
                boolean efetivo = Boolean.parseBoolean(lerString(scanner, "É efetivo? (true/false): "));
                int tempoEfetivo = lerInt(scanner, "Digite o tempo de serviço efetivo (em anos): ");
                int tempoGestao = lerInt(scanner, "Digite o tempo em cargo de gestão (em anos): ");
                eleitores.add(new TecnicoAdministrativo(nome, cpf, dataNascimento, matricula, campus, titulacao, efetivo, tempoEfetivo, tempoGestao));
            }
            
            resp = lerString(scanner, "Deseja cadastrar outra pessoa? (S/N): ");
        }
        
        // Gera o arquivo com a lista de eleitores (organizado por segmento)
        urna.gerarListaEleitores(eleitores);
        
        // O responsável inicia a votação
        String matRespIni = lerString(scanner, "Digite a matrícula do responsável para iniciar a votação: ");
        urna.iniciarVotacao(matRespIni, eleitores);
        
        // Processo de votação
        System.out.println("\n--- Início da Votação ---");
        boolean continuarVotacao = true;
        
        while (continuarVotacao) {
            // Mostra os candidatos disponíveis
            System.out.println("\nCandidatos disponíveis:");
            System.out.println("0 - Voto em branco");
            for (Candidato candidato : urna.getCandidatos()) {
                System.out.printf("%d - %s (Cor: %s)\n", 
                    candidato.getNumero(),
                    candidato.getServidor().getNome(),
                    candidato.getCor());
            }
            
            // Coleta o voto
            String matVotante = lerString(scanner, "\nDigite sua matrícula para votar (ou 'FIM' para encerrar): ");
            
            if (matVotante.equalsIgnoreCase("FIM")) {
                continuarVotacao = false;
            } else {
                int numVoto = lerInt(scanner, "Digite o número do candidato conforme lista acima: ");
                urna.registrarVoto(matVotante, numVoto);
            }
        }
        
        // O responsável finaliza a votação
        String matRespFim = lerString(scanner, "\nDigite a matrícula do responsável para finalizar a votação: ");
        urna.finalizarVotacao(matRespFim);
        
        // Gera arquivo com resultados
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultados.txt"))) {
            writer.write("=== Resultado da Votação ===\n\n");
            writer.write("Votos em branco: " + urna.getVotosBrancos() + "\n");
            writer.write("Votos nulos: " + urna.getVotosNulos() + "\n\n");
            writer.write("Resultados por candidato:\n");
            
            for (Candidato c : urna.getCandidatos()) {
                writer.write(String.format("Candidato %d - %s (Cor: %s)\n", 
                    c.getNumero(), 
                    c.getServidor().getNome(),
                    c.getCor()));
                writer.write(String.format("Percentual: %.2f%%\n", 
                    c.calcularPercentual(
                        urna.getTotalDocentes(),
                        urna.getTotalDiscentes(),
                        urna.getTotalTecnicos())));
                writer.write(String.format("Total de votos: %d\n\n", 
                    c.getTotalVotosAbsolutos()));
            }
            
            System.out.println("Arquivo 'resultados.txt' gerado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao gerar arquivo de resultados: " + e.getMessage());
        }
        
        // Gera a lista de ausentes
        urna.gerarListaAusentes(eleitores);
        
        scanner.close();
    }
}
