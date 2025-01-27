package eleicao;

import java.time.LocalDate;

public abstract class Pessoa {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String matricula;

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

    public int getIdade() {
        return LocalDate.now().getYear() - dataNascimento.getYear();
    }
}
