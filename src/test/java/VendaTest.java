import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons.Cupom;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos.Alimento;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos.Produto;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos.Roupa;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Vendas.Venda;
import br.ufjf.dcc.dcc025.comercioeletronico.Exceptions.CupomInvalidoException;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class VendaTest 
{
    @Test
    void testCalculoTotalSemCupom()
    {
        Produto roupa = new Roupa(1, "Camiseta", 50.0, 'M', "Preta");
        Produto alimento = new Alimento(2, "Chocolate", 30.0, "10-12-2025");

        Venda venda = new Venda(101, Arrays.asList(roupa, alimento), null);
        double totalEsperado = 80.0;

        assertEquals(totalEsperado, venda.calcularTotal(), 0.01, "O total da venda sem cupom está incorreto.");
    }

    @Test
    void testCalculoTotalComCupom() throws CupomInvalidoException
    {
        Produto roupa = new Roupa(1, 
            "Camiseta", 50.0, 'M', "Preta");
        Produto alimento = new Alimento(2, "Chocolate", 30.0, "10-12-2025");
        Cupom cupom = new Cupom(1, 10.0, true);

        Venda venda = new Venda(102, Arrays.asList(roupa, alimento), cupom);
        double totalEsperado = 72.0; // 10% de desconto sobre R$ 80.0

        assertEquals(totalEsperado, venda.calcularTotal(), 0.01, "O total da venda com cupom está incorreto.");
    }
}
