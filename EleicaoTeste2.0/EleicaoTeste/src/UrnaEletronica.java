import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class UrnaEletronica {
    private List<Candidato> candidatos;
    private Set<String> eleitoresVotantes;
    private Set<String> eleitoresQueVotaram;
    private Servidor responsavel;
    private boolean votacaoEmAndamento;
    private int votosBrancos;
    private int votosNulos;
    private Map<String, Integer> totaisPorCategoria;
    private Map<String, String> categoriaPorMatricula;

    public UrnaEletronica(Servidor responsavel) {
        this.responsavel = responsavel;
        this.candidatos = new ArrayList<>();
        this.eleitoresVotantes = new HashSet<>();
        this.eleitoresQueVotaram = new HashSet<>();
        this.votacaoEmAndamento = false;
        this.votosBrancos = 0;
        this.votosNulos = 0;
        this.totaisPorCategoria = new HashMap<>();
        this.categoriaPorMatricula = new HashMap<>();
    }

    public void gerarListaEleitores(List<Pessoa> eleitores) {
        List<Pessoa> discentes = new ArrayList<>();
        List<Pessoa> docentes = new ArrayList<>();
        List<Pessoa> tecnicos = new ArrayList<>();

        for (Pessoa p : eleitores) {
            if (p instanceof Aluno) {
                discentes.add(p);
            } else if (p instanceof Docente) {
                docentes.add(p);
            } else if (p instanceof TecnicoAdministrativo) {
                tecnicos.add(p);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("lista_eleitores.txt"))) {
            writer.write("DISCENTES:\n");
            for (Pessoa p : discentes) {
                writer.write(p.getMatricula() + " - " + p.getNome() + "\n");
            }
            writer.write("\nDOCENTES:\n");
            for (Pessoa p : docentes) {
                writer.write(p.getMatricula() + " - " + p.getNome() + "\n");
            }
            writer.write("\nTÉCNICOS-ADMINISTRATIVOS:\n");
            for (Pessoa p : tecnicos) {
                writer.write(p.getMatricula() + " - " + p.getNome() + "\n");
            }
            System.out.println("Arquivo 'lista_eleitores.txt' gerado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao gerar lista de eleitores: " + e.getMessage());
        }
    }
    
    public void iniciarEleicao(List<Pessoa> eleitores) {
        eleitoresVotantes.clear();
        eleitoresQueVotaram.clear();
        totaisPorCategoria.clear();
        categoriaPorMatricula.clear();
        votosBrancos = 0;
        votosNulos = 0;

        for (Pessoa p : eleitores) {
            if (p.getMatricula() == null || p.getMatricula().trim().isEmpty()) continue;
            String categoria = null;
            if (p instanceof Aluno) {
                categoria = "DISCENTE";
            } else if (p instanceof Docente) {
                categoria = "DOCENTE";
            } else if (p instanceof TecnicoAdministrativo) {
                categoria = "TECNICO";
            }
            if (categoria != null) {
                eleitoresVotantes.add(p.getMatricula());
                categoriaPorMatricula.put(p.getMatricula(), categoria);
                totaisPorCategoria.put(categoria, totaisPorCategoria.getOrDefault(categoria, 0) + 1);
            }
        }
        votacaoEmAndamento = true;
        System.out.println("Eleição iniciada! Totais por categoria: " + totaisPorCategoria);
    }
    
    public void iniciarVotacao(String matriculaResponsavel, List<Pessoa> eleitores) {
        if (matriculaResponsavel == null || matriculaResponsavel.trim().isEmpty()) {
            System.out.println("Matrícula do responsável não pode ser vazia.");
            return;
        }
        if (!responsavel.getMatricula().equals(matriculaResponsavel)) {
            System.out.println("Apenas o responsável pode iniciar a votação.");
            return;
        }
        iniciarEleicao(eleitores);
    }
    
    public void registrarCandidato(Candidato candidato) {
        if(candidato != null) {
            candidatos.add(candidato);
        }
    }
    
    public boolean podeVotar(String matricula) {
        return votacaoEmAndamento &&
               matricula != null &&
               !matricula.trim().isEmpty() &&
               eleitoresVotantes.contains(matricula) &&
               !eleitoresQueVotaram.contains(matricula);
    }
    
    public void registrarVoto(String matricula, int numeroVoto) {
        if (!votacaoEmAndamento) {
            System.out.println("Votação não está em andamento.");
            return;
        }
        if (!podeVotar(matricula)) {
            System.out.println("Voto não permitido para matrícula: " + matricula);
            return;
        }
        if (numeroVoto == 0) {
            votosBrancos++;
        } else {
            boolean candidatoEncontrado = false;
            for (Candidato c : candidatos) {
                if (c.getNumero() == numeroVoto) {
                    String categoria = categoriaPorMatricula.get(matricula);
                    c.registrarVoto(categoria);
                    candidatoEncontrado = true;
                    break;
                }
            }
            if (!candidatoEncontrado) {
                votosNulos++;
            }
        }
        eleitoresQueVotaram.add(matricula);
    }
    
    public void finalizarVotacao(String matriculaResponsavel) {
        if (matriculaResponsavel == null || matriculaResponsavel.trim().isEmpty()) {
            System.out.println("Matrícula do responsável não pode ser vazia.");
            return;
        }
        if (!responsavel.getMatricula().equals(matriculaResponsavel)) {
            System.out.println("Apenas o responsável pode finalizar a votação.");
            return;
        }
        finalizarEleicao();
    }
    
    public void finalizarEleicao() {
        if (!votacaoEmAndamento) {
            System.out.println("A eleição já foi finalizada ou nem foi iniciada.");
            return;
        }
        votacaoEmAndamento = false;
        
        int totalDocentes = totaisPorCategoria.getOrDefault("DOCENTE", 0);
        int totalDiscentes = totaisPorCategoria.getOrDefault("DISCENTE", 0);
        int totalTecnicos = totaisPorCategoria.getOrDefault("TECNICO", 0);
        
        System.out.println("=== Fim da Votação ===");
        System.out.println("Votos em branco: " + votosBrancos);
        System.out.println("Votos nulos: " + votosNulos);
        System.out.println("Resultados dos candidatos:");
        for (Candidato c : candidatos) {
            float percentual = c.calcularPercentual(totalDocentes, totalDiscentes, totalTecnicos);
            System.out.printf("Candidato %d (%s) - Percentual: %.2f%%, Votos Totais: %d\n",
                    c.getNumero(), c.getCor(), percentual, c.getTotalVotosAbsolutos());
        }
    }
    
    public void gerarListaAusentes(List<Pessoa> eleitores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ausentes.txt"))) {
            writer.write("Lista de eleitores ausentes:\n");
            for (Pessoa p : eleitores) {
                if (!eleitoresQueVotaram.contains(p.getMatricula())) {
                    writer.write(p.getMatricula() + " - " + p.getNome() + "\n");
                }
            }
            System.out.println("Arquivo 'ausentes.txt' gerado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao gerar lista de ausentes: " + e.getMessage());
        }
    }

    public List<Candidato> getCandidatos() {
        return Collections.unmodifiableList(candidatos);
    }

    public int getVotosBrancos() {
        return votosBrancos;
    }
    
    public int getVotosNulos() {
        return votosNulos;
    }
    
    public int getTotalDocentes() {
        return totaisPorCategoria.getOrDefault("DOCENTE", 0);
    }
    
    public int getTotalDiscentes() {
        return totaisPorCategoria.getOrDefault("DISCENTE", 0);
    }
    
    public int getTotalTecnicos() {
        return totaisPorCategoria.getOrDefault("TECNICO", 0);
    }
}
