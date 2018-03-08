import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { RestService } from './rest-service/rest.service';

@NgModule({
  imports: [
    CommonModule,
    HttpModule
  ],
  declarations: [],
  providers :[
    RestService
  ]
})
export class ServiceModule { }
