
package br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons;

import br.ufjf.dcc.dcc025.comercioeletronico.Exceptions.CupomInvalidoException;

public class Cupom 
{
    public int id;
    public double percentualDesconto;
    public boolean ativo;
    
    public Cupom(){}
    
    public Cupom(int id, double percentualDesconto, boolean ativo) throws CupomInvalidoException
    {
        this.id = id;
        if (!percentualDescontoEhValido(percentualDesconto))
            throw new CupomInvalidoException("Percentual de desconto aplicado não é válido!");
        
        this.percentualDesconto = percentualDesconto;    
        this.ativo = ativo;
    }
    
    private boolean percentualDescontoEhValido(double percentualDesconto)
    {
        return percentualDesconto >= 0 && percentualDesconto <= 100;        
    }
}

