
package com.projetodextra.repository;

import com.projetodextra.model.Hamburguer;
import com.projetodextra.utils.LeitorDados;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HamburguerRepository {
    private final LeitorDados reader;

    public HamburguerRepository(LeitorDados reader) {
        this.reader = reader;
    }

    public List<Hamburguer> getAll() {
        return this.reader.getHamburgers();
    }
}