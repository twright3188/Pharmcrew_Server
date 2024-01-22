import { TestBed } from '@angular/core/testing';

import { PtaxService } from './ptax.service';

describe('PtaxService', () => {
  let service: PtaxService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PtaxService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
