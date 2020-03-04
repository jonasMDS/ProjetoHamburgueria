import {Component, OnInit} from '@angular/core';
import {Hamburguer} from "./models/hamburguer";
import {HamburguerService} from "./services/hamburguer.service";

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

  constructor(private hamburguerService: HamburguerService) {}

  ngOnInit() {
    this.getHamburgueres();
  }

  // Chamando o serviço que retorna todos os hamburgueres
  getHamburgueres() {
    this.hamburguerService.getHamburgueres().subscribe((hamburgueres: Hamburguer[]) => {
      this.hamburgueres = hamburgueres;
    });
  }

  calcularPrecoHamburguer(h: Hamburguer){
    this.hamburguerService.calcularPrecoHamburguer(this.h).subscribe(valor=> {
      this.precoFinal = valor;
    });
  }

}
