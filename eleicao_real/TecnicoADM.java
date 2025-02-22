import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServidorTecnico extends Servidor {
    private int tempoEfetivoServico; // em meses
    private int tempoCargoGestao; // em meses
    private static List<ServidorTecnico> tecnicos = new ArrayList<>();

    public ServidorTecnico(String nome, String cpf, LocalDate dataNascimento,
                          String matricula, String campus, Titulacao titulacao,
                          boolean efetivo, int tempoEfetivoServico, int tempoCargoGestao) {
        super(nome, cpf, dataNascimento, matricula, campus, titulacao, efetivo);
        this.tempoEfetivoServico = tempoEfetivoServico;
        this.tempoCargoGestao = tempoCargoGestao;
        tecnicos.add(this);
    }

    public static int getTotalTecnicosEfetivos() {
        return (int) tecnicos.stream()
            .filter(Servidor::isEfetivo)
            .count();
    }
    
    public static List<ServidorTecnico> getTecnicos() {
        return new ArrayList<>(tecnicos);
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