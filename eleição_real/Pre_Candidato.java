package eleição_real;

import java.time.LocalDate;
import java.util.Random;

public class Pre_Candidato extends Servidor {
    private Servidor servidor;
    private boolean inscricaoValida;

    

    public Pre_Candidato(String nome, int cpf, int matricula, LocalDate dataNascimento, String campus,
            eleição_real.Titulacao titulacao, LocalDate inicio_carreira, boolean efetivo, Servidor servidor,
            boolean inscricaoValida) {
        super(nome, cpf, matricula, dataNascimento, campus, titulacao, inicio_carreira, efetivo);
        this.servidor = servidor;
        this.inscricaoValida = inscricaoValida;
    }

    // Método para validar a inscrição
    public boolean validarInscricao() {
        // Requisitos: Idade, tempo de carreira, titulação
        if (servidor.getIdade() >= 35 && servidor.getinicio_carreira() >= 5) {
            // Verificar se o servidor tem doutorado ou pelo menos 2 anos de carreira
            if (servidor.getTitulacao() != null && (servidor.getTitulacao() == Titulacao.DOUTORADO || servidor.getinicio_carreira() >= 2)) {
                inscricaoValida = true;
                return true;
            }
        }
        return false;
    }

    // Método para oficializar a candidatura
    public Candidato oficializarCandidatura() {
        if (inscricaoValida) {
            int numero = gerarNumeroCandidato();
            String cor = escolherCor();
            return new Candidato(servidor, numero, cor);
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
