package br.ufjf.dcc.dcc025.comercioeletronico.Entities.Vendas;

import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons.Cupom;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos.Produto;
import java.util.List;

public class Venda 
{
    public int id;
    public List<Produto> produtosComprados;
    public Cupom cupomAplicado;

    public Venda(int id, List<Produto> produtosComprados, Cupom cupomAplicado)
    {
        this.id = id;
        this.produtosComprados = produtosComprados;
        this.cupomAplicado = cupomAplicado;
    }

    // Método para calcular o total da compra sem desconto
    public double calcularTotal()
    {
        double total = 0;
        for (Produto produto : produtosComprados) 
        {
            total += produto.getPreco();
        }
        return total;
    }

    // Método para calcular o total com desconto (se houver)
    public double calcularTotalComDesconto() 
    {
        double total = calcularTotal();
        if (cupomAplicado != null && cupomAplicado.ativo) 
        {
            double desconto = total * (cupomAplicado.percentualDesconto / 100);
            return total - desconto;
        }
        return total;
    }

    public void exibirResumoProdutosComprados() 
    {
        System.out.println("""
                           ======================================
                           >> RESUMO DE PRODUTOS COMPRADOS
                           ======================================
                           """);
        
        System.out.println(">> ID da venda: " + this.id);
        System.out.println(">> Produtos comprados: ");
        
        double total = calcularTotal();
        
        for (Produto produto : produtosComprados) 
        {
            System.out.println("   - " + produto.getNome() + " | Preço: R$ " + produto.getPreco());
        }
        
        System.out.println("--------------------------------------");
        
        if (cupomAplicado != null && cupomAplicado.ativo) 
        {
            double desconto = total - calcularTotalComDesconto();
            System.out.println(">> Cupom aplicado: " + cupomAplicado.percentualDesconto + "% de desconto");
            System.out.println(">> Valor do desconto: R$ " + desconto);
            System.out.println(">> Total com desconto: R$ " + calcularTotalComDesconto());
        } 
        else 
        {
            System.out.println(">> Nenhum cupom aplicado.");
            System.out.println(">> Total final: R$ " + total);
        }

        System.out.println("======================================");
    }
}

