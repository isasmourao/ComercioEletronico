package br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Roupa extends Produto
{
    public char tamanho;
    public String cor;
    
    public Roupa(char tamanho, String cor)
    {
        if (verificarSeTamanhoEhValido(tamanho))
            this.tamanho = tamanho;
        else
        {
            throw new IllegalArgumentException("Tamanho inválido! Use P, M, G ou X.");
        }

        this.cor = cor;

        // Definição de um nome padrão e um preço fixo para testes
        this.setNome("Roupa " + cor);
        this.setPreco(50.0); // Preço padrão para testes
    }
    
    private boolean verificarSeTamanhoEhValido(char tamanho)
    {
        Set<Character> tamanhosValidos = new HashSet<>(Arrays.asList('P', 'M', 'G', 'X'));
        return tamanhosValidos.contains(tamanho);
    }
}

