package br.ufjf.dcc.dcc025.comercioeletronico;

import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons.Cupom;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons.CupomQuantidadeLimitada;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons.CupomValorMinimo;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos.Alimento;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos.Eletronico;
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
            GestorVendas gestor = new GestorVendas();
            gerarTesteNaMain(gestor);
            
            System.out.println("\n\n\n>>> GERANDO RELATÓRIOS <<<");
            gestor.gerarRelatorioVendas();
            gestor.listarCuponsAtivos();
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
    
    private static void gerarTesteNaMain(GestorVendas gestor) throws CupomInvalidoException
    {
        Roupa calca = new Roupa(1, "Calça Jeans", 90.0, 'M', "Azul");
        Alimento chocolate = new Alimento(2, "Chocolate 70% Cacau", 30.0, "15-12-2025");
        Eletronico smartphone = new Eletronico(3, "Smartphone XYZ", 2000.0, 24);   
        
        Cupom cupomDesconto = new Cupom(1, 10.0, true);
        CupomValorMinimo cupomMinimo = new CupomValorMinimo();
        CupomQuantidadeLimitada cupomLimitado = new CupomQuantidadeLimitada(2, 10.0, true, 2); 
        
        caso1_CompraSemCupom(gestor, calca, chocolate);
        
        caso2_CompraComCupom10Valido(gestor, calca, chocolate, cupomDesconto);
        
        caso3_CompraComCupomValorMinimoNaoAtingido(gestor, chocolate, cupomMinimo);
        
        caso4_CompraComCupomValorMinimoAtingido(gestor, smartphone, cupomMinimo);
        
        caso5_CompraComCupomQuantidadeLimitadaDisponivel(gestor, calca, cupomLimitado);
        
        caso6_CompraComQuantidadeLimitadaTotalmenteUtilizado(gestor, calca, cupomLimitado);
    }
    
    private static void caso1_CompraSemCupom(GestorVendas gestor, Produto calca, Produto chocolate)
    {
        System.out.println("\n\n\n(1) Compra sem cupom");
        List<Produto> compra = Arrays.asList(calca, chocolate);
        Venda venda = new Venda(01, compra, null);
        
        venda.exibirResumoProdutosComprados(); 
        gestor.adicionarVenda(venda);
    }
    
    private static void caso2_CompraComCupom10Valido(GestorVendas gestor, Produto calca, Produto chocolate, Cupom cupomDesconto)
    {
        System.out.println("\n\n\n(2) Compra com cupom de 10% válido");
        List<Produto> compra = Arrays.asList(calca, chocolate);
        Venda venda = new Venda(02, compra, cupomDesconto);
        
        gestor.adicionarVenda(venda);
        venda.exibirResumoProdutosComprados();      
    }
        
    private static void caso3_CompraComCupomValorMinimoNaoAtingido(GestorVendas gestor, Produto chocolate, Cupom cupomMinimo)
    {
        System.out.println("\n\n\n(3) Cupom de valor mínimo não atingido");
        List<Produto> compra = Arrays.asList(chocolate);
        Venda venda = new Venda(03, compra, cupomMinimo);
        
        gestor.adicionarVenda(venda);
        venda.exibirResumoProdutosComprados();       
    }
    
    private static void caso4_CompraComCupomValorMinimoAtingido(GestorVendas gestor, Produto smartphone, Cupom cupomMinimo)
    {
        System.out.println("\n\n\n(4) Cupom de valor mínimo atingido");
        List<Produto> compra = Arrays.asList(smartphone);
        Venda venda = new Venda(04, compra, cupomMinimo);
        
        gestor.adicionarVenda(venda);
        venda.exibirResumoProdutosComprados();      
    }
    
    private static void caso5_CompraComCupomQuantidadeLimitadaDisponivel(GestorVendas gestor, Produto calca, CupomQuantidadeLimitada cupomLimitado)
    {
        System.out.println("\n\n\n(5) Cupom de quantidade limitada ainda disponível.");
        List<Produto> compra = Arrays.asList(calca);
        Venda venda = new Venda(05, compra, cupomLimitado);
        cupomLimitado.utilizarCupom();
        
        gestor.adicionarVenda(venda);
        venda.exibirResumoProdutosComprados();
                
    }
    
    private static void caso6_CompraComQuantidadeLimitadaTotalmenteUtilizado(GestorVendas gestor, Produto calca, CupomQuantidadeLimitada cupomLimitado)
    {
        cupomLimitado.utilizacoesAtuais = 3; // Simulando cupom esgotado
        System.out.println("\n\n\n(6) Cupom de quantidade limitada já foi totalmente utilizado.");
        cupomLimitado.utilizarCupom();
        List<Produto> compra = Arrays.asList(calca);
        Venda venda = new Venda(106, compra, cupomLimitado);

        gestor.adicionarVenda(venda);
        venda.exibirResumoProdutosComprados();        
    }
}
