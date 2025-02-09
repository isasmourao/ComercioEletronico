package br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Roupa extends Produto
{
    private char tamanho;
    private final String cor;

    public Roupa(int id, String nome, double preco, char tamanho, String cor) 
    {
        super(id, nome, preco);
        if (verificarSeTamanhoEhValido(tamanho)) 
        {
            this.tamanho = tamanho;
        } 
        else 
        {
            throw new IllegalArgumentException("Tamanho inválido!");
        }
        this.cor = cor;
    }

    private boolean verificarSeTamanhoEhValido(char tamanho) 
    {
        Set<Character> tamanhosValidos = new HashSet<>(Arrays.asList('P', 'M', 'G', 'X'));
        return tamanhosValidos.contains(tamanho);
    }
}

