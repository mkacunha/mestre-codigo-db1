import { Observable } from 'rxjs/Observable';
import { ProcessadorCepService } from './../processador-cep.service';
import { Historico } from './../../model/historico/historico';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'td-historico-status',
  templateUrl: './td-historico-status.component.html',
  styleUrls: ['./td-historico-status.component.css']
})
export class TdHistoricoStatusComponent implements OnInit {

  @Input() historico: Historico;
  @Output() historicoChange: EventEmitter<Historico> = new EventEmitter<Historico>();

  constructor(private service: ProcessadorCepService) { }

  ngOnInit() {
    this.initConsultaHistorico();
  }

  private initConsultaHistorico() {
    if (this.historico.token && this.historico.isProcessando()) {
      let isConsultarHistorico: boolean = true;
      let timerConsultaHistorico = Observable.timer(100, 5000);
      timerConsultaHistorico
        .takeWhile(() => isConsultarHistorico)
        .subscribe(() => {
          console.log('consultando')
          this.service.findHistoricoByToken(this.historico.token)
            .takeWhile(() => isConsultarHistorico)
            .map(result => new Historico(result))
            .subscribe(historicoResult => {
              if (!historicoResult.isProcessando()) {
                this.historico = historicoResult;
                this.historicoChange.emit(historicoResult);
                isConsultarHistorico = false;
              }
            });
        });
    }
  }

}
