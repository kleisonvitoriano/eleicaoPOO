public class Candidato {
    private Servidor servidor;
    private int numero;
    private String cor;
    private int votosDocentes;
    private int votosDiscentes;
    private int votosTecnicos;

    public Candidato(Servidor servidor, int numero, String cor) {
        this.servidor = servidor;
        this.numero = numero;
        this.cor = cor;
        this.votosDocentes = 0;
        this.votosDiscentes = 0;
        this.votosTecnicos = 0;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public int getNumero() {
        return numero;
    }

    public String getCor() {
        return cor;
    }

    public void registrarVoto(String categoria) {
        if (categoria.equalsIgnoreCase("DOCENTE")) {
            votosDocentes++;
        } else if (categoria.equalsIgnoreCase("DISCENTE")) {
            votosDiscentes++;
        } else if (categoria.equalsIgnoreCase("TECNICO")) {
            votosTecnicos++;
        }
    }

    public float calcularPercentual(int totalDocentes, int totalDiscentes, int totalTecnicos) {
        // Fórmula:  (100 / 3) * ((votosDocentes / totalDocentes) + (votosDiscentes / totalDiscentes) + (votosTecnicos / totalTecnicos))
        float sum = 0f;
        if (totalDocentes > 0) {
            sum += (float)votosDocentes / totalDocentes;
        }
        if (totalDiscentes > 0) {
            sum += (float)votosDiscentes / totalDiscentes;
        }
        if (totalTecnicos > 0) {
            sum += (float)votosTecnicos / totalTecnicos;
        }
        return (100f / 3f) * sum;
    }

    public boolean elegivel_diretorGeral() {
        // Usa o método elegível do servidor (Docente ou Técnico)
        if (servidor instanceof Docente) {
            return ((Docente) servidor).elegivel();
        } else if (servidor instanceof TecnicoAdministrativo) {
            return ((TecnicoAdministrativo) servidor).elegivel();
        }
        return false;
    }

    public int getTotalVotosAbsolutos() {
        return votosDocentes + votosDiscentes + votosTecnicos;
    }
}
