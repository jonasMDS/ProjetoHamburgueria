package com.projetodextra;

public class Promocao {

    private Ingrediente[] ingredientes;

    public Promocao(Ingrediente[] ingredientes){
        if (ingredientes.length > 0){
            this.ingredientes = ingredientes;
        }

    }

    public boolean descontoLight(){
        boolean alface = false;
        boolean bacon = false;
        for (Ingrediente ingrediente : this.ingredientes) {
            if (ingrediente.getId() == 1) {                              // 1 = Alface
                alface = true;
            } else if (ingrediente.getId() == 2) {                       // 2 = Bacon
                bacon = true;
            }
        }

        return alface && !bacon;
    }

    // Retorno baseado no numero de hamburguers a serem descontados
    // Ex: 0 (nenhum desconto), 2 (2 hamburgueres desconto)
    //
    public int descontoCarne(){

        for (Ingrediente ingrediente : this.ingredientes) {
            if (ingrediente.getId() == 3) {                                 // 3 = Hamburguer de Carne
                if (ingrediente.getQuantidade() > 3) {
                    return ingrediente.getQuantidade() / 3;
                }
            }
        }
        return 0;
    }
    // Retorno baseado no numero de queijos a serem descontados
    // Ex: 0 (nenhum desconto), 2 (2 queijos de desconto)
    //
    public int descontoQueijo(){
        for (Ingrediente ingrediente : this.ingredientes) {
            if (ingrediente.getId() == 5) {                                 // 5 =  Queijo
                if (ingrediente.getQuantidade() > 3) {
                    return ingrediente.getQuantidade() / 3;
                }
            }
        }
        return 0;
    }
}
