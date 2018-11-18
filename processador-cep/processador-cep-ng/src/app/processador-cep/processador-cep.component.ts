import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import 'rxjs/add/operator/takeWhile';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { Historico } from './../model/historico/historico';
import { ProcessadorCepService } from './processador-cep.service';



@Component({
  selector: 'app-processador-cep',
  templateUrl: './processador-cep.component.html',
  styleUrls: ['./processador-cep.component.css']
})
export class ProcessadorCepComponent implements OnInit {

  @ViewChild('inputfile') inputFile: ElementRef;

  private file: File;
  private session: string;

  fileName: string;
  historicoProcessado: Historico = new Historico({});
  historicos: Historico[] = [];

  enviando = false;
  error: String = '';

  name: string;
  message: string;

  socket: any;
  stompClient: any;

  constructor(private service: ProcessadorCepService) { }

  ngOnInit() {
    this.session = Math.random().toString().substring(2) + new Date().getMilliseconds().toString();
    this.initSocket();
    this.connectSocket();
  }

  private initSocket() {
  }

  private connectSocket() {
    const socket = new SockJS('/ws');
    this.stompClient = Stomp.over(socket);
    this.stompClient.reconnect_delay = 5000;
    this.stompClient.connect({}, () => this.onConnected(), (error, e) => this.onError(error));
  }

  private onConnected() {
    this.error = '';
    this.stompClient.subscribe(`/user/${this.session}/queue/reply`, message => this.onReceiveMessage(message));
  }

  private onError(error) {
    this.error = error;
    if (!this.stompClient.connected) {
      setInterval(() => this.connectSocket(), 5000);
    }
  }

  private onReceiveMessage(message) {
    const historico = new Historico(JSON.parse(message.body));
    this.onHistoricoChange(historico);
  }

  openFile() {
    this.inputFile.nativeElement.click();
  }

  selectFile(event) {
    this.initVariables();
    if (event.target.files && event.target.files.length > 0) {
      this.file = event.target.files[0];
      this.fileName = this.file.name;
    }
  }

  uploadArquivo() {
    if (!this.file) {
      this.error = 'Informe um arquivo para executar o UPLOAD.';
      return;
    }

    this.error = '';
    this.enviando = true;
    this.service.upload(this.session, this.file)
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
    this.error = '';
  }

  private onHistoricoChange(historico: Historico) {
    const index = this.historicos.findIndex(his => his.token === historico.token);
    if (index >= 0) {
      this.historicos[index] = historico;
    }
  }
}
