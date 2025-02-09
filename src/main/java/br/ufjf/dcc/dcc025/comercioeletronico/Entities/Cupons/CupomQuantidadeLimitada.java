
package br.ufjf.dcc.dcc025.comercioeletronico.Entities.Cupons;

import br.ufjf.dcc.dcc025.comercioeletronico.Exceptions.CupomInvalidoException;

public class CupomQuantidadeLimitada extends Cupom
{
    public static final int MAXIMO_UTILIZACOES = 3;
    public int utilizacoesAtuais;

    public CupomQuantidadeLimitada(int id, double percentualDesconto, boolean ativo, int utilizacoesAtuais) throws CupomInvalidoException 
    {
        super(id, percentualDesconto, ativo);
        this.utilizacoesAtuais = utilizacoesAtuais;
    }

    public boolean atingiuMaximoUtilizacoes() 
    {
        return utilizacoesAtuais >= MAXIMO_UTILIZACOES;
    }

    public void utilizarCupom() 
    {
        if (!atingiuMaximoUtilizacoes()) 
        {
            utilizacoesAtuais++;
        }
        else
        {
            this.ativo = false;
        }
           
    }
}
