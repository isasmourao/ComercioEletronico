
package br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos;

public abstract class Produto 
{
    protected int id;
    protected String nome;
    protected double preco;
    
    public String getNome() 
    {
        return this.nome;
    }

    public double getPreco() 
    {
        return this.preco;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public void setPreco(double preco) 
    {
        this.preco = preco;
    }
}
