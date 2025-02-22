package eleicao_real;

import java.util.Random;

public class Candidato {
    private Servidor servidor;
    private String nome;
    private int numero;
    private String cor;
    private int votosDocentes;
    private int votosDiscentes;
    private int votosTecnicos;

    
   
    
    // public Candidato(Servidor servidor, int numero, String cor) {
    //     this.setServidor(servidor);
    //     this.setNumero(numero);
    //     this.setCor(cor);
    //     this.votosDocentes = 0;
    //     this.votosDiscentes = 0;
    //     this.votosTecnicos = 0;
    // }

    public Candidato(Servidor servidor) {
        if (!validarInscricao(servidor)) {
            System.out.println("O servidor não atende aos requisitos para ser candidato.");
        } else {
            System.out.println("Inscrição aceita!");
        }

        this.servidor = servidor;
        this.numero = gerarNumeroCandidato();
        this.cor = escolherCor();
        this.votosDocentes = 0;
        this.votosDiscentes = 0;
        this.votosTecnicos = 0;
    }

    public boolean validarInscricao(Servidor servidor) {
        if (servidor.getIdade() < 35 || servidor.getinicio_carreira() < 5) {
            return false; // Inscrição inválida
        }
        if (servidor.getTitulacao() != Titulacao.DOUTORADO && servidor.getTitulacao() != Titulacao.MESTRADO) {
            return false; // Inscrição inválida
        }
        return true; // Inscrição válida
    }


    private int gerarNumeroCandidato() {
        Random random = new Random();
        return 1000 + random.nextInt(9000); // Número entre 1000 e 9999
    }

    private String escolherCor() {
        String[] cores = {"azul", "amarelo", "vermelho", "verde", "roxo", "rosa", "laranja"};
        Random random = new Random();
        return cores[random.nextInt(cores.length)];
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
