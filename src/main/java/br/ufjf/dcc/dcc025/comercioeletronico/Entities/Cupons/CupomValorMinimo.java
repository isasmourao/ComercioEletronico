
package br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons;

import br.ufjf.dcc.dcc025.comercioeletronico.Exceptions.CupomInvalidoException;

public class CupomValorMinimo extends Cupom
{
    public double valorMinimo = 200;

    public CupomValorMinimo() throws CupomInvalidoException 
    {
        super(0, 5.0, true);
        if (valorMinimo < 0) 
        {
            throw new CupomInvalidoException("O valor mínimo não pode ser negativo.");
        }
        
        this.valorMinimo = 200.0;
    }
        
    public CupomValorMinimo(int id, double percentualDesconto, boolean ativo, double valorMinimo) throws CupomInvalidoException 
    {
        super(id, percentualDesconto, ativo);
        if (valorMinimo < 0) 
        {
            throw new CupomInvalidoException("O valor mínimo não pode ser negativo.");
        }
        this.valorMinimo = valorMinimo;
    }
  
    public boolean valorMinimoAtingido(double total)
    {
        return total >= this.valorMinimo;
    }
}
