import java.time.LocalDate;

public class Servidor extends Pessoa {
    
    protected Titulacao titulacao;
    protected boolean efetivo;


    
    public Servidor(String nome, String campus, String cpf, LocalDate dataNascimento, String matricula, Titulacao titulacao, boolean efetivo) {
        super(nome, campus, cpf, dataNascimento, matricula);
        this.titulacao = titulacao;
        this.efetivo = efetivo;
    }



  



    public Titulacao getTitulacao() {
        return titulacao;
    }



    public void setTitulacao(Titulacao titulacao) {
        this.titulacao = titulacao;
    }



    public boolean isEfetivo() {
        return efetivo;
    }



    public void setEfetivo(boolean efetivo) {
        this.efetivo = efetivo;
    }

    
}
