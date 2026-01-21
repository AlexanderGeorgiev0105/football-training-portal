import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FkicksComponent } from './fkicks.component';

describe('FkicksComponent', () => {
  let component: FkicksComponent;
  let fixture: ComponentFixture<FkicksComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FkicksComponent]
    });
    fixture = TestBed.createComponent(FkicksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
