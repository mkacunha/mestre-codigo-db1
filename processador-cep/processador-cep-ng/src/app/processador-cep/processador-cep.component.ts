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
  fileName: string;
  historicoProcessado: Historico = new Historico({});
  historicos: Historico[] = [];

  enviando = false;
  error: String = '';

  name: string;
  message: string;

  stompClient: any;

  constructor(private service: ProcessadorCepService) { }

  ngOnInit() {
  }

  connectSocket() {
    const socket = new SockJS('/ws');
    this.stompClient = Stomp.over(socket);
    console.log(this.name);
    this.stompClient.connect({}, () => this.onConnected(), error => this.onError(error));
  }

  private onConnected() {
    // Subscribe to the Public Topic
    this.stompClient.subscribe('/topic/public', message => this.receiveMessage(message));
    this.stompClient.subscribe('/topic/joao', message => this.receivePrivateMessage(message));

    // Tell your username to the server
    this.stompClient.send("/app/chat.addUser",
      {},
      JSON.stringify({ sender: this.name, type: 'JOIN' })
    )
  }

  enviar() {
    this.stompClient.send("/app/chat.sendMessage",
      {},
      JSON.stringify({ sender: this.name, type: 'CHAT', content: this.message })
    )
  }

  private onError(error) {
    console.log(error);
  }


  private receivePrivateMessage(message) {
    console.log('Mensagem privada')
    console.log(message);
  }

  private receiveMessage(message) {
    console.log('Mensagem public');
    console.log(message);
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
    this.error = '';
  }

  onHistoricoChange(historico: Historico) {
    const index = this.historicos.findIndex(his => his.token === historico.token);
    if (index >= 0) {
      this.historicos[index] = historico;
    }
  }
}
