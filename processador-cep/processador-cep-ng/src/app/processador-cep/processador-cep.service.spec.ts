import { TestBed, inject } from '@angular/core/testing';

import { ProcessadorCepService } from './processador-cep.service';

describe('ProcessadorCepService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ProcessadorCepService]
    });
  });

  it('should be created', inject([ProcessadorCepService], (service: ProcessadorCepService) => {
    expect(service).toBeTruthy();
  }));
});
