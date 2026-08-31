import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { toSignal } from '@angular/core/rxjs-interop';

import { Residuo } from '../../models/residuo.model';
import { ColetasService } from '../../services/coletas';
import { ResiduosService } from '../../services/residuos';

@Component({
  selector: 'app-solicitar-coleta',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './solicitar-coleta.html',
  styleUrl: './solicitar-coleta.scss',
})
export class SolicitarColeta {
  private fb = inject(FormBuilder);
  private coletasService = inject(ColetasService);
  private residuosService = inject(ResiduosService);

  residuos = toSignal(this.residuosService.listar(), {
    initialValue: [] as Residuo[],
  });

  mensagem = '';
  erro = '';

  formulario = this.fb.group({
    residuo: ['', Validators.required],
    quantidade: [1, Validators.required],
    logradouro: ['', Validators.required],
    numero: ['', Validators.required],
    bairro: ['', Validators.required],
    cidade: ['Santa Rita do Sapucaí', Validators.required],
    data: ['', Validators.required],
    periodo: ['Manhã', Validators.required],
  });

  salvar(): void {
    if (this.formulario.invalid) {
      this.formulario.markAllAsTouched();
      return;
    }

    this.mensagem = '';
    this.erro = '';

    const coleta = {
      ...this.formulario.value,
      status: 'Agendada',
    };

    this.coletasService.criar(coleta as any).subscribe({
      next: (response) => {
        console.log('Coleta criada com sucesso:', response);
        this.mensagem = 'Coleta agendada com sucesso!';

        this.formulario.reset({
          quantidade: 1,
          cidade: 'Santa Rita do Sapucaí',
          periodo: 'Manhã',
        });
      },
      error: (error) => {
        console.error('Erro ao agendar coleta:', error);
        this.erro = 'Erro ao agendar coleta. Tente novamente.';
      },
    });
  }
}
