
import java.util.*;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

public class UrnaEletronica {
    private Servidor servidorResponsavel;
    private boolean votacaoIniciada;
    private List<Candidato> candidatos;
    private List<Pessoa> eleitores;
    private Set<String> matriculasVotantes;
    private Map<Candidato, Map<TipoEleitor, Integer>> votos;
    private Map<TipoEleitor, Integer> votosBrancos;
    private Map<TipoEleitor, Integer> votosNulos;
    
    public UrnaEletronica(Servidor servidorResponsavel) {
        this.servidorResponsavel = servidorResponsavel;
        this.votacaoIniciada = false;
        this.candidatos = new ArrayList<>();
        this.eleitores = new ArrayList<>();
        this.matriculasVotantes = new HashSet<>();
        this.votos = new HashMap<>();
        this.votosBrancos = new HashMap<>();
        this.votosNulos = new HashMap<>();
        
        // Inicializa contadores de votos brancos e nulos
        for (TipoEleitor tipo : TipoEleitor.values()) {
            this.votosBrancos.put(tipo, 0);
            this.votosNulos.put(tipo, 0);
        }
    }
    
    public void iniciarVotacao(String matriculaServidor) throws Exception {
        if (!servidorResponsavel.getMatricula().equals(matriculaServidor)) {
            throw new Exception("Apenas o servidor responsável pode iniciar a votação");
        }
        
        if (votacaoIniciada) {
            throw new Exception("A votação já foi iniciada");
        }
        
        if (candidatos.isEmpty()) {
            throw new Exception("Não há candidatos registrados");
        }
        
        if (eleitores.isEmpty()) {
            throw new Exception("Não há eleitores registrados");
        }
        
        // Inicializa o mapa de votos para cada candidato
        for (Candidato candidato : candidatos) {
            Map<TipoEleitor, Integer> votosPorTipo = new HashMap<>();
            votosPorTipo.put(TipoEleitor.DOCENTE, 0);
            votosPorTipo.put(TipoEleitor.TECNICO, 0);
            votosPorTipo.put(TipoEleitor.DISCENTE, 0);
            votos.put(candidato, votosPorTipo);
        }
        
        votacaoIniciada = true;
        exibirZeresima();
    }
    
    private void exibirZeresima() {
        System.out.println("=== ZERÉSIMA ===");
        System.out.println("Quantidade de eleitores por categoria:");
        
        long docentes = ServidorDocente.getTotalDocentesEfetivos();
        long tecnicos = ServidorTecnico.getTotalTecnicosEfetivos();
        long discentes = Aluno.getTotalAlunos();
        
        System.out.println("Docentes Efetivos: " + docentes);
        System.out.println("Técnicos Efetivos: " + tecnicos);
        System.out.println("Discentes: " + discentes);
        
        System.out.println("\nCandidatos:");
        for (Candidato candidato : candidatos) {
            System.out.println(candidato.getNumero() + " - " + candidato.getNome() + ": 0 votos");
        }
    }
    
    public void finalizarVotacao(String matriculaServidor) throws Exception {
        if (!servidorResponsavel.getMatricula().equals(matriculaServidor)) {
            throw new Exception("Apenas o servidor responsável pode finalizar a votação");
        }
        
        if (!votacaoIniciada) {
            throw new Exception("A votação não foi iniciada");
        }
        
        votacaoIniciada = false;
        exibirResultados();
    }
    
    public void registrarVoto(String matriculaEleitor, int numeroCandidato) throws Exception {
        if (!votacaoIniciada) {
            throw new Exception("A votação não foi iniciada");
        }
        
        if (matriculasVotantes.contains(matriculaEleitor)) {
            throw new Exception("Eleitor já votou");
        }
        
        if (!matriculaEleitor.matches("\\d+")) {
            throw new IllegalArgumentException("A matrícula deve conter apenas números");
        }
        
        Pessoa eleitor = encontrarEleitor(matriculaEleitor);
        if (eleitor == null) {
            throw new Exception("Eleitor não encontrado");
        }
        
        TipoEleitor tipo = determinarTipoEleitor(eleitor);
        
        // Registra o voto
        if (numeroCandidato == 0) { // Voto em branco
            votosBrancos.put(tipo, votosBrancos.get(tipo) + 1);
        } else {
            Candidato candidato = encontrarCandidato(numeroCandidato);
            if (candidato != null) {
                Map<TipoEleitor, Integer> votosCandidato = votos.get(candidato);
                votosCandidato.put(tipo, votosCandidato.get(tipo) + 1);
            } else { // Voto nulo
                votosNulos.put(tipo, votosNulos.get(tipo) + 1);
            }
        }
        
        matriculasVotantes.add(matriculaEleitor);
    }
    
    private Pessoa encontrarEleitor(String matricula) {
        return eleitores.stream()
            .filter(e -> e.getMatricula().equals(matricula))
            .findFirst()
            .orElse(null);
    }

    private Candidato encontrarCandidato(int numero) {
        return candidatos.stream()
            .filter(c -> c.getNumero() == numero)
            .findFirst()
            .orElse(null);
    }

    private TipoEleitor determinarTipoEleitor(Pessoa eleitor) {
        if (eleitor instanceof ServidorDocente) {
            ServidorDocente docente = (ServidorDocente) eleitor;
            if (!docente.isEfetivo()) {
                throw new IllegalArgumentException("Apenas servidores efetivos podem votar");
            }
            return TipoEleitor.DOCENTE;
        } else if (eleitor instanceof ServidorTecnico) {
            ServidorTecnico tecnico = (ServidorTecnico) eleitor;
            if (!tecnico.isEfetivo()) {
                throw new IllegalArgumentException("Apenas servidores efetivos podem votar");
            }
            return TipoEleitor.TECNICO;
        } else if (eleitor instanceof Aluno) {
            return TipoEleitor.DISCENTE;
        }
        throw new IllegalArgumentException("Tipo de eleitor não reconhecido");
    }

    private void exibirResultados() {
        System.out.println("=== RESULTADO DA VOTAÇÃO ===");
        
        // Total de votos realizados
        int totalVotos = matriculasVotantes.size();
        int totalEleitores = eleitores.size();
        
        System.out.println("Total de votos: " + totalVotos);
        System.out.println("Eleitores que não votaram: " + (totalEleitores - totalVotos));
        
        // Resultados por candidato
        for (Candidato candidato : candidatos) {
            Map<TipoEleitor, Integer> votosCandidato = votos.get(candidato);
            int totalVotosCandidato = votosCandidato.values().stream().mapToInt(Integer::intValue).sum();
            
            System.out.println("\nCandidato: " + candidato.getNome());
            System.out.println("Número: " + candidato.getNumero());
            System.out.println("Total de votos: " + totalVotosCandidato);
            System.out.println("Votos por categoria:");
            System.out.println("- Docentes: " + votosCandidato.get(TipoEleitor.DOCENTE));
            System.out.println("- Técnicos: " + votosCandidato.get(TipoEleitor.TECNICO));
            System.out.println("- Discentes: " + votosCandidato.get(TipoEleitor.DISCENTE));
        }
    }

    public void adicionarCandidato(Candidato candidato) {
        if (!votacaoIniciada) {
            candidatos.add(candidato);
        }
    }

    public void adicionarEleitor(Pessoa eleitor) {
        if (!votacaoIniciada) {
            eleitores.add(eleitor);
        }
    }

    public void gerarArquivoEleitores(String nomeArquivo) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            writer.println("=== LISTA DE ELEITORES ===\n");
            
            writer.println("DISCENTES:");
            Aluno.getAlunos().stream()
                .sorted(Comparator.comparing(Pessoa::getNome))
                .forEach(a -> writer.println(a.getMatricula() + " - " + a.getNome()));
                
            writer.println("\nDOCENTES EFETIVOS:");
            ServidorDocente.getDocentes().stream()
                .filter(Servidor::isEfetivo)
                .sorted(Comparator.comparing(Pessoa::getNome))
                .forEach(d -> writer.println(d.getMatricula() + " - " + d.getNome()));
                
            writer.println("\nTÉCNICOS EFETIVOS:");
            ServidorTecnico.getTecnicos().stream()
                .filter(Servidor::isEfetivo)
                .sorted(Comparator.comparing(Pessoa::getNome))
                .forEach(t -> writer.println(t.getMatricula() + " - " + t.getNome()));
                
            writer.println("\nTOTAL DE ELEITORES:");
            writer.println("Discentes: " + Aluno.getTotalAlunos());
            writer.println("Docentes Efetivos: " + ServidorDocente.getTotalDocentesEfetivos());
            writer.println("Técnicos Efetivos: " + ServidorTecnico.getTotalTecnicosEfetivos());
        }
    }
} 