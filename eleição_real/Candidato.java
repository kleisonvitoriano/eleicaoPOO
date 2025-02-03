package eleição_real;

public class Candidato {
    private Servidor sevidor;
    private int numero;
    private String cor;
    private int votosDocentes;
    private int votosDiscentes;
    private int votosTecnicos;

    
    
    
    public Candidato(Servidor sevidor, int numero, String cor, int votosDocentes, int votosDiscentes,int votosTecnicos) {
       
        this.sevidor = sevidor;
        this.numero = numero;
        this.cor = cor;
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

    public double calcularPercentual( int totalDocentes, int totalDiscentes, int totalTecnicos) {
       
        double percentualDocentes = totalDocentes == 0 ? 0 : (double) votosDocentes / totalDocentes;
        double percentualDiscentes = totalDiscentes == 0 ? 0 : (double) votosDiscentes / totalDiscentes;
        double percentualTecnicos = totalTecnicos == 0 ? 0 : (double) votosTecnicos / totalTecnicos;
        
        return (100.0 / 3.0) * (percentualDocentes + percentualDiscentes + percentualTecnicos);

        

    }

    


    public Servidor getSevidor() {
        return sevidor;
    }


    public void setSevidor(Servidor sevidor) {
        this.sevidor = sevidor;
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
        
        return numero;

    }
    
}
