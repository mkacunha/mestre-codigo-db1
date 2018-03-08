import { RouterModule, Routes, Router } from '@angular/router';

import { LoginGuard } from './login/login.guard';
import { LoginComponent } from './login/login.component';
import { ProcessadorCepComponent } from './processador-cep/processador-cep.component';

const appRoutes: Routes = [
    { path: 'login', component: LoginComponent,  },
    { path: 'processador', component: ProcessadorCepComponent, canActivate: [LoginGuard] },
    { path: '**', redirectTo: 'login' }
  ];

export const routing = RouterModule.forRoot(appRoutes);