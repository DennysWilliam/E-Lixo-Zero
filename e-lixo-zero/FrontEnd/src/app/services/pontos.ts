import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { PontoColeta } from '../models/ponto-coleta.model';

@Injectable({
  providedIn: 'root',
})
export class PontosService {
  private http = inject(HttpClient);

  private api = 'http://localhost:3000/pontos-coleta';

  listar(): Observable<PontoColeta[]> {
    return this.http.get<PontoColeta[]>(this.api);
  }

  buscarPorId(id: number): Observable<PontoColeta> {
    return this.http.get<PontoColeta>(`${this.api}/${id}`);
  }

  buscarPorCidade(cidade: string): Observable<PontoColeta[]> {
    return this.http.get<PontoColeta[]>(`${this.api}/cidade/${cidade}`);
  }

  criar(ponto: PontoColeta): Observable<PontoColeta> {
    return this.http.post<PontoColeta>(this.api, ponto);
  }

  atualizar(id: number, ponto: PontoColeta): Observable<PontoColeta> {
    return this.http.put<PontoColeta>(`${this.api}/${id}`, ponto);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}