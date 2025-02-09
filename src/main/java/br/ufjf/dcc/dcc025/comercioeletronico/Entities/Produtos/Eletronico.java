
package br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos;

public class Eletronico extends Produto
{
    private final int garantiaMeses;

    public Eletronico(int id, String nome, double preco, int garantiaMeses) 
    {
        super(id, nome, preco);
        this.garantiaMeses = garantiaMeses;
    }
}
