import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Pre_Candidato {
    private Servidor servidor;
    private boolean inscricaoValida;
    private static Set<Integer> numerosUsados = new HashSet<>();
    private static final String[] CORES = {"Azul", "Vermelho", "Verde", "Amarelo", "Roxo", "Laranja"};
    private static final Random random = new Random();

    public Pre_Candidato(Servidor servidor) {
        if (servidor == null) {
            throw new IllegalArgumentException("Servidor não pode ser nulo para pré-candidatura.");
        }
        this.servidor = servidor;
        this.inscricaoValida = false;
    }

    public boolean validarInscricao() {
        if (servidor instanceof Docente) {
            inscricaoValida = ((Docente) servidor).elegivel();
        } else if (servidor instanceof TecnicoAdministrativo) {
            inscricaoValida = ((TecnicoAdministrativo) servidor).elegivel();
        } else {
            inscricaoValida = false;
        }
        return inscricaoValida;
    }

    public Candidato oficializarCandidatura() {
        if (!inscricaoValida && !validarInscricao()) {
            System.out.println("Inscrição inválida para candidatura do servidor: " + servidor.getNome());
            return null;
        }
        int numeroCandidato = gerarNumeroCandidato();
        String cor = escolherCor();
        return new Candidato(servidor, numeroCandidato, cor);
    }

    private int gerarNumeroCandidato() {
        int numero;
        do {
            numero = random.nextInt(100) + 1; // Gera número de 1 a 100
        } while (numerosUsados.contains(numero));
        numerosUsados.add(numero);
        return numero;
    }

    private String escolherCor() {
        return CORES[random.nextInt(CORES.length)];
    }
}
