packpage eleição_real;

public class Candidato {
    private Servidor servidor;
    private int numero;
    private String cor;
    
    public Candidato(Servidor servidor, int numero, String cor) {
        this.servidor = servidor;
        this.numero = numero;
        this.cor = cor;
    }
    
    public String getNome() {
        return servidor.getNome();
    }
    
    public int getNumero() {
        return numero;
    }
    
    public String getCor() {
        return cor;
    }
    
    public Servidor getServidor() {
        return servidor;
    }
} 