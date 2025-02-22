package eleicao_real;


import java.util.Random;
// classe nao sera usada, vou juntar com o candidato 
public class Pre_Candidato {
    private Servidor servidor;
    private boolean inscricaoValida;

    public Pre_Candidato(Servidor servidor) {
        this.servidor = servidor;
        this.inscricaoValida = false; // A inscrição começa como inválida
    }

    
    public boolean validarInscricao() {
        if (servidor.getIdade() >= 35 && servidor.getinicio_carreira() >= 5) {
            if (servidor.getTitulacao() == Titulacao.DOUTORADO || servidor.getTitulacao() == Titulacao.MESTRADO) {
                inscricaoValida = true;
                return true;
            }
        }
        return false; // Agora sempre retorna um valor booleano
    }
    // Método para oficializar a candidatura
    public Candidato oficializarCandidatura() {
        if (inscricaoValida) {
            int numero = gerarNumeroCandidato();
            String cor = escolherCor();
            return new Candidato(servidor, numero, cor); // Corrigido para passar o servidor correto
        } else {
            System.out.println("A inscrição não foi validada.");
            return null;
        }
    }

    // Método para gerar um número identificador aleatório para o candidato
    private int gerarNumeroCandidato() {
        Random random = new Random();
        return random.nextInt(1000); // Número aleatório entre 0 e 999
    }

    // Método para escolher uma cor aleatória para a campanha do candidato
    private String escolherCor() {
        String[] cores = {"azul", "amarelo", "vermelho", "verde", "roxo", "rosa", "laranja"};
        Random random = new Random();
        return cores[random.nextInt(cores.length)];
    }

    // Getters e Setters
    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public boolean isInscricaoValida() {
        return inscricaoValida;
    }

    public void setInscricaoValida(boolean inscricaoValida) {
        this.inscricaoValida = inscricaoValida;
    }
}
