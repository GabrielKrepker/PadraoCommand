public class AtivarHabilidadeComando implements IComando {
    private Habilidade habilidade;

    public AtivarHabilidadeComando(Habilidade habilidade) {
        this.habilidade = habilidade;
    }

    @Override
    public void executar() {
        this.habilidade.ativar();
    }

    @Override
    public void cancelar() {
        this.habilidade.desativar();
    }
}