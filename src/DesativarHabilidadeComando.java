public class DesativarHabilidadeComando implements IComando {
    private Habilidade habilidade;

    public DesativarHabilidadeComando(Habilidade habilidade) {
        this.habilidade = habilidade;
    }

    @Override
    public void executar() {
        this.habilidade.desativar();
    }

    @Override
    public void cancelar() {
        this.habilidade.ativar();
    }
}