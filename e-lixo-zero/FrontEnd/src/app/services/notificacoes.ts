import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { Notificacao } from '../models/notificacao.model';

@Injectable({
  providedIn: 'root',
})
export class NotificacoesService {
  private http = inject(HttpClient);
  private api = 'http://localhost:3000/notificacoes';

  listar(): Observable<Notificacao[]> {
    return this.http.get<Notificacao[]>(this.api);
  }

  buscarPorId(id: number): Observable<Notificacao> {
    return this.http.get<Notificacao>(`${this.api}/${id}`);
  }

  listarPorUsuario(usuarioId: number): Observable<Notificacao[]> {
    return this.http.get<Notificacao[]>(`${this.api}/usuario/${usuarioId}`);
  }

  listarNaoLidasPorUsuario(usuarioId: number): Observable<Notificacao[]> {
    return this.http.get<Notificacao[]>(`${this.api}/usuario/${usuarioId}/nao-lidas`);
  }

  criar(notificacao: Notificacao): Observable<Notificacao> {
    return this.http.post<Notificacao>(this.api, notificacao);
  }

  marcarComoLida(id: number): Observable<Notificacao> {
    return this.http.put<Notificacao>(`${this.api}/${id}/marcar-lida`, {});
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}