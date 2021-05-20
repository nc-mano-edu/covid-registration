import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {TaskInstanceService} from "./services/taskInstance.service";
import {TaskInstance} from "./models/taskInstance.model";
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {TaskInstanceData} from "./models/taskInstanceData.model";
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import {MomentDateAdapter} from '@angular/material-moment-adapter';
import {DatePipe} from "@angular/common";
import {User} from "../user/user.model";

const DATE_FORMAT_INPUT = 'YYYY-MM-DD';
const DATE_FORMAT_OUTPUT = 'YYYY-MM-dd';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  providers: [
    TaskInstanceService,
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {
      provide: MAT_DATE_FORMATS,
      useValue: {
        parse: {
          dateInput: DATE_FORMAT_INPUT
        },
        display: {
          dateInput: DATE_FORMAT_INPUT,
          monthYearLabel: 'MMM YYYY',
          dateA11yLabel: 'LL',
          monthYearA11yLabel: 'MMMM YYYY',
        }
      }
    },
    {provide: MAT_DATE_LOCALE, useValue: 'ru-RU'}
  ],
  styleUrls: ['./task.component.css'],
})
export class TaskComponent implements OnInit {

  // Dict of names of attributes in DB and their data types
  attrType: Map<string, string> = new Map([
    ['String value', 'string_value'],
    ['Numeric value', 'numeric_value'],
    ['Image value', 'image_value'],
    ['Date value', 'date_value'],
    ['Phone number value', 'string_value'],
    ['Url value', 'string_value']
  ]);

  taskId: string;
  isDoctor: boolean;
  taskInstance: TaskInstance;
  taskInstanceData: TaskInstanceData[];
  user: User;
  img:string;

  taskForm: FormGroup;
  imageBlobs: Map<number, string[]>;
  large: boolean = false;

  constructor(
    private activatedRoute: ActivatedRoute,
    private taskInstanceService: TaskInstanceService,
    private formBuilder: FormBuilder,
    private datePipe: DatePipe) {
  }

  // Convert string to number value
  Number(id: string) {
    return Number(id);
  }

  // Create File object from base64 string
  base64ToFile(data: string, name: string = 'image') {
    const type = data.substring('data:'.length, data.indexOf(';base64'));
    const format = type.substring(type.indexOf('/') + 1, data.length);
    const b64Data = data.substring(data.indexOf('base64,') + 'base64,'.length, data.length);

    const byteCharacters = atob(b64Data);
    const byteNumbers = new Array(byteCharacters.length);
    for (let i = 0; i < byteCharacters.length; i++) {
      byteNumbers[i] = byteCharacters.charCodeAt(i);
    }
    const byteArray = new Uint8Array(byteNumbers);
    const blob = new Blob([byteArray], {type: type});

    return new File([blob], name + '.' + format, {type: type});
  }

  // Show input images previews
  loadBlobs(field_id: string) {
    let files = this.taskForm.controls[field_id].value;
    if (!Array.isArray(files)) {
      files = [files];
    }

    const readFile = file => {
      return new Promise(function (resolve, reject) {
        const reader = new FileReader();

        reader.onload = function () {
          resolve(reader.result);
        };

        reader.onerror = function () {
          reject(reader);
        };

        reader.readAsDataURL(file);
      });
    };

    const readers = [];
    for (let i = 0; i < files.length; i++) {
      readers.push(readFile(files[i]));
    }

    Promise.all(readers).then((values: string[]) => {
      this.imageBlobs.set(Number(field_id), values);
    });
  }

  // Form submitted
  onSubmit() {

    // Validation
    if (!this.taskForm.valid) {
      return Object.keys(this.taskForm.controls).forEach(field => {
        const control = this.taskForm.get(field);
        control.markAsTouched({onlySelf: true});
      });
    }

    // Object creation
    Object.entries(this.taskForm.value).forEach(([key, value]) => {
      const task_data: TaskInstanceData = this.taskInstance.data.find(d => d.id == key);
      switch (this.attrType.get(task_data.attribute.attributeType.name)) {
        case 'numeric_value': {
          task_data.numericValue = value?.toString();
          break;
        }
        case 'image_value': {
          if (this.imageBlobs.has(Number(key)))
            task_data.imageValue = this.imageBlobs.get(Number(key))[0];
          break;
        }
        case 'date_value': {
          if (value) {
            task_data.dateValue = this.datePipe.transform(new Date(value.toString()), DATE_FORMAT_OUTPUT);
          }
          break;
        }
        default: {
          task_data.stringValue = value?.toString();
        }
      }
    });

    // Update record in database
    this.taskInstanceService.update(this.taskId, this.taskInstance).subscribe(() => {
      window.location.reload();
    });
  }

  // Get patient's full name
  getPatientName() {
    const initials = `${this.user.firstName.toUpperCase().charAt(0)}.${this.user.middleName.toUpperCase().charAt(0)}.`;
    return this.user.lastName + ' ' + initials;
  }

  changeImage(){
    this.large = !this.large;
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.taskId = params['task_id'];
      this.isDoctor = (sessionStorage.getItem("isDoctor") == "true");
      this.taskInstanceService.find(this.taskId).subscribe(data => {

        this.taskForm = this.formBuilder.group({});
        this.imageBlobs = new Map();
        this.taskInstance = data;
        this.taskInstanceData = this.taskInstance.data;
        this.user = data.request.user;

        this.taskInstanceData.forEach(field => {
          this.taskForm.addControl(field.id, new FormControl(''));

          if (this.attrType.get(field.attribute.attributeType.name) !== 'date_value') {
            this.taskForm.controls[field.id].setValidators([
              Validators.pattern((field.attribute.attributeType.checkMask))
            ]);
          }

          switch (this.attrType.get(field.attribute.attributeType.name)) {
            case 'numeric_value': {
              this.taskForm.controls[field.id].setValue(field.numericValue);
              break;
            }
            case 'image_value': {
              if (field.imageValue) {
                this.taskForm.controls[field.id].setValue(this.base64ToFile(field.imageValue));
                this.loadBlobs(field.id);
              }
              break;
            }
            case 'date_value': {
              if (field.dateValue) {
                this.taskForm.controls[field.id].setValue(new Date(field.dateValue));
              }
              break;
            }
            default: {
              this.taskForm.controls[field.id].setValue(field.stringValue);
            }
          }
        });
      });
    });
  }
}
