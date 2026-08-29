import { Routes } from '@angular/router';

import { HomeComponent } from './pages/home/home';
import { LoginComponent } from './pages/login/login.component';
import { CadastroComponent } from './pages/cadastro/cadastro.component';

import { Dashboard } from './pages/dashboard/dashboard';
import { PontosColetaComponent } from './pages/pontos-coleta/pontos-coleta';
import { SolicitarColeta } from './pages/solicitar-coleta/solicitar-coleta';
import { MinhasColetas } from './pages/minhas-coletas/minhas-coletas';
import { Residuos } from './pages/residuos/residuos';
import { Notificacoes } from './pages/notificacoes/notificacoes';
import { Perfil } from './pages/perfil/perfil';
import { MainLayout } from './layouts/main-layout/main-layout';
import { Sobre } from './pages/sobre/sobre';
import { ComoFunciona } from './pages/como-funciona/como-funciona';
import { authGuard } from './core/guards/auth-guard';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'cadastro', component: CadastroComponent },

  
    {
  path: '',
  component: MainLayout,
  children: [
    { path: 'dashboard', component: Dashboard, canActivate: [authGuard] },
    { path: 'pontos-coleta', component: PontosColetaComponent },
    { path: 'solicitar-coleta', component: SolicitarColeta, canActivate: [authGuard] },
    { path: 'minhas-coletas', component: MinhasColetas, canActivate: [authGuard] },
    { path: 'residuos', component: Residuos},
    { path: 'notificacoes', component: Notificacoes, canActivate: [authGuard] },
    { path: 'perfil', component: Perfil, canActivate: [authGuard] },
    { path: 'sobre', component: Sobre },
{ path: 'como-funciona', component: ComoFunciona },
  ],
},

  { path: '**', redirectTo: '' },
];