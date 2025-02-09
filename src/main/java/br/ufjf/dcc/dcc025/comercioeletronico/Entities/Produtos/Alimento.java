package br.ufjf.dcc.dcc025.comercioeletronico.Entities.Produtos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Alimento extends Produto
{
    public String dataValidade;
    
    public Alimento(String dataValidade)
    {
        if (verificarSeDataValidadeEhCorreta(dataValidade))
        {
            this.dataValidade = dataValidade;
        }
        else
        {
            // lançar alguma exception
        }
    }
    
    private boolean verificarSeDataValidadeEhCorreta(String data) 
    {
        // formato esperado (dd-MM-yyyy)
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