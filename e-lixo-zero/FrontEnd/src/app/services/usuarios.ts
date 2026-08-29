import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { Usuario } from '../models/usuario.model';

@Injectable({
  providedIn: 'root',
})
export class UsuariosService {
  private http = inject(HttpClient);
  private api = 'http://localhost:8087/api/usuarios';

  listar(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(this.api);
  }

  buscarPorId(id: number): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.api}/${id}`);
  }

  criar(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(this.api, usuario);
  }

  atualizar(id: number, usuario: Usuario): Observable<Usuario> {
    return this.http.put<Usuario>(`${this.api}/${id}`, usuario);
  }

  login(email: string, senha: string): Observable<Usuario> {
    return this.http.post<Usuario>(`${this.api}/login`, { email, senha });
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}