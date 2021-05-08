import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskInstanceForUserComponent } from './task-instance-for-user.component';

describe('TaskInstanceForUserComponent', () => {
  let component: TaskInstanceForUserComponent;
  let fixture: ComponentFixture<TaskInstanceForUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TaskInstanceForUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskInstanceForUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
