package eleicao_real;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa {
    private String curso;
    private static List<Aluno> alunos = new ArrayList<>();

    public Aluno(String nome, String cpf, LocalDate dataNascimento,
                 String matricula, String curso) {
        super(nome, cpf, dataNascimento, matricula);
        this.curso = curso;
        alunos.add(this);
    }

    public String getCurso() {
        return curso;
    }
    
    public static int getTotalAlunos() {
        return alunos.size();
    }
    
    public static List<Aluno> getAlunos() {
        return new ArrayList<>(alunos);
    }
} 


    

