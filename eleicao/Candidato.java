package eleicao;

public class Candidato {
    private Servidor servidor;
    private int numero;
    private String cor;
    private int votosDocentes;
    private int votosDiscentes;
    private int votosTecnicos;

    public Candidato(Servidor servidor, int numero, String cor) {
        if (!servidor.isEfetivo()) {
            throw new IllegalArgumentException("Apenas servidores efetivos podem ser candidatos");
        }
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
        switch (categoria.toUpperCase()) {
            case "DOCENTE":
                votosDocentes++;
                break;
            case "DISCENTE":
                votosDiscentes++;
                break;
            case "TECNICO":
                votosTecnicos++;
                break;
        }
    }

    public double calcularPercentual(int totalDocentes, int totalDiscentes, int totalTecnicos) {
        double percentualDocentes = totalDocentes == 0 ? 0 : (double) votosDocentes / totalDocentes;
        double percentualDiscentes = totalDiscentes == 0 ? 0 : (double) votosDiscentes / totalDiscentes;
        double percentualTecnicos = totalTecnicos == 0 ? 0 : (double) votosTecnicos / totalTecnicos;
        
        return (100.0 / 3.0) * (percentualDocentes + percentualDiscentes + percentualTecnicos);
    }

    public int getTotalVotosAbsolutos() {
        return votosDocentes + votosDiscentes + votosTecnicos;
    }

    public int getVotosDocentes() {
        return votosDocentes;
    }

    public int getVotosDiscentes() {
        return votosDiscentes;
    }

    public int getVotosTecnicos() {
        return votosTecnicos;
    }
}
