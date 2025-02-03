package eleição_real;
import java.time.LocalDate;
public class TecnicoADM extends Servidores {
    private int tempoEfetivoServico;
    private LocalDate tempoCargoGestao;


   public TecnicoADM(String nome, int cpf, int matricula, LocalDate dataNascimento, String campus, Titulacao titulacao, boolean efetivo, int tempoEfetivoServico, LocalDate tempoCargoGestao) {
        super(nome, cpf, matricula, dataNascimento, campus, titulacao, tempoCargoGestao, efetivo); 
        this.tempoEfetivoServico = tempoEfetivoServico;
        this.tempoCargoGestao = tempoCargoGestao;
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





}
