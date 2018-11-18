import { RestService } from './../service/rest-service/rest.service';
import { Injectable } from '@angular/core';

@Injectable()
export class ProcessadorCepService {

  readonly resource: string = '';

  constructor(private restService: RestService) { }

  upload(session: string, file: File) {
    return this.restService.upload('cepfiles/upload', session, file);
  }

  findHistoricoByToken(token: String) {
    return this.restService.get(`historicos/token/${token}`);
  }
}
