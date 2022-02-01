import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SnackbarinputComponent } from './snackbarinput.component';

describe('SnackbarinputComponent', () => {
  let component: SnackbarinputComponent;
  let fixture: ComponentFixture<SnackbarinputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SnackbarinputComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SnackbarinputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
