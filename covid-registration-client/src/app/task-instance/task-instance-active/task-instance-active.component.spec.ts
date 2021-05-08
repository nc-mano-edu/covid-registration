import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskInstanceActiveComponent } from './task-instance-active.component';

describe('TaskInstanceActiveComponent', () => {
  let component: TaskInstanceActiveComponent;
  let fixture: ComponentFixture<TaskInstanceActiveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TaskInstanceActiveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskInstanceActiveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
