package com.projetodextra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Ingrediente {
    private int id_ingrediente;
    private String nome;
    private float preco;
    private int quantidade;


    public Ingrediente(int id_ingrediente, String nome, float preco, int quantidade){
        if (id_ingrediente > 0){
            this.id_ingrediente = id_ingrediente;
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

    public static Ingrediente[] buscarIngredientes(){
        try {
            File arquivoIngrediente = new File("src/ingrediente.txt");
            Scanner leitor = new Scanner(arquivoIngrediente);

            int qtd = Integer.parseInt(leitor.nextLine());                                      // Primeira linha indica quantidade de dados
            Ingrediente[] vetorIngrediente = new Ingrediente[qtd];
            int cont = 0;
            while (leitor.hasNextLine()) {                                                      // Leitura da lista de ingredientes
                String[] linha = leitor.nextLine().split(",");
                vetorIngrediente[cont] = new Ingrediente(Integer.parseInt(linha[0]), linha[1], Float.parseFloat(linha[2]), 1);
                cont++;
            }
            leitor.close();

            return vetorIngrediente;
        } catch (FileNotFoundException e) {
            System.out.println("Nao foi possivel encontrar o arquivo de ingredientes");
            e.printStackTrace();
            return null;
        }
    }

    public static Ingrediente[] ingredientesVinculados(int[] id_ingredientes, Ingrediente[] todos){
        Ingrediente[] vinculados = new Ingrediente[id_ingredientes.length];
        int contIngrediente = 0;
        for (Ingrediente ingrediente: todos){
            if (IntStream.of(id_ingredientes).anyMatch(i -> i == ingrediente.getId())){             //Verificando se o ingredientes esta na lista do lanche
                vinculados[contIngrediente] = ingrediente;
                contIngrediente++;
            }
        }

        return vinculados;
    }

    public int getId(){
        return this.id_ingrediente;
    }

    public String getNome(){
        return this.nome;
    }

    public int getQuantidade() {
        return  this.quantidade;
    }

    public float getPreco() {
        return this.preco;
    }

}
