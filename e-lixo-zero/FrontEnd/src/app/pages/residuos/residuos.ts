import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { toSignal } from '@angular/core/rxjs-interop';

import { Residuo } from '../../models/residuo.model';
import { ResiduosService } from '../../services/residuos';

@Component({
  selector: 'app-residuos',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './residuos.html',
  styleUrl: './residuos.scss',
})
export class Residuos {
  private residuosService = inject(ResiduosService);

  residuos = toSignal<Residuo[]>(
    this.residuosService.listar(),
  );

  obterIcone(nome: string): string {
    const icones: Record<string, string> = {
      Celulares: '📱',
      Computadores: '💻',
      Monitores: '🖥️',
      'Pilhas e Baterias': '🔋',
      'Cabos e Carregadores': '🔌',
      Impressoras: '🖨️',
      Teclados: '⌨️',
      Mouse: '🖱️',
    };

    return icones[nome] || '♻️';
  }
}