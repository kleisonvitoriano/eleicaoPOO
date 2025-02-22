import java.util.Random;

public class Pre_Candidato {
    private Servidor servidor;
    private boolean inscricaoValida;

   

    public Pre_Candidato(Servidor servidor) {
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
        // Se ainda não validou, tenta validar
        if (!inscricaoValida && !validarInscricao()) {
            System.out.println("Inscrição inválida para candidatura do servidor: " + servidor.getNome());
            return null;
        }
        int numeroCandidato = gerarNumeroCandidato();
        String cor = escolherCor();
        return new Candidato(servidor, numeroCandidato, cor);
    }

    private int gerarNumeroCandidato() {
        Random random = new Random();
    return random.nextInt(100) + 1;
    }

    private String escolherCor() {
        String[] cores = {"Azul", "Vermelho", "Verde", "Amarelo", "Roxo", "Laranja"};
        int index = (int) (Math.random() * cores.length);
        return cores[index];
    }
}
