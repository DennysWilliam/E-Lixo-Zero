import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { PontosService } from '../../services/pontos';
import { PontoColeta } from '../../models/ponto-coleta.model';

@Component({
  selector: 'app-pontos-coleta',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './pontos-coleta.html',
  styleUrl: './pontos-coleta.scss',
})
export class PontosColetaComponent  {
  private pontosService = inject(PontosService);

  termo = '';
  pontos$ = this.pontosService.listar();

  buscar(): void {
    const cidade = this.termo.trim();
    if (!cidade) {
      this.pontos$ = this.pontosService.listar();
    } else {
      this.pontos$ = this.pontosService.buscarPorCidade(cidade);
    }
  }
}
