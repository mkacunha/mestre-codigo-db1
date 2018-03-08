import { Component, OnInit } from '@angular/core';
import { RestService } from './service/rest-service/rest.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private restService: RestService) { }

  ngOnInit() {
  
  }
}
