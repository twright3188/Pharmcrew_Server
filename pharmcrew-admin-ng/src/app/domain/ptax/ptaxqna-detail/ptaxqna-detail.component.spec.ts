import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PtaxqnaDetailComponent } from './ptaxqna-detail.component';

describe('PtaxqnaDetailComponent', () => {
  let component: PtaxqnaDetailComponent;
  let fixture: ComponentFixture<PtaxqnaDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PtaxqnaDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PtaxqnaDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
