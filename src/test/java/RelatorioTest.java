import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Vendas.GestorVendas;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class RelatorioTest 
{
    @Test
    void testGerarRelatorioVendas() {
        GestorVendas gestor = new GestorVendas();
        
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));

        gestor.gerarRelatorioVendas();

        String resultado = saida.toString().trim();
        assertTrue(resultado.contains("total de vendas realizadas"), "O relatório de vendas está incorreto.");
    }

    @Test
    void testListarCuponsAtivos() 
    {
        GestorVendas gestor = new GestorVendas();

        // Capturar saída do console
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));

        gestor.listarCuponsAtivos();

        String resultado = saida.toString().trim();
        assertTrue(resultado.contains("cupons ainda disponíveis"), "O relatório de cupons ativos está incorreto.");
    }
}
