import {Component, OnInit} from '@angular/core';
import {Hamburguer} from "./models/hamburguer";
import {HamburguerService} from "./services/hamburguer.service";
import {IngredienteService} from "./services/ingrediente.service";
import {Ingrediente} from "./models/ingrediente";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'hamburgueriaApp';

  h = {} as Hamburguer;
  precoFinal : number;
  hamburgueres: Hamburguer[];
  ingredientes: Ingrediente[];
  telaAtual: number;

  constructor(private hamburguerService: HamburguerService, private ingredienteService: IngredienteService) {}

  ngOnInit() {
    this.getHamburgueres();

    this.getIngredientes();
    this.telaAtual = 0;
  }


  // Chamando o serviço que retorna todos os hamburgueres
  getHamburgueres() {
    this.hamburguerService.getHamburgueres().subscribe((hamburgueres: Hamburguer[]) => {
      this.hamburgueres = hamburgueres;
    });
  }


  // Chamando o serviço que retorna todos os hamburgueres
  getIngredientes() {
    this.ingredienteService.getIngredientes().subscribe((ingredientes: Ingrediente[]) => {
      this.ingredientes = ingredientes;
    });
  }

  calcularPrecoHamburguer(){
    this.hamburguerService.calcularPrecoHamburguer(this.ingredientes).subscribe(valor => {
      this.precoFinal = valor;
    });
  }

  novoPedido(){
    this.telaAtual = 1;
  }

  identificador(index, item){
    return index;
  }

  selecionarHamburguer(i: number){
    this.h = this.hamburgueres[i];
    for (var i = 0; i < this.ingredientes.length; i++) {
      this.ingredientes[i].quantidade = 0;
      for (var j = 0; j < this.h.ingredientes.length; j++) {
        if (this.ingredientes[i].idIngrediente === this.h.ingredientes[j].idIngrediente){
          this.ingredientes[i].quantidade =  this.h.ingredientes[j].quantidade;
          console.log(this.ingredientes[i].idIngrediente);
          console.log(this.h.ingredientes[j].idIngrediente);
        }
      }
    }

    this.telaAtual = 2;
    console.log(i);
  }


}
