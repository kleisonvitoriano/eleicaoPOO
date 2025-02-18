package eleição_real;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Escritora {
    private final String nomeArquivo;
    private final boolean incremental;

    public Escritora (String nomeArquivo, boolean incremental){
        this.nomeArquivo = nomeArquivo;
        this.incremental = incremental;

    }

    public void escrever(String textoAEscrever){
        try(BufferedWriter writer = new BufferedWriter( new FileWriter(nomeArquivo, incremental) );){
            writer.append (textoAEscrever);
            writer.close();
        } catch (IOException e){
            e.printStackTrace();    
        }
    }

}
