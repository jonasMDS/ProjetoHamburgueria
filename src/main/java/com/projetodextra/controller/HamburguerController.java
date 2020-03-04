package com.projetodextra.controller;

import com.projetodextra.model.Hamburguer;
import com.projetodextra.model.Ingrediente;
import com.projetodextra.model.Promocao;
import com.projetodextra.repository.HamburguerRepository;
import com.projetodextra.repository.IngredienteRepository;
import com.projetodextra.utils.DadosEstaticos;
import com.projetodextra.utils.LeitorDados;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HamburguerController {

    private final HamburguerRepository hamburguerRepository;
    private final IngredienteRepository ingredienteRepository;


    public HamburguerController() {
        LeitorDados leitor = new LeitorDados(new DadosEstaticos("src/hamburguer.txt", "src/ingrediente.txt"));
        this.hamburguerRepository = new HamburguerRepository(leitor);
        this.ingredienteRepository = new IngredienteRepository(leitor);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/hamburguer/todos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Hamburguer> buscarHamburgueres() {
        return this.hamburguerRepository.getAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/hamburguer/calcular", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Promocao calcularPreco(@RequestBody List<Ingrediente> ingredientes) {
        Hamburguer h = new Hamburguer(ingredientes);
        return h.calcularPrecoPromocao();
    }

    // Busca de todos os Ingredientes

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/ingrediente/todos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ingrediente> buscarIngredientes() {
        return this.ingredienteRepository.getAll();
    }

}