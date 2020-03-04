package com.projetodextra.model;

import java.util.List;
import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors


public class Hamburguer {

    private int id_hamburguer;
    private String nome;
    private List<Ingrediente> ingredientes;
    private double preco;


    public Hamburguer(int id_hamburguer, String nome, List<Ingrediente> ingredientes, double preco){
        if (id_hamburguer > 0){
            this.id_hamburguer = id_hamburguer;
        }
        if (!nome.isEmpty()){
            this.nome = nome;
        }
        if (!ingredientes.isEmpty()){
            this.ingredientes = ingredientes;
        }
        if (preco > 0){
            this.preco = preco;
        }
    }

    public Hamburguer(List<Ingrediente> ingredientes){

        if (!ingredientes.isEmpty()){
            this.ingredientes = ingredientes;
        }
    }

    public String getNome() {
        return nome;
    }

    public int getIdHamburguer() {
        return id_hamburguer;
    }

    public List<Ingrediente> getIngredientes(){
        return this.ingredientes;
    }

    public double getPreco(){
        return this.preco;
    }

    public static double calcularPreco(List<Ingrediente> ingredientes){

        double preco_final = 0;

        for (Ingrediente ingrediente : ingredientes) {
            preco_final += ingrediente.getPreco() * ingrediente.getQuantidade();
        }

        return preco_final;
    }

    public double calcularPrecoPromocao(){

        double preco_final = 0;

        Promocao p = new Promocao(this.ingredientes);
        for (Ingrediente ingrediente : this.ingredientes) {
            switch (ingrediente.getIdIngrediente()){

                case 1:         // Alface
                case 2:         // Bacon
                case 4:         // Ovo
                    preco_final += ingrediente.getPreco() * ingrediente.getQuantidade();
                    break;
                case 3:
                    int descontoC = p.descontoCarne();
                    preco_final += ingrediente.getPreco() * (ingrediente.getQuantidade() - descontoC);
                    break;
                case 5:
                    int descontoQ = p.descontoQueijo();
                    preco_final += ingrediente.getPreco() * (ingrediente.getQuantidade() - descontoQ);
                    break;
            }
        }
        return p.descontoLight()? (preco_final*0.9):preco_final;
    }

    private static int[] strToIntArray(String[] vetor){
        int[] resultado = new int[vetor.length];
        for (int i = 0; i < vetor.length; i++){
            resultado[i] = Integer.parseInt(vetor[i]);
        }
        return  resultado;
    }


}
