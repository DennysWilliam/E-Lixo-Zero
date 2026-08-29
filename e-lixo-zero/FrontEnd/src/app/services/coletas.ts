import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Coleta } from '../models/coleta.model';

@Injectable({
  providedIn: 'root',
})
export class ColetasService {
  private http = inject(HttpClient);

  private api = 'http://localhost:8087/api/coletas';

  listar(): Observable<Coleta[]> {
    return this.http.get<Coleta[]>(this.api);
  }

  criar(coleta: Omit<Coleta, 'id'>): Observable<Coleta> {
    return this.http.post<Coleta>(this.api, coleta);
  }

  buscarPorId(id: number) {
  return this.http.get<Coleta>(`${this.api}/${id}`);
}

  atualizarStatus(id: number, status: string): Observable<Coleta> {
    return this.http.put<Coleta>(`${this.api}/${id}/status`, { status });
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}