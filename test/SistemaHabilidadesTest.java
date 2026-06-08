import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SistemaHabilidadesTest {

    private Habilidade bola_de_fogo;
    private Habilidade escudo_magico;
    private GerenciadorHabilidades gerenciador;

    @BeforeEach
    public void setUp() {
        bola_de_fogo = new Habilidade("Bola de Fogo");
        escudo_magico = new Habilidade("Escudo Mágico");
        gerenciador = new GerenciadorHabilidades();
    }


    @Test
    public void testHabilidadeIniciaInativa() {
        assertEquals("inativa", bola_de_fogo.getStatus());
    }

    @Test
    public void testAtivarHabilidadeAlteraStatus() {
        IComando cmd = new AtivarHabilidadeComando(bola_de_fogo);
        cmd.executar();
        assertEquals("Bola de Fogo ativada", bola_de_fogo.getStatus());
    }

    @Test
    public void testCancelarAtivacaoDesativaHabilidade() {
        IComando cmd = new AtivarHabilidadeComando(bola_de_fogo);
        cmd.executar();
        cmd.cancelar();
        assertEquals("Bola de Fogo desativada", bola_de_fogo.getStatus());
    }

    @Test
    public void testDesativarHabilidadeAlteraStatus() {
        bola_de_fogo.ativar();
        IComando cmd = new DesativarHabilidadeComando(bola_de_fogo);
        cmd.executar();
        assertEquals("Bola de Fogo desativada", bola_de_fogo.getStatus());
    }

    @Test
    public void testCancelarDesativacaoReativaHabilidade() {
        bola_de_fogo.ativar();
        IComando cmd = new DesativarHabilidadeComando(bola_de_fogo);
        cmd.executar();
        cmd.cancelar();
        assertEquals("Bola de Fogo ativada", bola_de_fogo.getStatus());
    }

    @Test
    public void testGerenciadorExecutaComandoDeAtivacao() {
        gerenciador.executarComando(new AtivarHabilidadeComando(bola_de_fogo));
        assertEquals("Bola de Fogo ativada", bola_de_fogo.getStatus());
    }

    @Test
    public void testGerenciadorCancelaUltimoComando() {
        gerenciador.executarComando(new AtivarHabilidadeComando(bola_de_fogo));
        gerenciador.cancelarUltimoComando();
        assertEquals("Bola de Fogo desativada", bola_de_fogo.getStatus());
    }

    @Test
    public void testGerenciadorCancelaApenasUltimoDeVariosComandos() {
        gerenciador.executarComando(new AtivarHabilidadeComando(bola_de_fogo));
        gerenciador.executarComando(new AtivarHabilidadeComando(escudo_magico));
        gerenciador.cancelarUltimoComando();
        assertEquals("Bola de Fogo ativada", bola_de_fogo.getStatus());
        assertEquals("Escudo Mágico desativado", escudo_magico.getStatus());
    }

    @Test
    public void testCancelarSemHistoricoNaoLancaExcecao() {
        assertDoesNotThrow(() -> gerenciador.cancelarUltimoComando());
    }

    @Test
    public void testFluxoCompletoAtivarEDesativar() {
        gerenciador.executarComando(new AtivarHabilidadeComando(bola_de_fogo));
        assertEquals("Bola de Fogo ativada", bola_de_fogo.getStatus());

        gerenciador.executarComando(new DesativarHabilidadeComando(bola_de_fogo));
        assertEquals("Bola de Fogo desativada", bola_de_fogo.getStatus());

        gerenciador.cancelarUltimoComando(); // desfaz a desativação
        assertEquals("Bola de Fogo ativada", bola_de_fogo.getStatus());
    }
}