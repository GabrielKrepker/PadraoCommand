public class Habilidade {
    private String nome;
    private String status;

    public Habilidade(String nome) {
        this.nome = nome;
        this.status = "inativa";
    }

    public String getNome() {
        return nome;
    }

    public String getStatus() {
        return status;
    }

    public void ativar() {
        this.status = nome + " ativada";
    }

    public void desativar() {
        this.status = nome + " desativada";
    }
}