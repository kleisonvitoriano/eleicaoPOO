import java.time.LocalDate;
import java.time.Period;

public class TecnicoAdministrativo extends Servidor {
    private int tempoEfetivoServico;
    private int tempoCargoGestao;

    public TecnicoAdministrativo(String nome, String cpf, LocalDate dataNascimento, String matricula,
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
        int idade = Period.between(getDataNascimento(), LocalDate.now()).getYears();
        if (!isEfetivo()) return false;
        if (idade < 35) return false;
        if (tempoEfetivoServico < 5) return false;
        if (getTitulacao() != Titulacao.DOUTORADO && tempoCargoGestao < 2) return false;
        return true;
    }
}
