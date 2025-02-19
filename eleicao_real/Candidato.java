package eleicao_real;



public class Candidato {
    private Servidor servidor;
    private String nome;
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

    public float calcularPercentual(int totalDocentes, int totalDiscentes, int totalTecnicos) {
        float percentualDocentes = totalDocentes == 0 ? 0 : (float) votosDocentes / totalDocentes;
        float percentualDiscentes = totalDiscentes == 0 ? 0 : (float) votosDiscentes / totalDiscentes;
        float percentualTecnicos = totalTecnicos == 0 ? 0 : (float) votosTecnicos / totalTecnicos;
    
        return (100.0f / 3.0f) * (percentualDocentes + percentualDiscentes + percentualTecnicos);
    }
    


    public Servidor getServidor() {
        return servidor;
    }


    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }


    public int getNumero() {
        return numero;
    }


    public void setNumero(int numero) {
        this.numero = numero;
    }


    public String getCor() {
        return cor;
    }


    public void setCor(String cor) {
        this.cor = cor;
    }


    public int getVotosDocentes() {
        return votosDocentes;
    }


    public void setVotosDocentes(int votosDocentes) {
        this.votosDocentes = votosDocentes;
    }


    public int getVotosDiscentes() {
        return votosDiscentes;
    }


    public void setVotosDiscentes(int votosDiscentes) {
        this.votosDiscentes = votosDiscentes;
    }


    public int getVotosTecnicos() {
        return votosTecnicos;
    }


    public void setVotosTecnicos(int votosTecnicos) {
        this.votosTecnicos = votosTecnicos;
    }

    public int getVotosAbsolutos() {
        
        return votosDocentes + votosDiscentes + votosTecnicos;

    }




    public String getNome() {
        return nome;
    }




    public void setNome(String nome) {
        this.nome = nome;
    }


    
}
