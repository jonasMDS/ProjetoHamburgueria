package com.projetodextra.repository;

import com.projetodextra.model.Ingrediente;
import com.projetodextra.utils.LeitorDados;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IngredienteRepository {
    private final LeitorDados reader;

    public IngredienteRepository(LeitorDados reader) {
        this.reader = reader;
    }

    public List<Ingrediente> getAll() {
        return this.reader.getIngredients();
    }
}