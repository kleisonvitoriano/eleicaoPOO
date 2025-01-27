package eleicao;

import java.time.LocalDate;

public class Aluno extends Pessoa {
    private String curso;

    public Aluno(String nome, String cpf, LocalDate dataNascimento, String matricula, String curso) {
        super(nome, cpf, dataNascimento, matricula);
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }
}
