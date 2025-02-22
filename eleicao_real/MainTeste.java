packpage eleição_real;
import java.time.LocalDate;

public class MainTeste {
    public static void main(String[] args) {
        try {
       
            Aluno aluno1 = new Aluno("João Silva", "12345678900", LocalDate.of(2000, 5, 15), "20230001", "Informática");
            Aluno aluno2 = new Aluno("Maria Santos", "98765432100", LocalDate.of(2001, 3, 20), "20230002", "Informática");
            Aluno aluno3 = new Aluno("Pedro Souza", "45678912300", LocalDate.of(1999, 8, 10), "20230003", "Mecânica");
            
            ServidorDocente docente1 = new ServidorDocente(
                "Carlos Oliveira", "11122233344", LocalDate.of(1980, 2, 15),
                "20150001", "Campus Natal-Central", Titulacao.DOUTORADO, true, 96, 36
            );
            
            ServidorDocente docente2 = new ServidorDocente(
                "Ana Paula Silva", "55566677788", LocalDate.of(1975, 7, 22),
                "20160002", "Campus Parnamirim", Titulacao.DOUTORADO, true, 84, 24
            );
            
         
            ServidorTecnico tecnico1 = new ServidorTecnico(
                "José Santos", "99988877766", LocalDate.of(1982, 4, 10),
                "20140001", "Campus São Gonçalo do Amarante", Titulacao.MESTRADO, true, 108, 48
            );
            
            ServidorTecnico tecnico2 = new ServidorTecnico(
                "Marina Lima", "44433322211", LocalDate.of(1978, 9, 5),
                "20130002", "Campus Natal-Zona Norte", Titulacao.ESPECIALIZACAO, true, 120, 60
            );
            
          
            Candidato candidato1 = new Candidato(docente1, 1, "Azul");
            Candidato candidato2 = new Candidato(tecnico1, 2, "Verde");
            
          
            ServidorTecnico servidorResponsavel = new ServidorTecnico(
                "Roberto Silva", "77788899900", LocalDate.of(1970, 1, 1),
                "20100001", "Campus Nova Cruz", Titulacao.MESTRADO, true, 156, 72
            );
            
            UrnaEletronica urna = new UrnaEletronica(servidorResponsavel);
        
            urna.adicionarEleitor(aluno1);
            urna.adicionarEleitor(aluno2);
            urna.adicionarEleitor(aluno3);
            urna.adicionarEleitor(docente1);
            urna.adicionarEleitor(docente2);
            urna.adicionarEleitor(tecnico1);
            urna.adicionarEleitor(tecnico2);
            
            urna.adicionarCandidato(candidato1);
            urna.adicionarCandidato(candidato2);
      
            urna.gerarArquivoEleitores("eleitores.txt");
            
            System.out.println("\nIniciando votação...");
            urna.iniciarVotacao("20100001");
            
           
            urna.registrarVoto("20230001", 1); // Aluno votando no candidato 1
            urna.registrarVoto("20230002", 2); // Aluno votando no candidato 2
            urna.registrarVoto("20230003", 1); // Aluno votando no candidato 1
            urna.registrarVoto("20150001", 1); 
            urna.registrarVoto("20160002", 2);
            urna.registrarVoto("20140001", 1); 
            urna.registrarVoto("20130002", 0); 
            
            // Finalizando votação
            System.out.println("\nFinalizando votação...");
            urna.finalizarVotacao("20100001");
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 