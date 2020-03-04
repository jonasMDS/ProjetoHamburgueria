package com.projetodextra;

import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors


public class Hamburguer {

    private int id_hamburguer;
    private String nome;
    private Ingrediente[] ingredientes;


    public Hamburguer(int id_hamburguer, String nome, Ingrediente[] ingredientes){
        if (id_hamburguer > 0){
            this.id_hamburguer = id_hamburguer;
        }
        if (!nome.isEmpty()){
            this.nome = nome;
        }
        if (ingredientes.length > 0){
            this.ingredientes = ingredientes;
        }
    }

    public Hamburguer(int id_hamburguer, String nome){
        if (id_hamburguer > 0){
            this.id_hamburguer = id_hamburguer;
        }
        if (!nome.isEmpty()){
            this.nome = nome;
        }
    }

    public String getNome() {
        return nome;
    }

    public int getId_hamburguer() {
        return id_hamburguer;
    }

    public Ingrediente[] getIngredientes(){
        return this.ingredientes;
    }

    public static Hamburguer[] buscarHamburgueres(){
        try {
            Ingrediente[] ingredientes = Ingrediente.buscarIngredientes();

            File arquivoHamburguer = new File("src/hamburguer.txt");                                // Identificando o Arquivo dos dados do Hamburguer
            Scanner leitorHamburguer = new Scanner(arquivoHamburguer);

            int qtdHamburguer = Integer.parseInt(leitorHamburguer.nextLine());                                // Primeira linha indica quantidade de dados
            Hamburguer[] vetorHamburguer = new Hamburguer[qtdHamburguer];

            int cont = 0;
            while (leitorHamburguer.hasNextLine()) {                                                          // Leitura do Arquivo de armazenamento de Hamburgueres
                String[] linha = leitorHamburguer.nextLine().split(",");
                vetorHamburguer[cont] = new Hamburguer(Integer.parseInt(linha[0]), linha[1],Ingrediente.ingredientesVinculados(strToIntArray(linha[2].split("/")), ingredientes));
                cont++;
            }
            leitorHamburguer.close();


            return vetorHamburguer;
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possível encontrar a lista de hamburgueres");
            e.printStackTrace();
            return null;
        }
    }

    public double calcularPreco(){

        float preco_final = 0;

        Promocao p = new Promocao(this.ingredientes);
        for (Ingrediente ingrediente : this.ingredientes) {
            switch (ingrediente.getId()){

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
