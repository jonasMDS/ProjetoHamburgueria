import {Ingrediente} from "./ingrediente";

export interface Hamburguer {
  id_hamburguer: number;
  nome: String;
  ingredientes : Array<Ingrediente>;

}
