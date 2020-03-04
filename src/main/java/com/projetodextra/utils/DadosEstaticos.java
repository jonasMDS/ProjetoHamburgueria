package com.projetodextra.utils;


public class DadosEstaticos {
    private String arquivoIngredientes;
    private String arquivoHamburgueres;

    public DadosEstaticos(String enderecoHamburguer, String enderecoIngrediente){
        if (!enderecoHamburguer.isEmpty()){
            this.arquivoHamburgueres = enderecoHamburguer;
        }
        if (!enderecoIngrediente.isEmpty()){
            this.arquivoIngredientes = enderecoIngrediente;
        }
    }

    public String getArquivoHamburgueres() {
        return arquivoHamburgueres;
    }

    public String getArquivoIngredientes() {
        return arquivoIngredientes;
    }
}
