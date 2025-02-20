package eleicao_real;
import java.time.LocalDate;

public class Aluno extends Pessoa {
    private String curso;

    
    
   public Aluno(String nome, String cpf, int matricula, LocalDate dataNascimento, String curso) {
        super(nome, cpf, matricula, dataNascimento);
        this.setCurso(curso);;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    }



    

