import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Notificacao } from '../../models/notificacao.model';
import { NotificacoesService } from '../../services/notificacoes';

@Component({
  selector: 'app-notificacoes',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './notificacoes.html',
  styleUrl: './notificacoes.scss',
})
export class Notificacoes {
  private notificacoesService = inject(NotificacoesService);

  notificacoes$ = this.notificacoesService.listar()

}