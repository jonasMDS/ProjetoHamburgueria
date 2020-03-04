package com.projetodextra.model;

import com.projetodextra.model.Ingrediente;

import java.util.List;

public class Promocao {

    private List<Ingrediente> ingredientes;
    private int muitaCarne;
    private int muitoQueijo;
    private boolean light;
    private double precoFinal;



    public Promocao(List<Ingrediente> ingredientes){
        if (!ingredientes.isEmpty()){
            this.ingredientes = ingredientes;
        }

    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public boolean isLight() {
        return this.light;
    }

    public int getMuitaCarne() {
        return this.muitaCarne;
    }

    public int getMuitoQueijo() {
        return this.muitoQueijo;
    }

    /**
     * Um resultado booleano informando se com base nos ingredientes
     * haverá ou não o desconto de 10% por ser considerado Light
     * @return boolean
     */
    public boolean descontoLight(){
        boolean alface = false;
        boolean bacon = false;
        for (Ingrediente ingrediente : this.ingredientes) {
            if (ingrediente.getIdIngrediente() == 1) {                              // 1 = Alface
                alface = true;
            } else if (ingrediente.getIdIngrediente() == 2) {                       // 2 = Bacon
                bacon = true;
            }
        }

        return alface && !bacon;
    }

    /**
     * A quantidade de hamburgueres que serão descontados devido a promoção
     * "Muita Carne"(A cada 3 Hamburgueres, você só paga 2).
     * @return  <code>int</code> numero de hamburgueres descontados
     */
    public int descontoCarne(){

        for (Ingrediente ingrediente : this.ingredientes) {
            if (ingrediente.getIdIngrediente() == 3) {                                 // 3 = Hamburguer de Carne
                if (ingrediente.getQuantidade() > 3) {
                    return ingrediente.getQuantidade() / 3;
                }
            }
        }
        return 0;
    }

    /**
     * A quantidade de queijos que serão descontados devido a promoção
     * "Muita o Queijo"(A cada 3 queijos, você só paga 2).
     * @return  <code>int</code> numero de queijos descontados
     */
    public int descontoQueijo(){
        for (Ingrediente ingrediente : this.ingredientes) {
            if (ingrediente.getIdIngrediente() == 5) {                                 // 5 =  Queijo
                if (ingrediente.getQuantidade() > 3) {
                    return ingrediente.getQuantidade() / 3;
                }
            }
        }
        return 0;
    }

    protected Promocao calcularPromocao(){
        Promocao p = new Promocao(this.ingredientes);
        p.precoFinal = 0;
        for (Ingrediente ingrediente : p.ingredientes) {
            switch (ingrediente.getIdIngrediente()){

                case 1:         // Alface
                case 2:         // Bacon
                case 4:         // Ovo
                    p.precoFinal += ingrediente.getPreco() * ingrediente.getQuantidade();
                    break;
                case 3:
                    p.muitaCarne = p.descontoCarne();
                    p.precoFinal += ingrediente.getPreco() * (ingrediente.getQuantidade() - p.muitaCarne);
                    break;
                case 5:
                    p.muitoQueijo = p.descontoQueijo();
                    p.precoFinal += ingrediente.getPreco() * (ingrediente.getQuantidade() - p.muitoQueijo);
                    break;
            }
        }
        p.light = p.descontoLight();
        p.precoFinal = p.light? (p.precoFinal*0.9):p.precoFinal;
        return p;

    };
}
