import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PtaxqnaListComponent } from './ptaxqna-list.component';

describe('PtaxqnaListComponent', () => {
  let component: PtaxqnaListComponent;
  let fixture: ComponentFixture<PtaxqnaListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PtaxqnaListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PtaxqnaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
