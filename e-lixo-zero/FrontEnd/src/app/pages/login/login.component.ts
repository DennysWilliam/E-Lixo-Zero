import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  private auth = inject(AuthService);
  private router = inject(Router);

  email = '';
  senha = '';
  erro = '';

  entrar(): void {
  console.log('EMAIL:', this.email);
  console.log('SENHA:', this.senha);

  this.auth.login(this.email.trim(), this.senha.trim()).subscribe({
    next: (valido) => {
      console.log('RESULTADO LOGIN:', valido);

      if (valido) {
        this.erro = '';
        this.router.navigate(['/dashboard']);
      } else {
        this.erro = 'E-mail ou senha inválidos.';
      }
    },
    error: (erro) => {
      console.error('ERRO NA REQUISIÇÃO:', erro);
      this.erro = 'Erro ao conectar com a API.';
    },
  });
}
}