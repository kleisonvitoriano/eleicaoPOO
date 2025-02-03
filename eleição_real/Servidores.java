package eleição_real;

import java.time.LocalDate;

public class Servidores extends Pessoa {
    private String campus;
    private Titulacao Titulacao;
    private LocalDate inicio_carreira;
    private boolean efetivo;


    public Servidores(String nome, int cpf, int matricula, LocalDate dataNascimento, String campus,
            eleição_real.Titulacao titulacao, LocalDate inicio_carreira, boolean efetivo) {
        super(nome, cpf, matricula, dataNascimento);
        this.campus = campus;
        Titulacao = titulacao;
        this.inicio_carreira = inicio_carreira;
        this.efetivo = efetivo;
    }



    public String getCampus() {
        return campus;
    }



    public void setCampus(String campus) {
        this.campus = campus;
    }



    public Titulacao getTitulacao() {
        return Titulacao;
    }



    public void setTitulacao(Titulacao titulacao) {
        Titulacao = titulacao;
    }



    public boolean getEfetivo() {
        return efetivo;
    }



    public void setEfetivo(boolean efetivo) {
        this.efetivo = efetivo;
    }



    public int getinicio_carreira() {
        return LocalDate.now().getYear() - inicio_carreira.getYear();
    }



    public void setinicio_carreira(LocalDate experiencia) {
        this.inicio_carreira = experiencia;
    }

    
    
    
}
