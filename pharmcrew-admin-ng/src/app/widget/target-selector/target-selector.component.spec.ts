import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TargetSelectorComponent } from './target-selector.component';

describe('TargetSelectorComponent', () => {
  let component: TargetSelectorComponent;
  let fixture: ComponentFixture<TargetSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TargetSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TargetSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
