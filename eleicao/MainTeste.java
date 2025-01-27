package eleicao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainTeste {
    public static void main(String[] args) {
        try {
            // Criar alguns servidores (docentes e técni
            cos)
            Docente docente1 = new Docente(
                "João Silva", "111.111.111-11",
                LocalDate.of(1980, 5, 15), "D001",
                "IFRN-Natal", Titulacao.DOUTORADO, true,
                10, 3);

            Docente docente2 = new Docente(
                "Maria Santos", "222.222.222-22",
                LocalDate.of(1975, 8, 20), "D002",
                "IFRN-Natal", Titulacao.MESTRADO, true,
                8, 4);

            TecnicoAdministrativo tecnico1 = new TecnicoAdministrativo(
                "Pedro Oliveira", "333.333.333-33",
                LocalDate.of(1982, 3, 10), "T001",
                "IFRN-Natal", Titulacao.ESPECIALIZACAO, true,
                7, 2);

            // Criar alguns alunos
            Aluno aluno1 = new Aluno(
                "Ana Pereira", "444.444.444-44",
                LocalDate.of(2000, 6, 25), "A001",
                "Informática");

            Aluno aluno2 = new Aluno(
                "Carlos Souza", "555.555.555-55",
                LocalDate.of(2001, 4, 15), "A002",
                "Mecânica");

            Aluno aluno3 = new Aluno(
                "Beatriz Lima", "666.666.666-66",
                LocalDate.of(1999, 9, 30), "A003",
                "Informática");

            // Criar lista de eleitores
            List<Pessoa> eleitores = new ArrayList<>();
            eleitores.add(docente1);
            eleitores.add(docente2);
            eleitores.add(tecnico1);
            eleitores.add(aluno1);
            eleitores.add(aluno2);
            eleitores.add(aluno3);

            // Criar urna eletrônica com servidor responsável
            UrnaEletronica urna = new UrnaEletronica(tecnico1);

            // Registrar candidatos
            // Apenas docente1 e tecnico1 são elegíveis
            if (docente1.elegivel()) {
                Candidato candidato1 = new Candidato(docente1, 1, "Azul");
                urna.registrarCandidato(candidato1);
                System.out.println("Candidato registrado: " + docente1.getNome());
            }

            if (tecnico1.elegivel()) {
                Candidato candidato2 = new Candidato(tecnico1, 2, "Verde");
                urna.registrarCandidato(candidato2);
                System.out.println("Candidato registrado: " + tecnico1.getNome());
            }

            // Iniciar eleição
            System.out.println("\nIniciando eleição...");
            urna.iniciarEleicao(eleitores);

            // Simular votação
            System.out.println("Simulando votação...\n");

            // Docentes votam
            if (urna.podeVotar("D001")) urna.registrarVoto("D001", 1);  // Vota no candidato 1
            if (urna.podeVotar("D002")) urna.registrarVoto("D002", 2);  // Vota no candidato 2

            // Técnico vota
            if (urna.podeVotar("T001")) urna.registrarVoto("T001", 1);  // Vota no candidato 1

            // Alunos votam
            if (urna.podeVotar("A001")) urna.registrarVoto("A001", 1);  // Vota no candidato 1
            if (urna.podeVotar("A002")) urna.registrarVoto("A002", 2);  // Vota no candidato 2
            if (urna.podeVotar("A003")) urna.registrarVoto("A003", 0);  // Voto em branco

            // Finalizar eleição
            System.out.println("Finalizando eleição...");
            urna.finalizarEleicao();

            System.out.println("\nArquivos gerados:");
            System.out.println("1. zeresima.txt - Contém o registro inicial da urna");
            System.out.println("2. resultado_eleicao.txt - Contém o resultado final da eleição");
            System.out.println("3. ausentes.txt - Lista de eleitores que não votaram");

        } catch (Exception e) {
            System.err.println("Erro durante a execução: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
