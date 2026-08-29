import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

import { AuthService } from '../../services/auth';
import { Usuario, UsuarioCompat } from '../../models/usuario.model';

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [FormsModule, RouterLink, CommonModule],
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.scss',
})
export class CadastroComponent {
  private auth = inject(AuthService);
  private router = inject(Router);

  nome = '';
  email = '';
  senha = '';
  confirmarSenha = '';

  logradouro = '';
  numero = '';
  bairro = '';
  cidade = 'Santa Rita do Sapucaí';

  erro = '';

  cadastrar(): void {
    if (this.senha !== this.confirmarSenha) {
      this.erro = 'As senhas não conferem.';
      return;
    }

    this.auth
      .cadastrar({
        nomeCompleto: this.nome,
        email: this.email,
        senha: this.senha,
        logradouro: this.logradouro,
        numero: this.numero,
        bairro: this.bairro,
        cidade: this.cidade,
        estado: 'MG',
        telefone: '',
        tipoUsuario: 'CIDADAO',
        cpf: '' // CPF opcional por enquanto
      })
      .subscribe({
        next: (usuario) => {
          // Converter para formato compatível
          const usuarioCompat: UsuarioCompat = {
            id: String(usuario.id),
            nome: usuario.nomeCompleto,
            email: usuario.email,
            senha: usuario.senha,
            logradouro: usuario.logradouro,
            numero: usuario.numero,
            bairro: usuario.bairro,
            cidade: usuario.cidade
          };
          localStorage.setItem('usuarioLogado', JSON.stringify(usuarioCompat));
          this.router.navigate(['/dashboard']);
        },
        error: () => {
          this.erro = 'Erro ao cadastrar usuário.';
        },
      });
  }
}