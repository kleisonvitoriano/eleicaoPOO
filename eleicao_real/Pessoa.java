package eleicao_real;

import java.time.LocalDate;

public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected int matricula;
    protected LocalDate dataNascimento;


    public Pessoa(String nome, String cpf, int matricula, LocalDate dataNascimento) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setMatricula(matricula);
        this.setDataNascimento(dataNascimento);
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getCpf() {
        return cpf;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public int getMatricula() {
        return matricula;
    }


    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }


    public LocalDate getDataNascimento() {
        return dataNascimento;
    }


    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return LocalDate.now().getYear() - dataNascimento.getYear();
    }
   
    
   

    
    

}
