package eleicao_real;

import java.time.LocalDate;
import java.time.Period;

public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected LocalDate dataNascimento;
    protected String matricula;

    public Pessoa(String nome, String cpf, LocalDate dataNascimento, String matricula) {
        if (!matricula.matches("\\d+")) {
            throw new IllegalArgumentException("A matrícula deve conter apenas números");
        }
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.matricula = matricula;
    }

    // Getters e setters
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
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
} 