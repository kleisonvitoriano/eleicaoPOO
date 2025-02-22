import java.time.LocalDate;


public class TecnicoAdministrativo extends Servidor {
    private int tempoEfetivoServico;
    private int tempoCargoGestao;
    private RegimeTrabalho regimeTrabalho;
    



     

    public TecnicoAdministrativo(String nome, String campus, String cpf, LocalDate dataNascimento, String matricula,
            Titulacao titulacao, boolean efetivo, int tempoEfetivoServico, int tempoCargoGestao,
            RegimeTrabalho regimeTrabalho) {
        super(nome, campus, cpf, dataNascimento, matricula, titulacao, efetivo);
        this.tempoEfetivoServico = tempoEfetivoServico;
        this.tempoCargoGestao = tempoCargoGestao;
        this.regimeTrabalho = regimeTrabalho;
    }
    
   

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
    public RegimeTrabalho getRegimeTrabalho() {
        return regimeTrabalho;
    }
    public void setRegimeTrabalho(RegimeTrabalho regimeTrabalho) {
        this.regimeTrabalho = regimeTrabalho;
    }

   

}
