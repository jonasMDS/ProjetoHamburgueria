package com.projetodextra.model;

import com.projetodextra.model.Ingrediente;

import java.util.List;

public class Promocao {

    private List<Ingrediente> ingredientes;

    public Promocao(List<Ingrediente> ingredientes){
        if (!ingredientes.isEmpty()){
            this.ingredientes = ingredientes;
        }

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
}
