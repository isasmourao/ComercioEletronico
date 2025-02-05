package br.ufjf.dcc.dcc025.comercioeletronico.Entities.Vendas;

import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons.Cupom;
import br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos.Produto;
import java.util.List;

public class Venda 
{
    public int id;
    public List<Produto> produtosComprados;
    public Cupom cupomAplicado;
}

// As vendas devem exibir um resumo com os produtos comprados, cupom aplicado (se houver) e o valor final.