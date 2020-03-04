
package com.projetodextra;

import com.projetodextra.model.Hamburguer;
import com.projetodextra.model.Ingrediente;
import com.projetodextra.model.Promocao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PromocaoTest {
    @Test
    public void testSemPromocao() {
        List<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes.add(new Ingrediente(1, "Alface", 0.40f, 1));
        ingredientes.add(new Ingrediente(2, "Bacon", 2.00f, 1));
        ingredientes.add(new Ingrediente(3, "Hambúrguer de carne", 3.00f, 1));
        ingredientes.add(new Ingrediente(4, "Ovo", 0.80f, 1));
        ingredientes.add(new Ingrediente(5, "Queijo", 1.50f, 1));

        Promocao promocao = new Promocao(ingredientes);

        Assertions.assertFalse(promocao.descontoLight());
        Assertions.assertEquals(0, promocao.descontoCarne());
        Assertions.assertEquals(0, promocao.descontoQueijo());
        Assertions.assertEquals(7.70, Hamburguer.calcularPreco(ingredientes), 0.10);
    }

    @Test
    public void testComPromocao() {
        List<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes.add(new Ingrediente(1, "Alface", 0.40f, 1));
        ingredientes.add(new Ingrediente(3, "Hambúrguer de carne", 3.00f, 3));
        ingredientes.add(new Ingrediente(4, "Ovo", 0.80f, 1));
        ingredientes.add(new Ingrediente(5, "Queijo", 1.50f, 3));

        Promocao promocao = new Promocao(ingredientes);

        Assertions.assertTrue(promocao.descontoLight());
        Assertions.assertEquals(1, promocao.descontoCarne());
        Assertions.assertEquals(1, promocao.descontoQueijo());
        Assertions.assertEquals(10.20, Hamburguer.calcularPreco(ingredientes), 0.10);
    }
}