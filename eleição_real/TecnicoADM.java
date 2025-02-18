package eleição_real;
import java.time.LocalDate;
public class TecnicoADM extends Servidor {
    private int tempoEfetivoServico;
    private LocalDate tempoCargoGestao;
    private String regimeTrabalho;


   

    public TecnicoADM(String nome, String cpf, int matricula, LocalDate dataNascimento, String campus,
            eleição_real.Titulacao titulacao, LocalDate inicio_carreira, boolean efetivo, String responsavel,
            int tempoEfetivoServico, LocalDate tempoCargoGestao, String regimeTrabalho) {
        super(nome, cpf, matricula, dataNascimento, campus, titulacao, inicio_carreira, efetivo, responsavel);
        this.tempoEfetivoServico = tempoEfetivoServico;
        this.tempoCargoGestao = tempoCargoGestao;
        this.regimeTrabalho = regimeTrabalho;
    }

   

    public int getTempoEfetivoServico() {
        return tempoEfetivoServico;
    }

    public LocalDate getTempoCargoGestao() {
        return tempoCargoGestao;
    }
    public boolean elegivel() {
        LocalDate hoje = LocalDate.now();
        int idade = hoje.getYear() - getDataNascimento().getYear();
        return tempoEfetivoServico >= 5 && idade >= 35;
    }

    public void setTempoEfetivoServico(int tempoEfetivoServico) {
        this.tempoEfetivoServico = tempoEfetivoServico;
    }

    public void setTempoCargoGestao(LocalDate tempoCargoGestao) {
        this.tempoCargoGestao = tempoCargoGestao;
    }

    public String getRegimeTrabalho() {
        return regimeTrabalho;
    }

    public void setRegimeTrabalho(String regime) {
        this.regimeTrabalho = regime;
    }

    



}
