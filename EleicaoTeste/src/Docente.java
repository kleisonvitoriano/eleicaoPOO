import java.time.LocalDate;


public class Docente extends Servidor {
    private int tempoEfetivoServico;
    private int tempoCargoGestao;


    

    // public Docente(String nome, String cpf, LocalDate dataNascimento, String matricula, String campus, Titulacao titulacao, boolean efetivo,int tempoEfetivoServico, int tempoCargoGestao) {
    //     super(nome, cpf, dataNascimento, matricula, campus, titulacao, efetivo);
    //     this.tempoEfetivoServico = tempoEfetivoServico;
    //     this.tempoCargoGestao = tempoCargoGestao;
    // }

    public Docente(String nome, String campus, String cpf, LocalDate dataNascimento, String matricula,
            Titulacao titulacao, boolean efetivo, int tempoEfetivoServico, int tempoCargoGestao) {
        super(nome, campus, cpf, dataNascimento, matricula, titulacao, efetivo);
        this.tempoEfetivoServico = tempoEfetivoServico;
        this.tempoCargoGestao = tempoCargoGestao;
    }

    // Critérios: efetivo, idade >= 35, tempoEfetivoServico >= 5 e 
    // (titulacao == DOUTORADO ou tempoCargoGestao >= 2)
    public boolean elegivel() {
        int idade = LocalDate.now().getYear() - getDataNascimento().getYear();
        if (!isEfetivo()) {
            return false;
        } 
        if (idade < 35){
            return false;
        }
        if (tempoEfetivoServico < 5) {
            return false;
        } 
        if (getTitulacao() != Titulacao.DOUTORADO && tempoCargoGestao < 2) {
            return false;
        }  else {
            return true;

        }
    }

    public int getTempoEfetivoServico() {
        return tempoEfetivoServico;
    }

    public void setTempoEfetivoServico(int tempoEfetivoServico) {
        this.tempoEfetivoServico = tempoEfetivoServico;
    }

    public int getTempoCargoGestao() {
        return tempoCargoGestao;
    }

    public void setTempoCargoGestao(int tempoCargoGestao) {
        this.tempoCargoGestao = tempoCargoGestao;
    }

    
}
