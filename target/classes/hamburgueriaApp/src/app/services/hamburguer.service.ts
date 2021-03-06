import { BrowserModule } from '@angular/platform-browser';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Hamburguer } from '../models/hamburguer';
import {Ingrediente} from "../models/ingrediente";
import {Promocao} from "../models/promocao";

@Injectable({
  providedIn: 'root'
})
export class HamburguerService {
  url = 'http://localhost:8080';

  // injetando o HttpClient
  constructor(private httpClient: HttpClient) { }

  // Headers
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  // Obtem todos os hamburgueres
  getHamburgueres(): Observable<Hamburguer[]> {
    return this.httpClient.get<Hamburguer[]>(this.url + '/hamburguer/todos')
      .pipe(
        retry(2),
        catchError(this.handleError))
  }

  // devolve o preco
  calcularPrecoHamburguer(ings: Ingrediente[]): Observable<Promocao> {
    return this.httpClient.post<Promocao>(this.url + '/hamburguer/calcular', JSON.stringify(ings), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // Manipulação de erros
  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Código do erro: ${error.status}, ` + `mensagem: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  };
}
