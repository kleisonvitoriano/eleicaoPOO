import java.time.LocalDate;

public abstract class Pessoa {
    protected String nome;
    protected String campus;
    protected String cpf;
    protected LocalDate dataNascimento;
    protected String matricula;


    


    // public Pessoa(String nome, String cpf, LocalDate dataNascimento, String matricula) {
    //     this.nome = nome;
    //     this.cpf = cpf;
    //     this.dataNascimento = dataNascimento;
    //     this.matricula = matricula;
    // }

    public Pessoa(String nome, String campus, String cpf, LocalDate dataNascimento, String matricula) {
        this.nome = nome;
        this.campus = campus;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.matricula = matricula;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getCpf() {
        return cpf;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    

    

    // public String getNome() {
    //     return nome;
    // }

    // public String getCpf() {
    //     return cpf;
    // }

    // public LocalDate getDataNascimento() {
    //     return dataNascimento;
    // }

    // public String getMatricula() {
    //     return matricula;
    // }
}
