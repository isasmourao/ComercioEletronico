package br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Alimento extends Produto
{
    private String dataValidade;

    public Alimento(int id, String nome, double preco, String dataValidade) 
    {
        super(id, nome, preco);
        if (verificarSeDataValidadeEhCorreta(dataValidade)) 
        {
            this.dataValidade = dataValidade;
        } 
        else 
        {
            throw new IllegalArgumentException("Data de validade inválida.");
        }
    }

    private boolean verificarSeDataValidadeEhCorreta(String data) 
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try 
        {
            LocalDate.parse(data, formatter);
            return true;
        } 
        catch (DateTimeParseException e) 
        {
            return false;
        }
    }
}