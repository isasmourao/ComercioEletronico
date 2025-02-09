import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons.Cupom;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons.CupomQuantidadeLimitada;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons.CupomValorMinimo;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos.Alimento;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos.Produto;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos.Roupa;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Vendas.GestorVendas;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Vendas.Venda;
import br.ufjf.dcc.dcc025.comercioeletronico.Exceptions.CupomInvalidoException;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class RelatorioTest 
{
    @Test
    void testGerarRelatorioVendas() throws CupomInvalidoException 
    {
        GestorVendas gestor = new GestorVendas();

        Produto produto1 = new Roupa(1, "Camiseta", 50.0, 'M', "Preta");
        Produto produto2 = new Alimento(2, "Chocolate", 30.0, "10-12-2025");
        Cupom cupom = new CupomQuantidadeLimitada(1, 10.0, true, 1);
        Venda venda = new Venda(1, Arrays.asList(produto1, produto2), cupom);
        gestor.adicionarVenda(venda);

        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));

        gestor.gerarRelatorioVendas();

        String resultado = saida.toString().trim();
        assertTrue(resultado.contains(">> Total de vendas realizadas: 1"), "O relatório de vendas está incorreto.");
        assertTrue(resultado.contains(">> Valor total arrecadado: R$"), "O relatório não mostra o total arrecadado corretamente.");
    }

    @Test
    void testListarCuponsAtivos() throws CupomInvalidoException 
    {
        GestorVendas gestor = new GestorVendas();

        Cupom cupom1 = new CupomQuantidadeLimitada(1, 10.0, true, 1);
        Cupom cupom2 = new CupomValorMinimo(2, 5.0, true, 200.0);
        gestor.adicionarCupom(cupom1);
        gestor.adicionarCupom(cupom2);

        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));

        gestor.listarCuponsAtivos();

        String resultado = saida.toString().trim();
        assertTrue(resultado.contains(">> CUPONS ATIVOS DISPONÍVEIS"), "O cabeçalho do relatório está incorreto.");
        assertTrue(resultado.contains("ID: 1 | Desconto: 10.0%"), "O cupom 1 não foi listado corretamente.");
        assertTrue(resultado.contains("ID: 2 | Desconto: 5.0%"), "O cupom 2 não foi listado corretamente.");
    }
}
