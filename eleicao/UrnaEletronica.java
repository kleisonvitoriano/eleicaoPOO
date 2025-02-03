package eleicao;

import java.util.*;
import java.io.*;

public class UrnaEletronica {
    private List<Candidato> candidatos;
    private Set<String> eleitoresVotantes;
    private Set<String> eleitoresQueVotaram;
    private Servidor responsavel;
    private boolean votacaoEmAndamento;
    private int votosBrancos;
    private int votosNulos;
    private Map<String, Integer> totaisPorCategoria;

    public UrnaEletronica(Servidor responsavel) {
        this.responsavel = responsavel;
        this.candidatos = new ArrayList<>();
        this.eleitoresVotantes = new HashSet<>();
        this.eleitoresQueVotaram = new HashSet<>();
        this.votacaoEmAndamento = false;
        this.votosBrancos = 0;
        this.votosNulos = 0;
        this.totaisPorCategoria = new HashMap<>();
    }

    public void iniciarEleicao(List<Pessoa> eleitores) throws IOException {
        if (candidatos.isEmpty()) {
            throw new IllegalStateException("Não há candidatos registrados");
        }

        votacaoEmAndamento = true;
        eleitoresVotantes.clear();
        eleitoresQueVotaram.clear();
        totaisPorCategoria.clear();

        for (Pessoa eleitor : eleitores) {
            if (eleitor instanceof Aluno) {
                totaisPorCategoria.merge("DISCENTE", 1, Integer::sum);
                eleitoresVotantes.add(eleitor.getMatricula());
            } else if (eleitor instanceof Servidor && ((Servidor) eleitor).isEfetivo()) {
                if (eleitor instanceof Docente) {
                    totaisPorCategoria.merge("DOCENTE", 1, Integer::sum);
                } else {
                    totaisPorCategoria.merge("TECNICO", 1, Integer::sum);
                }
                eleitoresVotantes.add(eleitor.getMatricula());
            }
        }

        gerarZeresima();
    }

    public void registrarCandidato(Candidato candidato) {
        if (votacaoEmAndamento) {
            throw new IllegalStateException("Não é possível registrar candidatos durante a votação");
        }
        candidatos.add(candidato);
    }

    public boolean podeVotar(String matricula) {
        return eleitoresVotantes.contains(matricula) && !eleitoresQueVotaram.contains(matricula);
    }

    public void registrarVoto(String matricula, int numeroVoto) {
        if (!votacaoEmAndamento) {
            throw new IllegalStateException("Votação não está em andamento");
        }
        if (!podeVotar(matricula)) {
            throw new IllegalStateException("Eleitor não pode votar");
        }

        String categoria = determinarCategoria(matricula);
        if (numeroVoto == 0) {
            votosBrancos++;
        } else {
            boolean votoRegistrado = false;
            for (Candidato candidato : candidatos) {
                if (candidato.getNumero() == numeroVoto) {
                    candidato.registrarVoto(categoria);
                    votoRegistrado = true;
                    break;
                }
            }
            if (!votoRegistrado) {
                votosNulos++;
            }
        }
        eleitoresQueVotaram.add(matricula);
    }

    private String determinarCategoria(String matricula) {
        if (matricula.startsWith("D")) return "DOCENTE";
        if (matricula.startsWith("T")) return "TECNICO";
        return "DISCENTE";
    }

    public void finalizarEleicao() throws IOException {
        if (!this.votacaoEmAndamento || this.responsavel == null) {
            throw new IllegalStateException("Votação não está em andamento");
        }
        if(this.responsavel != null){
            this.votacaoEmAndamento = false;
        }
        
        gerarResultadoFinal();
    }

    private void gerarZeresima() throws IOException {
        try (PrintWriter writer = new PrintWriter("zeresima.txt")) {
            writer.println("ZERÉSIMA DA ELEIÇÃO");
            writer.println("===================");
            writer.println("\nCandidatos:");
            for (Candidato candidato : candidatos) {
                writer.printf("Número: %d, Nome: %s, Votos: 0%n", 
                    candidato.getNumero(), candidato.getServidor().getNome());
            }
            writer.println("\nQuantidade de eleitores por categoria:");
            writer.printf("Docentes: %d%n", totaisPorCategoria.getOrDefault("DOCENTE", 0));
            writer.printf("Técnicos: %d%n", totaisPorCategoria.getOrDefault("TECNICO", 0));
            writer.printf("Discentes: %d%n", totaisPorCategoria.getOrDefault("DISCENTE", 0));
        }
    }

    private void gerarResultadoFinal() throws IOException {
        try (PrintWriter writer = new PrintWriter("resultado_eleicao.txt")) {
            writer.println("RESULTADO FINAL DA ELEIÇÃO");
            writer.println("==========================");
            
            List<ResultadoCandidato> resultados = new ArrayList<>();
            for (Candidato candidato : candidatos) {
                double percentual = candidato.calcularPercentual(
                    totaisPorCategoria.getOrDefault("DOCENTE", 0),
                    totaisPorCategoria.getOrDefault("DISCENTE", 0),
                    totaisPorCategoria.getOrDefault("TECNICO", 0)
                );
                resultados.add(new ResultadoCandidato(candidato, percentual));
            }

            
            resultados.sort((r1, r2) -> {
                int compare = Double.compare(r2.percentual, r1.percentual);
                if (compare == 0) {
               
                    
                    compare = Integer.compare(r2.candidato.getTotalVotosAbsolutos(), 
                                           r1.candidato.getTotalVotosAbsolutos());
                    if (compare == 0) {
        
                        if (r1.candidato.getServidor() instanceof Docente && 
                            r2.candidato.getServidor() instanceof Docente) {
                            compare = Integer.compare(
                                ((Docente)r2.candidato.getServidor()).getTempoEfetivoServico(),
                                ((Docente)r1.candidato.getServidor()).getTempoEfetivoServico()
                            );
                        }
                        if (compare == 0) {
                           
                            compare = Integer.compare(
                                r2.candidato.getServidor().getIdade(),
                                r1.candidato.getServidor().getIdade()
                            );
                        }
                    }
                }
                return compare;
            });

            for (ResultadoCandidato resultado : resultados) {
                writer.printf("Candidato: %s%n", resultado.candidato.getServidor().getNome());
                writer.printf("Percentual: %.2f%%%n", resultado.percentual);
                writer.printf("Votos absolutos: %d%n", resultado.candidato.getTotalVotosAbsolutos());
                writer.println();
            }

            writer.println("\nEstatísticas gerais:");
            writer.printf("Total de votos: %d%n", eleitoresQueVotaram.size() + votosBrancos + votosNulos);
            writer.printf("Abstenções: %d%n", eleitoresVotantes.size() - eleitoresQueVotaram.size());
            writer.printf("Votos em branco: %d%n", votosBrancos);
            writer.printf("Votos nulos: %d%n", votosNulos);
        }

        gerarListaAusentes();
    }

    private void gerarListaAusentes() throws IOException {
        try (PrintWriter writer = new PrintWriter("ausentes.txt")) {
            writer.println("LISTA DE ELEITORES AUSENTES");
            writer.println("===========================");
            
            Set<String> ausentes = new HashSet<>(eleitoresVotantes);
            ausentes.removeAll(eleitoresQueVotaram);
            
            for (String matricula : ausentes) {
                writer.printf("Matrícula: %s%n", matricula);
            }
        }
    }

    private static class ResultadoCandidato {
        Candidato candidato;
        double percentual;

        ResultadoCandidato(Candidato candidato, double percentual) {
            this.candidato = candidato;
            this.percentual = percentual;
        }
    }
}
