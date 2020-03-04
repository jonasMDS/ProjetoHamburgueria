package com.projetodextra;

import com.fasterxml.jackson.databind.util.JSONPObject;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class HamburgueriaController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {

        return "working";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/hamburguer/todos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Hamburguer[] buscarHamburguers() {
        try {
            return Hamburguer.buscarHamburgueres(); //error here
        } catch (Exception e) {

            System.out.println("Não foi possivel obter a lista de hamburgueres.");
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/hamburguer/calcular", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public double calcularPreco(@RequestBody Ingrediente[] ingredientes) {
        Hamburguer h = new Hamburguer(10, "Hamburguer de Calculo", ingredientes);
        return h.calcularPreco();
    }

}