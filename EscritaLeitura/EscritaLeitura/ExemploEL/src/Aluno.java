public class Aluno {
    private String cpf;
    private String nome;
    private int media;
    
    Aluno() { }

    Aluno(String cpf, String nome, int media) {
        this.setCpf(cpf);
        this.setNome(nome);
        this.setMedia(media);
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMedia() {
        return this.media;
    }

    public void setMedia(int media) {
        if(media >= 0 && media <= 100) {
            this.media = media;
        } else {
            System.out.println(" ERRO: valor de média inválido!");
        }
    }

    @Override
    public String toString() {
        return this.cpf + ";" + this.nome + ";" + this.media;
    }

    public void imprimir() {
        System.out.println(" * Nome: " + this.getNome());
        System.out.println(" * CPF: " + this.getCpf());
        System.out.println(" * Media: " + this.getMedia());
    }

}
