public class Pre_Candidato {
    private Servidor servidor;
    private boolean inscricaoValida;
    private static int contador = 100;

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
        return contador++;
    }

    private String escolherCor() {
        String[] cores = {"Azul", "Vermelho", "Verde", "Amarelo", "Roxo", "Laranja"};
        int index = (int) (Math.random() * cores.length);
        return cores[index];
    }
}
