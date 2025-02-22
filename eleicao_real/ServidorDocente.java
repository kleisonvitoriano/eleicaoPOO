
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServidorDocente extends Servidor {
    private int tempoEfetivoServico; // em meses
    private int tempoCargoGestao; // em meses
    private static List<ServidorDocente> docentes = new ArrayList<>();

    public ServidorDocente(String nome, String cpf, LocalDate dataNascimento,
                          String matricula, String campus, Titulacao titulacao,
                          boolean efetivo, int tempoEfetivoServico, int tempoCargoGestao) {
        super(nome, cpf, dataNascimento, matricula, campus, titulacao, efetivo);
        this.tempoEfetivoServico = tempoEfetivoServico;
        this.tempoCargoGestao = tempoCargoGestao;
        docentes.add(this);
    }

    public static int getTotalDocentesEfetivos() {
        return (int) docentes.stream()
            .filter(Servidor::isEfetivo)
            .count();
    }
    
    public static List<ServidorDocente> getDocentes() {
        return new ArrayList<>(docentes);
    }

    public int getTempoEfetivoServico() {
        return tempoEfetivoServico;
    }

    public int getTempoCargoGestao() {
        return tempoCargoGestao;
    }

    public boolean elegivel() {
        return efetivo && 
               tempoEfetivoServico >= 60 && // 5 anos em meses
               getIdade() >= 35 && 
               (titulacao == Titulacao.DOUTORADO || tempoCargoGestao >= 24); // 2 anos em meses
    }
} 