package eleicao;

import java.time.LocalDate; 

public abstract class Servidor extends Pessoa {
    private String campus;
    private Titulacao titulacao;
    private boolean efetivo;

    public Servidor(String nome, String cpf, LocalDate dataNascimento, String matricula,
                   String campus, Titulacao titulacao, boolean efetivo) {
        super(nome, cpf, dataNascimento, matricula);
        this.setCampus(campus);
        this.setTitulacao(titulacao);
        this.setEfetivo(efetivo);
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
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
