export interface Usuario {
  id: number | string; // Aceitar tanto number quanto string para compatibilidade
  nomeCompleto: string;
  email: string;
  senha?: string; // Senha opcional
  logradouro?: string;
  numero?: string;
  bairro?: string;
  cidade?: string;
  estado?: string;
  telefone?: string;
  tipoUsuario?: string;
  cpf?: string; // CPF opcional
}

// Para compatibilidade com código existente
export interface UsuarioCompat {
  id: string;
  nome: string;
  email: string;
  senha?: string; // Senha opcional por segurança
  logradouro?: string;
  numero?: string;
  bairro?: string;
  cidade?: string;
}