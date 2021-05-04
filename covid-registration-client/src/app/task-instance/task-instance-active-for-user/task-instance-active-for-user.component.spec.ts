import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskInstanceActiveForUserComponent } from './task-instance-active-for-user.component';

describe('TaskInstanceActiveForUserComponent', () => {
  let component: TaskInstanceActiveForUserComponent;
  let fixture: ComponentFixture<TaskInstanceActiveForUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TaskInstanceActiveForUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskInstanceActiveForUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
