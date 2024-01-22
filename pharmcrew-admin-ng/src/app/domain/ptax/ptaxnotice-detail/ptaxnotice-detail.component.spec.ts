import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PtaxnoticeDetailComponent } from './ptaxnotice-detail.component';

describe('PtaxnoticeDetailComponent', () => {
  let component: PtaxnoticeDetailComponent;
  let fixture: ComponentFixture<PtaxnoticeDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PtaxnoticeDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PtaxnoticeDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
