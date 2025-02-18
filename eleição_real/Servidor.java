package eleição_real;

import java.time.LocalDate;

public class Servidor extends Pessoa {
    private String campus;
    private Titulacao Titulacao;
    private LocalDate inicio_carreira;
    private boolean efetivo;
    private String responsavel;

    public Servidor(String nome, String cpf, int matricula, LocalDate dataNascimento, String campus,
            eleição_real.Titulacao titulacao, LocalDate inicio_carreira, boolean efetivo, String responsavel) {
        super(nome, cpf, matricula, dataNascimento);
        this.setCampus(campus);
        this.setTitulacao(titulacao);
        this.setinicio_carreira(inicio_carreira);
        this.setEfetivo(efetivo);
        this.setResponsavel(responsavel);
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
    
        this.responsavel = responsavel;
    }
    public Servidor(String nome, String cpf, int matricula, LocalDate dataNascimento, String campus,
            eleição_real.Titulacao titulacao, LocalDate inicio_carreira, boolean efetivo) {
        super(nome, cpf, matricula, dataNascimento);
        this.setCampus(campus);
        this.setTitulacao(titulacao);
        this.setinicio_carreira(inicio_carreira);
        this.setEfetivo(efetivo);
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



    public void setinicio_carreira(LocalDate inicio_carreira) {
        this.inicio_carreira = inicio_carreira;
    }

    
    
    
}
