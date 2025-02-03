package eleição_real;
import java.time.LocalDate;

public class Aluno extends Pessoa {
    private String curso;

    Aluno(String nome, int cpf, int matricula, LocalDate dataNascimento, String curso) {
        super(nome, cpf, matricula, dataNascimento);
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    }



    

