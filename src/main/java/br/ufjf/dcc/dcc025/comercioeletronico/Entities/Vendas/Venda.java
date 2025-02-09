package br.ufjf.dcc.dcc025.comercioeletronico.Entities.Vendas;

import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons.Cupom;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons.CupomQuantidadeLimitada;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons.CupomValorMinimo;
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

    public double calcularTotal() 
    {
        double total = 0.0;
        
        for (Produto produto : produtosComprados) 
        {
            total += produto.getPreco();
        }

        if (cupomAplicado != null && cupomAplicado.ativo) 
        {
            double desconto = 0.0;

            switch (cupomAplicado)
            {
                case CupomQuantidadeLimitada cupom -> 
                {
                    if (!cupom.atingiuMaximoUtilizacoes())
                    {
                        desconto = total * (cupom.percentualDesconto / 100);
                        cupom.utilizarCupom();
                    }
                }
                case CupomValorMinimo cupom -> 
                {
                    if (cupom.valorMinimoAtingido(total))
                    {
                        desconto = total * (cupom.percentualDesconto / 100);
                    }
                }
                default -> 
                {
                    desconto = total * (cupomAplicado.percentualDesconto / 100);
                }
            }

            total -= desconto;
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

        for (Produto produto : produtosComprados) 
        {
            System.out.println("   - " + produto.getNome() + " | Preço: R$ " + String.format("%.2f", produto.getPreco()));
        }

        System.out.println("--------------------------------------");

        double totalSemDesconto = calcularTotal();
        double descontoAplicado = 0.0;

        if (cupomAplicado != null && cupomAplicado.ativo) 
        {
            descontoAplicado = (totalSemDesconto * (cupomAplicado.percentualDesconto / 100));
            System.out.println(">> Cupom aplicado: " + cupomAplicado.percentualDesconto + "% de desconto");
            System.out.println(">> Valor do desconto: R$ " + String.format("%.2f", descontoAplicado));
        } 
        else 
        {
            System.out.println(">> Nenhum cupom aplicado.");
        }

        System.out.println(">> Total final: R$ " + String.format("%.2f", totalSemDesconto - descontoAplicado));
        System.out.println("======================================");
    }
}


