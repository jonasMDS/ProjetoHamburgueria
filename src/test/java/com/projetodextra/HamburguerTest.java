
package com.projetodextra;

import com.projetodextra.model.Hamburguer;
import com.projetodextra.model.Ingrediente;
import com.projetodextra.model.Promocao;
import com.projetodextra.utils.DadosEstaticos;
import com.projetodextra.utils.LeitorDados;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class HamburguerTest {
    @Test
    public void testPrecos() {
        LeitorDados leitorDados = new LeitorDados(new DadosEstaticos("src/hamburguer.txt", "src/ingredientes.txt"));

        Assertions.assertEquals(6.50, leitorDados.getHamburger(1).getPreco());
        Assertions.assertEquals(4.50, leitorDados.getHamburger(2).getPreco());
        Assertions.assertEquals(5.30, leitorDados.getHamburger(3).getPreco());
        Assertions.assertEquals(7.30, leitorDados.getHamburger(4).getPreco());
        Assertions.assertNull(leitorDados.getHamburger(5));
    }


}