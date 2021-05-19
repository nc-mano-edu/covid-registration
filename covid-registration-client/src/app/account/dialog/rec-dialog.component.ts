import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'confirmation-dialog',
  templateUrl: 'rec-dialog.component.html',
  styleUrls: ['../account.component.css', '../../task/task.component.css']
})
export class RecommendationDialog {

  message = 'Enter text';
  confirmButtonText = 'Submit';
  cancelButtonText = 'Cancel';
  inputLabel = 'Text';

  inputText = '';

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: any,
    private dialogRef: MatDialogRef<RecommendationDialog>) {
    if (data) {
      this.message = data.message || this.message;
      this.inputLabel = data.label || this.inputLabel;
      if (data.buttonText) {
        this.confirmButtonText = data.buttonText.ok || this.confirmButtonText;
        this.cancelButtonText = data.buttonText.cancel || this.cancelButtonText;
      }
    }
  }

  onConfirmClick(): void {
    this.dialogRef.close(this.inputText);
  }

  onCloseClick(): void {
    this.dialogRef.close(undefined);
  }

}
