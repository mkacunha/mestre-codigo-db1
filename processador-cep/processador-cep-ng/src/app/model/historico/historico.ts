import { HistoricoStatus } from './historico-status';

export class Historico {
    private _token: String;
    private _arquivo: String;
    private _status: HistoricoStatus;
    private _data: Date;
    private _quantidadeResgitrosNovos: Number;
    private _quantidadeRegistrosAlterados: Number;
    private _quantidadeRegistrosComErros: Number;
    private _quantidadeRegistrosProcessados: Number;

    constructor(obj) {
        this._token = obj.token;
        this._arquivo = obj.arquivo;
        this._status = HistoricoStatus[obj.status as string];
        this._data = obj.data;
        this._quantidadeResgitrosNovos = obj.quantidadeResgitrosNovos;
        this._quantidadeRegistrosAlterados = obj.quantidadeRegistrosAlterados;
        this._quantidadeRegistrosComErros = obj.quantidadeRegistrosComErros;
        this._quantidadeRegistrosProcessados = obj.quantidadeRegistrosProcessados;
    }

    public isStatus(status: HistoricoStatus): boolean {
        return this._status === status;
    }

    public isProcessando(): boolean {
        return this.isStatus(HistoricoStatus.PROCESSANDO);
    }
    set token(token: String) {
        this._token = token;
    }

    get token(): String {
        return this._token;
    }

    set arquivo(arquivo: String) {
        this._arquivo = arquivo;
    }

    get arquivo(): String {
        return this._arquivo;
    }

    set status(status: HistoricoStatus) {
        this._status = status;
    }

    get status(): HistoricoStatus {
        return this._status;
    }

    set data(date: Date) {
        this._data = date;
    }

    get data(): Date {
        return this._data;
    }

    set quantidadeResgitrosNovos(quantidadeResgitrosNovos: Number) {
        this._quantidadeResgitrosNovos = quantidadeResgitrosNovos;
    }

    get quantidadeResgitrosNovos(): Number {
        return this._quantidadeResgitrosNovos;
    }

    set quantidadeRegistrosAlterados(quantidadeRegistrosAlterados: Number) {
        this._quantidadeRegistrosAlterados = quantidadeRegistrosAlterados;
    }

    get quantidadeRegistrosAlterados(): Number {
        return this._quantidadeRegistrosAlterados;
    }

    set quantidadeRegistrosComErros(quantidadeRegistrosComErros: Number) {
        this._quantidadeRegistrosComErros = quantidadeRegistrosComErros;
    }

    get quantidadeRegistrosComErros(): Number {
        return this._quantidadeRegistrosComErros;
    }

    set quantidadeRegistrosProcessados(quantidadeRegistrosProcessados: Number) {
        this._quantidadeRegistrosProcessados = quantidadeRegistrosProcessados;
    }

    get quantidadeRegistrosProcessados(): Number {
        return this._quantidadeRegistrosProcessados;
    }
}