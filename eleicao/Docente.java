package eleicao;

import java.time.LocalDate;

public class Docente extends Servidor {
    private int tempoEfetivoServico;
    private int tempoCargoGestao;

    public Docente(String nome, String cpf, LocalDate dataNascimento, String matricula,
                  String campus, Titulacao titulacao, boolean efetivo,
                  int tempoEfetivoServico, int tempoCargoGestao) {
        super(nome, cpf, dataNascimento, matricula, campus, titulacao, efetivo);
        this.tempoEfetivoServico = tempoEfetivoServico;
        this.tempoCargoGestao = tempoCargoGestao;
    }

    public int getTempoEfetivoServico() {
        return tempoEfetivoServico;
    }

    public int getTempoCargoGestao() {
        return tempoCargoGestao;
    }

    public boolean elegivel() {
        return isEfetivo() && 
               getIdade() >= 35 && 
               tempoEfetivoServico >= 5 && 
               (getTitulacao() == Titulacao.DOUTORADO || tempoCargoGestao >= 2);
    }
}
