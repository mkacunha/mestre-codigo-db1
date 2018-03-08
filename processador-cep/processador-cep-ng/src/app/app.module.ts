import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ServiceModule } from './service/service.module';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { routing } from './app.routes';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/Rx';

import { ProcessadorCepComponent } from './processador-cep/processador-cep.component';

import { ProcessadorCepService } from './processador-cep/processador-cep.service';
import { TdHistoricoStatusComponent } from './processador-cep/td-historico-status/td-historico-status.component';

import { LoginComponent } from './login/login.component';
import { LoginGuard } from './login/login.guard';
import { LoginStorage } from './login/login.storage'

@NgModule({
  declarations: [
    AppComponent,
    ProcessadorCepComponent,
    TdHistoricoStatusComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ServiceModule,
    routing
  ],
  providers: [
    LoginGuard,
    LoginStorage,
    ProcessadorCepService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
