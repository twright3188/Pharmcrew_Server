import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EduApprovalComponent } from './edu-approval.component';

describe('EduApprovalComponent', () => {
  let component: EduApprovalComponent;
  let fixture: ComponentFixture<EduApprovalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EduApprovalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EduApprovalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
