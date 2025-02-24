import java.time.LocalDate;

public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected LocalDate dataNascimento;
    protected String matricula;

    public Pessoa(String nome, String cpf, LocalDate dataNascimento, String matricula) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getMatricula() {
        return matricula;
    }
}
