package br.ufjf.dcc.dcc025.comercioeletronico;

import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons.CupomQuantidadeLimitada;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons.CupomValorMinimo;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos.Produto;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos.Roupa;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Vendas.GestorVendas;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Vendas.Venda;
import br.ufjf.dcc.dcc025.comercioeletronico.Exceptions.CupomInvalidoException;
import java.util.Arrays;
import java.util.List;

public class ComercioEletronico 
{
    public static void main(String[] args) throws CupomInvalidoException 
    { 
        try
        {
            // Criando o gestor de vendas
            GestorVendas gestor = new GestorVendas();

            // Criando produtos
            Roupa jaqueta = new Roupa('G', "Preta");
            jaqueta.setNome("Jaqueta Preta");
            jaqueta.setPreco(180.0);

            Roupa calca = new Roupa('M', "Azul");
            calca.setNome("Calça Jeans");
            calca.setPreco(90.0);

            // Criando um cupom com valor mínimo de R$ 200
            CupomValorMinimo cupomMinimo = new CupomValorMinimo(1, 20.0, true, 200.0);
            gestor.adicionarCupom(cupomMinimo);

            // Criando um cupom com limite de 3 utilizações
            CupomQuantidadeLimitada cupomLimitado = new CupomQuantidadeLimitada(2, 10.0, true, 2);
            gestor.adicionarCupom(cupomLimitado);

            // CASO 1: Tentativa de compra abaixo do valor mínimo do cupom
            System.out.println("\n>>> Teste: Compra abaixo do valor mínimo para desconto.");
            List<Produto> compra1 = Arrays.asList(jaqueta);
            Venda venda1 = new Venda(101, compra1, cupomMinimo);
            venda1.exibirResumoProdutosComprados();

            // CASO 2: Compra acima do valor mínimo
            System.out.println("\n>>> Teste: Compra acima do valor mínimo para desconto.");
            List<Produto> compra2 = Arrays.asList(jaqueta, calca);
            Venda venda2 = new Venda(102, compra2, cupomMinimo);
            venda2.exibirResumoProdutosComprados();

            // CASO 3: Cupom com quantidade limitada - ainda pode ser usado
            System.out.println("\n>>> Teste: Cupom com quantidade limitada ainda disponível.");
            List<Produto> compra3 = Arrays.asList(calca);
            Venda venda3 = new Venda(103, compra3, cupomLimitado);
            venda3.exibirResumoProdutosComprados();
            cupomLimitado.utilizarCupom();

            // CASO 4: Cupom com quantidade limitada - atingiu o limite
            System.out.println("\n>>> Teste: Cupom com quantidade limitada já foi totalmente utilizado.");
            cupomLimitado.utilizarCupom(); // Usando mais uma vez
            List<Produto> compra4 = Arrays.asList(calca);
            Venda venda4 = new Venda(104, compra4, cupomLimitado);
            venda4.exibirResumoProdutosComprados();
        }
        catch (CupomInvalidoException e)
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("<<< Houve um erro e a aplicação foi finalizada: '" + e.getMessage() + "'. >>>");
        }
    }
}

// TODO: Adicionar blocos de try catch