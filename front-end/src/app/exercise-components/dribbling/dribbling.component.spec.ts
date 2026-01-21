import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DribblingComponent } from './dribbling.component';

describe('DribblingComponent', () => {
  let component: DribblingComponent;
  let fixture: ComponentFixture<DribblingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DribblingComponent]
    });
    fixture = TestBed.createComponent(DribblingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
