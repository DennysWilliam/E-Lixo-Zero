import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { map, catchError } from 'rxjs';
import { of } from 'rxjs';

import { Usuario, UsuarioCompat } from '../models/usuario.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private http = inject(HttpClient);
  private api = 'http://localhost:8087/api/usuarios';
  private chave = 'usuarioLogado';

  login(email: string, senha: string) {
    return this.http.post<any>(`${this.api}/login`, { email, senha }).pipe(
      map((usuario) => {
        console.log('Usuário recebido do backend:', usuario);
        
        // Converter para formato compatível (não incluir senha por segurança)
        const usuarioCompat: UsuarioCompat = {
          id: String(usuario.id),
          nome: usuario.nomeCompleto,
          email: usuario.email,
          senha: '', // Não armazenar senha no localStorage por segurança
          logradouro: usuario.logradouro || '',
          numero: usuario.numero || '',
          bairro: usuario.bairro || '',
          cidade: usuario.cidade || ''
        };
        
        localStorage.setItem(this.chave, JSON.stringify(usuarioCompat));
        console.log('Usuário salvo no localStorage:', usuarioCompat);
        return true;
      }),
      catchError((error) => {
        console.error('Erro no login:', error);
        console.error('Status:', error.status);
        console.error('Mensagem:', error.message);
        return of(false);
      })
    );
  }

  logout(): void {
    localStorage.removeItem(this.chave);
  }

  estaLogado(): boolean {
    return localStorage.getItem(this.chave) !== null;
  }

  cadastrar(usuario: Omit<Usuario, 'id'>) {
    return this.http.post<Usuario>(this.api, usuario);
  }
}