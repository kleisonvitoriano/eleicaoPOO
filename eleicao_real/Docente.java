package eleicao_real;

import java.time.LocalDate;

public class Docente extends Servidor{
       private int tempoEfetivoServico;
    private LocalDate tempoCargoGestao;


public Docente(String nome, String cpf, int matricula, LocalDate dataNascimento, String campus, Titulacao titulacao, boolean efetivo, int tempoEfetivoServico, LocalDate tempoCargoGestao) {
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
        int idade = hoje.getYear() - super.getDataNascimento().getYear();
        return tempoEfetivoServico >= 5 && idade >= 35;
    }




    
    
}
