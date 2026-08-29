import { Component, computed, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { toSignal } from '@angular/core/rxjs-interop';

import { ColetasService } from '../../services/coletas';
import { Usuario, UsuarioCompat } from '../../models/usuario.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss',
})
export class Dashboard {
  private coletasService = inject(ColetasService);

  usuario: UsuarioCompat | null = (() => {
    const usuarioSalvo = localStorage.getItem('usuarioLogado');
    if (!usuarioSalvo) return null;
    
    const usuario = JSON.parse(usuarioSalvo);
    // Converter nomeCompleto para nome para compatibilidade
    return {
      ...usuario,
      nome: usuario.nomeCompleto || usuario.nome || 'Usuário'
    };
  })();

  coletas = toSignal(
    this.coletasService.listar(),
    { initialValue: [] }
  );

  totalAgendadas = computed(
    () => this.coletas().filter(c => c.status === 'Agendada').length
  );

  totalAndamento = computed(
    () => this.coletas().filter(c => c.status === 'Em Andamento').length
  );

  totalConcluidas = computed(
    () => this.coletas().filter(c => c.status === 'Concluída').length
  );

  totalResiduos = computed(
    () => this.coletas().reduce(
      (total, coleta) => total + Number(coleta.quantidade),
      0
    )
  );
}