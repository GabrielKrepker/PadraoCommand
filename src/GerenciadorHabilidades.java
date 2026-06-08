import java.util.ArrayList;
import java.util.List;

public class GerenciadorHabilidades {
    private List<IComando> historico = new ArrayList<>();

    public void executarComando(IComando comando) {
        this.historico.add(comando);
        comando.executar();
    }

    public void cancelarUltimoComando() {
        if (!historico.isEmpty()) {
            IComando ultimo = this.historico.get(historico.size() - 1);
            ultimo.cancelar();
            this.historico.remove(historico.size() - 1);
        }
    }
}