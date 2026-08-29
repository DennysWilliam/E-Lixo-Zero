import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PontosService } from '../../services/pontos';
import { PontoColeta } from '../../models/ponto-coleta.model';

@Component({
  selector: 'app-pontos-coleta',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pontos-coleta.html',
  styleUrl: './pontos-coleta.scss',
})
export class PontosColetaComponent  {
  private pontosService = inject(PontosService);

  pontos$ = this.pontosService.listar();
}