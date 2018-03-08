import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessadorCepComponent } from './processador-cep.component';

describe('ProcessadorCepComponent', () => {
  let component: ProcessadorCepComponent;
  let fixture: ComponentFixture<ProcessadorCepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProcessadorCepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessadorCepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
