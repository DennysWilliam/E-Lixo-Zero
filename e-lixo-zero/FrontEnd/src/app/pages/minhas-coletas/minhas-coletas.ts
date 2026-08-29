import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Coleta } from '../../models/coleta.model';
import { ColetasService } from '../../services/coletas';

@Component({
  selector: 'app-minhas-coletas',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './minhas-coletas.html',
  styleUrl: './minhas-coletas.scss',
})
export class MinhasColetas  {
  private coletasService = inject(ColetasService);

  coletas: Coleta[] = [];
  coletas$ = this.coletasService.listar()


}