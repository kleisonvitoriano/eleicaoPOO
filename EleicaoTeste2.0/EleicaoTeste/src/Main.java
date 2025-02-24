import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Pessoa> eleitores = new ArrayList<>();
        
        // Cadastro interativo de pessoas
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
        
        // Cadastro do responsável
        System.out.println("\n--- Cadastro do Responsável ---");
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
        
        // Adiciona o responsável à lista, se desejar
        eleitores.add(respEleicao);
        
        // Cria a urna com o responsável
        UrnaEletronica urna = new UrnaEletronica(respEleicao);
        
        // Gera o arquivo com a lista de eleitores (organizado por segmento)
        urna.gerarListaEleitores(eleitores);
        
        // O responsável inicia a votação
        String matRespIni = lerString(scanner, "Digite a matrícula do responsável para iniciar a votação: ");
        urna.iniciarVotacao(matRespIni, eleitores);
        
        // Registro dos candidatos via pré-candidatura (para este exemplo, usamos os primeiros cadastrados que sejam elegíveis)
        if (!eleitores.isEmpty()) {
            Pessoa p = eleitores.get(0);
            if (p instanceof Docente || p instanceof TecnicoAdministrativo) {
                Pre_Candidato preCand = new Pre_Candidato((Servidor) p);
                Candidato cand = preCand.oficializarCandidatura();
                if (cand != null) {
                    urna.registrarCandidato(cand);
                }
            }
        }
        if (eleitores.size() > 1) {
            Pessoa p = eleitores.get(1);
            if (p instanceof Docente || p instanceof TecnicoAdministrativo) {
                Pre_Candidato preCand2 = new Pre_Candidato((Servidor) p);
                Candidato cand2 = preCand2.oficializarCandidatura();
                if (cand2 != null) {
                    urna.registrarCandidato(cand2);
                }
            }
        }
        
        // Votação interativa
        System.out.println("\n--- Votação ---");
        String matVotante = lerString(scanner, "Digite sua matrícula para votar: ");
        int numVoto = lerInt(scanner, "Digite o número do candidato (0 para voto em branco): ");
        urna.registrarVoto(matVotante, numVoto);
        
        // (Opcional) Adicione mais votos conforme necessário...
        
        // O responsável finaliza a votação
        String matRespFim = lerString(scanner, "\nDigite a matrícula do responsável para finalizar a votação: ");
        urna.finalizarVotacao(matRespFim);
        
        // Gera a lista de ausentes
        urna.gerarListaAusentes(eleitores);
        
        scanner.close();
    }
}
