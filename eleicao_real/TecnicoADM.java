package eleicao_real;
import java.time.LocalDate;
public class TecnicoADM extends Servidor {
    private int tempoEfetivoServico;
    private LocalDate tempoCargoGestao;
    private RegimeTrabalho regimeTrabalho;





    public TecnicoADM(String nome, String cpf, int matricula, LocalDate dataNascimento, String campus,
            eleicao_real.Titulacao titulacao, LocalDate inicio_carreira, boolean efetivo, int tempoEfetivoServico,
            LocalDate tempoCargoGestao, RegimeTrabalho regimeTrabalho) {
        super(nome, cpf, matricula, dataNascimento, campus, titulacao, inicio_carreira, efetivo);
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


    public RegimeTrabalho getRegimeTrabalho() {
        return regimeTrabalho;
    }



    public void setRegimeTrabalho(RegimeTrabalho regimeTrabalho) {
        this.regimeTrabalho = regimeTrabalho;
    }

   
    



}
