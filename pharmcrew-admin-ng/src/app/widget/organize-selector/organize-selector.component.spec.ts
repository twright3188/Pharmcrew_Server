import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizeSelectorComponent } from './organize-selector.component';

describe('OrganizeSelectorComponent', () => {
  let component: OrganizeSelectorComponent;
  let fixture: ComponentFixture<OrganizeSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrganizeSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrganizeSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
