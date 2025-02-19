package eleicao_real;
import java.util.List;
import java.util.Set;
import eleicao.Servidor;

public class UrnaEletronica {
    private List<Candidato> candidatos;
    private Set<Integer> eleitoresVotantes;
    private Set<Integer> eleitoresQueVotaram;
    private Servidor responsavel;
    private boolean votacaoEmAndamento;
    private int votosBrancos;
    private int votosNulos;
    private java.util.Map<String, Integer> votosPorCategoria;
    private int totalEleitores;

    public UrnaEletronica(List<Candidato> candidatos, Servidor responsavel) {
        this.candidatos = candidatos;
        this.responsavel = responsavel;
        this.eleitoresVotantes = new java.util.HashSet<>(List.of(1,2,3,4,5,6,7,8,9,10));
        this.eleitoresQueVotaram = new java.util.HashSet<>();        
        this.votacaoEmAndamento = false;
        this.votosBrancos = 0;
        this.votosNulos = 0;
        this.votosPorCategoria = new java.util.HashMap<>();
        this.totalEleitores = eleitoresVotantes.size();
    }
    public Servidor getResponsavel() {
        return responsavel;
    }
    public void exibirResultados() {
        System.out.println("Número total de votos realizados: " + eleitoresQueVotaram.size());
        System.out.println("Número de eleitores que não compareceram para votar: " + getEleitoresQueNaoVotaram());
        System.out.println("Número absoluto de votos de cada candidato:");
        for (Candidato candidato : candidatos) {
            int votos = votosPorCategoria.getOrDefault(candidato.getCategoria(), 0);
            System.out.println(candidato.getNome() + ": " + votos);
        }
        System.out.println("Votos em branco: " + votosBrancos);
        System.out.println("Votos nulos: " + votosNulos);
    }

    public void setResponsavel(Servidor responsavel) {

        this.responsavel = responsavel;
    }

    public void iniciarVotacao() {
        this.votacaoEmAndamento = true;
        for(Candidato c: this.candidatos){
            this.votosPorCategoria.put(c.getCategoria(), 0);
        }
    }

    public void encerrarVotacao() {
        this.votacaoEmAndamento = false;
    }

    public boolean adicionarEleitor(int matricula) {
        return this.eleitoresVotantes.add(matricula);
    }

    public boolean registrarVoto(int matricula, int numeroCandidato) {
        if (!this.votacaoEmAndamento) {
            return false;
        }
        if (this.eleitoresQueVotaram.contains(matricula)) {
            return false;
        }
        if (numeroCandidato >= 0 && numeroCandidato < this.candidatos.size()) {
            String categoria = this.candidatos.get(numeroCandidato).getCategoria();
            this.votosPorCategoria.put(categoria, this.votosPorCategoria.getOrDefault(categoria, 0) + 1);
            this.eleitoresQueVotaram.add(matricula);
            return true;
        } else {
            this.votosNulos++;
            this.eleitoresQueVotaram.add(matricula);
            return true;
        }
    }

    public void registrarVotoBranco(int matricula) {
        if (!this.eleitoresQueVotaram.contains(matricula)) {
            this.votosBrancos++;
            this.eleitoresQueVotaram.add(matricula);
        }
    }

    public java.util.Map<String, Integer> getVotosPorCategoria() {
        return votosPorCategoria;
    }

    public int getVotosBrancos() {
        return votosBrancos;
    }

    public int getVotosNulos() {
        return votosNulos;
    }

    public int getTotalEleitores() {
        return totalEleitores;
    }
public int getEleitoresQueVotaram(){
    return eleitoresQueVotaram.size();
}

public int getEleitoresQueNaoVotaram(){
    return totalEleitores - eleitoresQueVotaram.size();
}

public List<Candidato> getCandidatos(){
    return candidatos;
}

public double calcularPercentual(String categoria){
    double totalVotos = this.votosPorCategoria.getOrDefault(categoria,0);
    double totalCategoria = this.totalEleitores/3.0;
    return (totalVotos/totalCategoria)*100;
}
}

