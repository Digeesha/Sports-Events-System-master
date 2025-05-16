import { Component, Inject, OnInit, Input } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogRef,
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import { ApiService } from '../../../../service/api.service';
import { HttpClientModule } from '@angular/common/http';
import { MatSelectModule } from '@angular/material/select';
import { Team } from '../../../../models/team';
import { CommonModule } from '@angular/common';
import { throwError } from 'rxjs';
import { ErrorMessageDialogComponent } from '../../../errors/error-message-dialog/error-message-dialog.component';

@Component({
  selector: 'app-popup',
  standalone: true,
  imports: [
    MatDialogModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatSelectModule,
    CommonModule,
  ],
  templateUrl: './popup-event-request.component.html',
  styleUrl: './popup-event-request.component.css',
  providers: [ApiService],
})
export class PopupEventRequestComponent implements OnInit {
  @Input() inputData: any;
  teams: Team[] = [];
  selectedHomeTeam: number | undefined;
  selectedAwayTeam: number | undefined;

  actualDate: string = new Date().toISOString().slice(0, 10);

  errorMessage: string | undefined;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private ref: MatDialogRef<PopupEventRequestComponent>,
    private builder: FormBuilder,
    private apiService: ApiService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.apiService.getTeam().subscribe((teams: Team[]) => {
      this.teams = teams;
    });
    this.inputData = this.data;



  }

  closePopup(value: boolean) {
    this.ref.close(value);
  }

  myForm = this.builder.group({
    date: this.builder.control(this.actualDate),
    time: this.builder.control('12:30'),
    homeTeam: this.builder.control(''),
    awayTeam: this.builder.control(''),
    spectators: this.builder.control(''),
    requesterName:this.builder.control('')
  });

  saveEventRequest() {
    this.apiService.saveEventRequest(this.myForm.value).subscribe({
      next: (res) => {
        this.closePopup(true);
      },
      error: (err) => {
        // If error is handled in API, sent personalized message.
        if (typeof err.error === 'string') {
          this.errorMessage = err.error;
        } else {
          // Sent default error message + details
          this.errorMessage = 'An error ocurred: ' + err.error.detail;
        }
        this.dialog.open(ErrorMessageDialogComponent, {
          data: {
            message: this.errorMessage,
          },
        });
      },
    });
  }
}
