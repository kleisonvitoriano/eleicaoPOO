import java.time.LocalDate;

public class Servidor extends Pessoa {
    protected String campus;
    protected Titulacao titulacao;
    protected boolean efetivo;

    public Servidor(String nome, String cpf, LocalDate dataNascimento, String matricula,
                    String campus, Titulacao titulacao, boolean efetivo) {
        super(nome, cpf, dataNascimento, matricula);
        this.campus = campus;
        this.titulacao = titulacao;
        this.efetivo = efetivo;
    }

    public String getCampus() {
        return campus;
    }

    public Titulacao getTitulacao() {
        return titulacao;
    }

    public boolean isEfetivo() {
        return efetivo;
    }
}
