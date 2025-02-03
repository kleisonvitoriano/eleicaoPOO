package eleição_real;

import java.time.LocalDate;

public abstract class Pessoa {
    protected String nome;
    protected int cpf, matricula;
    protected LocalDate dataNascimento;


    public Pessoa(String nome, int cpf, int matricula, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
    }


    public String getNome() {
        return nome;
    }

    public int getCpf() {
        return cpf;
    }


    public int getMatricula() {
        return matricula;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public int getIdade() {
        return LocalDate.now().getYear() - dataNascimento.getYear();
    }

    
    

}
