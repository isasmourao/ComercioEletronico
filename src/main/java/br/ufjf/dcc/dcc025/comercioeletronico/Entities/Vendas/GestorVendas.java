package br.ufjf.dcc.dcc025.comercioeletronico.Entities.Vendas;

import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons.Cupom;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Vendas.Interface.Relatorio;
import java.util.ArrayList;
import java.util.List;

public class GestorVendas implements Relatorio
{
    private final List<Venda> vendas;
    private final List<Cupom> cupons;
    
    public GestorVendas() 
    {
        this.vendas = new ArrayList<>();
        this.cupons = new ArrayList<>();
    }
    
    public void adicionarVenda(Venda venda)
    {
        vendas.add(venda);
    }

    public void adicionarCupom(Cupom cupom) 
    {
        cupons.add(cupom);
    }
    
    @Override
    public void gerarRelatorioVendas()
    {
        System.out.println("""
                           ======================================
                           >> RELATÓRIO DE VENDAS

                           """);

        double totalArrecadado = 0;
        int totalVendas = vendas.size();

        for (Venda venda : vendas)
        {
            totalArrecadado += venda.calcularTotalComDesconto();
        }

        System.out.println(">> Total de vendas realizadas: " + totalVendas);
        System.out.println(">> Valor total arrecadado: R$ " + String.format("%.2f", totalArrecadado));
        System.out.println("======================================");
    }
    
    @Override
    public void listarCuponsAtivos()
    {
        System.out.println("""
                           ======================================
                           >> CUPONS ATIVOS DISPONÍVEIS
                           
                           """);

        boolean temCuponsAtivos = false;

        for (Cupom cupom : cupons) 
        {
            if (cupom.ativo)
            {
                System.out.println("ID: " + cupom.id + " | Desconto: " + cupom.percentualDesconto + "%");
                temCuponsAtivos = true;
            }
        }

        if (!temCuponsAtivos)
        {
            System.out.println("Nenhum cupom ativo disponível.");
        }

        System.out.println("======================================");
    }
}
