package com.projetodextra.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Ingrediente {
    private int idIngrediente;
    private String nome;
    private double preco;
    private int quantidade;

    public Ingrediente(){}

    public Ingrediente(int id_ingrediente, String nome, double preco, int quantidade){
        if (id_ingrediente > 0){
            this.idIngrediente = id_ingrediente;
        }
        if (!nome.isEmpty()){
            this.nome = nome;
        }
        if (preco > 0){
            this.preco = preco;
        }
        if (quantidade > 1){
            this.quantidade = quantidade;
        } else {
            this.quantidade = 1;
        }
    }

    public static List<Ingrediente> ingredientesVinculados(int[] id_ingredientes, List<Ingrediente> todos){
        List<Ingrediente> vinculados = new ArrayList<>();

        for (Ingrediente ingrediente: todos){
            if (IntStream.of(id_ingredientes).anyMatch(i -> i == ingrediente.getIdIngrediente())){             //Verificando se o ingredientes esta na lista do lanche
                vinculados.add(ingrediente);
            }
        }

        return vinculados;
    }

    public int getIdIngrediente(){
        return this.idIngrediente;
    }

    public String getNome(){
        return this.nome;
    }

    public int getQuantidade() {
        return  this.quantidade;
    }

    public double getPreco() {
        return this.preco;
    }

}
