import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TdHistoricoStatusComponent } from './td-historico-status.component';

describe('TdHistoricoStatusComponent', () => {
  let component: TdHistoricoStatusComponent;
  let fixture: ComponentFixture<TdHistoricoStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TdHistoricoStatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TdHistoricoStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
