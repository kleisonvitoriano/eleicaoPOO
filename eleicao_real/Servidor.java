package eleicao_real;

import java.time.LocalDate;

public abstract class Servidor extends Pessoa {
    protected String campus;
    protected Titulacao titulacao;
    protected boolean efetivo;
    
    public Servidor(String nome, String cpf, LocalDate dataNascimento, 
                   String matricula, String campus, Titulacao titulacao, 
                   boolean efetivo) {
        super(nome, cpf, dataNascimento, matricula);
        this.campus = campus;
        this.titulacao = titulacao;
        this.efetivo = efetivo;
    }

    public boolean isEfetivo() {
        return efetivo;
    }

    public String getCampus() {
        return campus;
    }

    public Titulacao getTitulacao() {
        return titulacao;
    }
} 