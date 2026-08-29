import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Usuario, UsuarioCompat } from '../../models/usuario.model';

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './perfil.html',
  styleUrl: './perfil.scss',
})
export class Perfil implements OnInit {
  usuario: UsuarioCompat | null = null;

  ngOnInit(): void {
    const usuarioSalvo = localStorage.getItem('usuarioLogado');

    if (usuarioSalvo) {
      const usuario = JSON.parse(usuarioSalvo);
      // Converter para formato compatível
      this.usuario = {
        ...usuario,
        nome: usuario.nomeCompleto || usuario.nome || 'Usuário'
      };
    }
  }
}