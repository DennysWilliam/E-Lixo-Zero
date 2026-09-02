import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Residuo } from '../models/residuo.model';

@Injectable({
  providedIn: 'root',
})
export class ResiduosService {
  private http = inject(HttpClient);

  private api = 'http://localhost:8087/api/residuos';

  listar(): Observable<Residuo[]> {
    return this.http.get<Residuo[]>(this.api);
  }

  buscarPorId(id: number): Observable<Residuo> {
    return this.http.get<Residuo>(`${this.api}/${id}`);
  }

  buscarPorCategoria(categoria: string): Observable<Residuo[]> {
    return this.http.get<Residuo[]>(`${this.api}/categoria/${categoria}`);
  }

  criar(residuo: Residuo): Observable<Residuo> {
    return this.http.post<Residuo>(this.api, residuo);
  }

  atualizar(id: number, residuo: Residuo): Observable<Residuo> {
    return this.http.put<Residuo>(`${this.api}/${id}`, residuo);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}