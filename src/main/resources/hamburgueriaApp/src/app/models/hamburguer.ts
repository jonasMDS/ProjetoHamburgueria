import {Ingrediente} from "./ingrediente";

export interface Hamburguer {
  idHamburguer: number;
  nome: String;
  ingredientes : Array<Ingrediente>;
  preco: number;

}
