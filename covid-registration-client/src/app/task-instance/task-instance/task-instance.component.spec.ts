import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskInstanceComponent } from './task-instance.component';

describe('TaskInstanceComponent', () => {
  let component: TaskInstanceComponent;
  let fixture: ComponentFixture<TaskInstanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TaskInstanceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskInstanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
