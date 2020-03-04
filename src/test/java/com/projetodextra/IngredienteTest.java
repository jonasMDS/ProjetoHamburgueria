
package com.projetodextra;

import com.projetodextra.utils.DadosEstaticos;
import com.projetodextra.utils.LeitorDados;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IngredienteTest {
    @Test
    public void testPrecos() {
        LeitorDados leitorDados = new LeitorDados(new DadosEstaticos("src/hamburguer.txt", "src/ingredientes.txt"));

        Assertions.assertEquals(0.40, leitorDados.getIngrediente(1).getPreco());
        Assertions.assertEquals(2.00, leitorDados.getIngrediente(2).getPreco());
        Assertions.assertEquals(3.00, leitorDados.getIngrediente(3).getPreco());
        Assertions.assertEquals(0.80, leitorDados.getIngrediente(4).getPreco());
        Assertions.assertEquals(1.50, leitorDados.getIngrediente(5).getPreco());
        Assertions.assertNull(leitorDados.getHamburger(6));
    }
    

}