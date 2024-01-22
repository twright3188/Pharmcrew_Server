import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PtaxnoticeListComponent } from './ptaxnotice-list.component';

describe('PtaxnoticeListComponent', () => {
  let component: PtaxnoticeListComponent;
  let fixture: ComponentFixture<PtaxnoticeListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PtaxnoticeListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PtaxnoticeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
