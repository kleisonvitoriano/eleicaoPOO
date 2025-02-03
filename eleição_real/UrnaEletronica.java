package eleição_real;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import eleicao.Servidor;

public class UrnaEletronica{
    private List<Candidato> candidatos;
    private Set<String> eleitoresVotantes;
    private Set<String> eleitoresQueVotaram;
    private Servidor responsavel;
    private boolean votacaoEmAndamento;
    private int votosBrancos;
    private int votosNulos;
    private java.util.Map<String, Integer> totaisPorCategoria;

    public UrnaEletronica(List<Candidato> candidatos, Servidor responsavel) {
        this.candidatos = candidatos;
        this.responsavel = responsavel;
        this.eleitoresVotantes = new java.util.HashSet<>();
        this.eleitoresQueVotaram = new java.util.HashSet<>();
        this.votacaoEmAndamento = false;
        this.votosBrancos = 0;
        this.votosNulos = 0;
        this.totaisPorCategoria = new java.util.HashMap<>();
    }

    public void iniciarVotacao() {
        this.votacaoEmAndamento = true;
    }

    public void encerrarVotacao() {
        this.votacaoEmAndamento = false;
    }

    public boolean adicionarEleitor(String cpf) {
        return this.eleitoresVotantes.add(cpf);
    }

    public boolean registrarVoto(String cpf, int numeroCandidato) {
        if (!this.votacaoEmAndamento) {
            return false;
        }
        if (this.eleitoresQueVotaram.contains(cpf)) {
            return false;
        }
        if (numeroCandidato >= 0 && numeroCandidato < this.candidatos.size()) {
            String categoria = this.candidatos.get(numeroCandidato).getCategoria();
            this.totaisPorCategoria.put(categoria, this.totaisPorCategoria.getOrDefault(categoria, 0) + 1);
            this.eleitoresQueVotaram.add(cpf);
            return true;
        } else {
            this.votosNulos++;
            this.eleitoresQueVotaram.add(cpf);
            return true;
        }
    }

    public void registrarVotoBranco(String cpf) {
        if (!this.votacaoEm

    
}
}
