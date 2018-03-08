import { Component, OnInit, Input, ViewChild, ElementRef } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import "rxjs/add/operator/takeWhile";


import { ProcessadorCepService } from './processador-cep.service';

import { Historico } from './../model/historico/historico';
import { HistoricoStatus } from './../model/historico/historico-status';

@Component({
  selector: 'processador-cep',
  templateUrl: './processador-cep.component.html',
  styleUrls: ['./processador-cep.component.css']
})
export class ProcessadorCepComponent implements OnInit {

  @ViewChild('inputfile') inputFile: ElementRef;

  private file: File;
  private fileName: string;
  private historicoProcessado: Historico = new Historico({});
  private historicos: Historico[] = [];

  private enviando: boolean = false;
  private error: String = '';

  constructor(private service: ProcessadorCepService) { }

  ngOnInit() {

  }

  private openFile() {
    this.inputFile.nativeElement.click();
  }

  private selectFile(event) {
    this.initVariables();
    if (event.target.files && event.target.files.length > 0) {
      this.file = event.target.files[0];
      this.fileName = this.file.name;
    }
  }

  private uploadArquivo() {
    if (!this.file) {
      this.error = "Informe um arquivo para executar o UPLOAD.";
      return;
    }

    this.enviando = true;
    this.service.upload(this.file)
      .map(result => new Historico(result))
      .subscribe(result => this.handleResultUpload(result), error => this.handleErrorUpload(error), () => this.enviando = false);
  }

  private handleResultUpload(historico: Historico) {
    this.historicos.push(historico);
  }

  private handleErrorUpload(error) {
    this.enviando = false;
    this.error = error.text();
  }

  private initVariables() {
    this.error = "";
  }

  private onHistoricoChange(historico: Historico) {
    let index = this.historicos.findIndex(his => his.token === historico.token);
    if (index >= 0) {
      this.historicos[index] = historico;
    }
  }
}
