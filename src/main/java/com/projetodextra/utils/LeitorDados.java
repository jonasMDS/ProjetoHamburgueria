package com.projetodextra.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetodextra.model.Hamburguer;
import com.projetodextra.model.Ingrediente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class LeitorDados {
    private static final Logger logger = LoggerFactory.getLogger(LeitorDados.class);
    private DadosEstaticos arquivos;
    private List<Hamburguer> hamburgueres;
    private List<Ingrediente> ingredientes;

    public LeitorDados(){};


    public LeitorDados(DadosEstaticos arquivos) {
        this.arquivos = arquivos;

        // Leitura dos Ingredientes
        try {
            File arquivoIngrediente = new File(arquivos.getArquivoIngredientes());
            Scanner leitor = new Scanner(arquivoIngrediente);

            List<Ingrediente> listaIngredientes = new ArrayList<>();
            while (leitor.hasNextLine()) {                                                      // Leitura da lista de ingredientes
                String[] linha = leitor.nextLine().split(",");
                listaIngredientes.add(new Ingrediente(Integer.parseInt(linha[0]), linha[1], Double.parseDouble(linha[2]), 1));
            }
            leitor.close();
            this.ingredientes = listaIngredientes;
        } catch (IOException e) {
            logger.error("Error reading file {}", this.arquivos.getArquivoIngredientes(), e);
            this.ingredientes = new ArrayList<>();
        }


        // Leitura do arquivo de hamburgueres
        try {

            File arquivoHamburguer = new File(arquivos.getArquivoHamburgueres());
            Scanner leitor = new Scanner(arquivoHamburguer);

            List<Hamburguer> listaHamburgueres = new ArrayList<>();

            while (leitor.hasNextLine()) {
                String[] linha = leitor.nextLine().split(",");
                List<Ingrediente> ingredientesVinculados = Ingrediente.ingredientesVinculados(strToIntArray(linha[2].split("/")), this.ingredientes);
                listaHamburgueres.add(new Hamburguer(Integer.parseInt(linha[0]), linha[1], ingredientesVinculados, Hamburguer.calcularPreco(ingredientesVinculados)));
            }
            this.hamburgueres = listaHamburgueres;
        } catch (IOException e) {
            logger.error("Error reading file {}", this.arquivos.getArquivoIngredientes(), e);
            this.ingredientes = new ArrayList<>();
        }
    }

    private static int[] strToIntArray(String[] vetor){
        int[] resultado = new int[vetor.length];
        for (int i = 0; i < vetor.length; i++){
            resultado[i] = Integer.parseInt(vetor[i]);
        }
        return  resultado;
    }

    public List<Hamburguer> getHamburgers() {
        return this.hamburgueres;
    }

    public List<Ingrediente> getIngredients() {
        return this.ingredientes;
    }
}