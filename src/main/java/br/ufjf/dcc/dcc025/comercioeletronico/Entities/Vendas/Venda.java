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

    public double calcularTotalBruto() 
    {
        double total = 0.0;
        
        for (Produto produto : produtosComprados) 
        {
            total += produto.getPreco();
        }

        return total;
    }

    public double calcularTotalComDesconto() 
    {
        double total = calcularTotalBruto();

        if (cupomAplicado != null && cupomAplicado.ativo) 
        {
            switch (cupomAplicado) {
                case CupomQuantidadeLimitada cupom -> {
                    if (!cupom.atingiuMaximoUtilizacoes())
                    {
                        total -= total * (cupom.percentualDesconto / 100.0);
                        cupom.utilizarCupom(); 
                    }
                }
                case CupomValorMinimo cupom -> {
                    if (cupom.valorMinimoAtingido(total))
                    {
                        total -= total * (cupom.percentualDesconto / 100.0);
                    }
                }
                default -> total -= total * (cupomAplicado.percentualDesconto / 100.0);
            }
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

        double totalSemDesconto = calcularTotalBruto();
        double totalComDesconto = calcularTotalComDesconto();
        double descontoAplicado = totalSemDesconto - totalComDesconto;

        if (cupomAplicado != null && cupomAplicado.ativo) 
        {
            System.out.println(">> Cupom aplicado: " + cupomAplicado.percentualDesconto + "% de desconto");
            System.out.println(">> Valor do desconto: R$ " + String.format("%.2f", descontoAplicado));
            System.out.println(">> Total final: R$ " + String.format("%.2f", totalComDesconto));
        } 
        else 
        {
            System.out.println(">> Nenhum cupom aplicado.");
            System.out.println(">> Total final: R$ " + String.format("%.2f", totalSemDesconto));
        }

        System.out.println("======================================");
    }
}


