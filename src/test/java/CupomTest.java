import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons.CupomQuantidadeLimitada;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons.CupomValorMinimo;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos.Alimento;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos.Produto;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos.Roupa;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Vendas.Venda;
import br.ufjf.dcc.dcc025.comercioeletronico.Exceptions.CupomInvalidoException;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CupomTest
{
    @Test
    void testCupomValorMinimoNaoAtingido() throws CupomInvalidoException
    {
        CupomValorMinimo cupom = new CupomValorMinimo(1, 10.0, true, 200.0);
        assertFalse(cupom.valorMinimoAtingido(150.0), "O cupom não deveria ser aplicável.");
    }

    @Test
    void testCupomValorMinimoAtingido() throws CupomInvalidoException 
    {
        CupomValorMinimo cupom = new CupomValorMinimo(2, 10.0, true, 200.0);
        assertTrue(cupom.valorMinimoAtingido(250.0), "O cupom deveria ser aplicável.");
    }

    @Test
    void testCupomQuantidadeLimitadaEsgotado() throws CupomInvalidoException 
    {
        CupomQuantidadeLimitada cupom = new CupomQuantidadeLimitada(3, 10.0, true, 3);
        assertTrue(cupom.atingiuMaximoUtilizacoes(), "O cupom deveria estar esgotado.");
    }

    @Test
    void testCupomQuantidadeLimitadaDisponivel() throws CupomInvalidoException 
    {
        CupomQuantidadeLimitada cupom = new CupomQuantidadeLimitada(1, 10.0, true, 4);
        assertTrue(cupom.atingiuMaximoUtilizacoes(), "O cupom ainda pode ser usado.");
    }
    
    @Test
    void testCalculoTotalComCupomQuantidadeLimitada() throws CupomInvalidoException 
    {
        Produto roupa = new Roupa(1, "Camiseta", 50.0, 'M', "Preta");
        Produto alimento = new Alimento(2, "Chocolate", 30.0, "10-12-2025");
        CupomQuantidadeLimitada cupom = new CupomQuantidadeLimitada(1, 10.0, true, 0);

        Venda venda = new Venda(2, Arrays.asList(roupa, alimento), cupom);
        double totalEsperado = 72.0; 

        assertEquals(totalEsperado, venda.calcularTotal(), 0.01, "O total da venda com cupom quantidade limitada está incorreto.");
    }
}
