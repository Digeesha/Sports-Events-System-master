import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

import { CommonModule } from '@angular/common';
import { ApiService } from '../../../service/api.service';
import { MatDialog } from '@angular/material/dialog';
import { EventDetailPopupComponent } from '../../popups/event-detail-popup/event-detail-popup.component';
import {EventRequest} from "../../../models/eventRequest";


@Component({
  selector: 'app-event-request-item',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './event-request-item.component.html',
  styleUrls: ['./event-request-item.component.css'],
  providers: [ApiService]
})
export class EventRequestItemComponent implements OnInit {
  @Input() eventRequest: EventRequest | undefined;
  @Output() refreshEventRequests: EventEmitter<void> = new EventEmitter<void>();
  @Input() isFirst: boolean = false;

  constructor(private apiService: ApiService, private dialog: MatDialog) {}

  ngOnInit(): void {

  }

  deleteEventRequest() {
    if (this.eventRequest?.uuid) {
      this.apiService.deleteEvent(this.eventRequest?.uuid).subscribe((result) => {
        if (result) {
          this.refreshEventRequests.emit(); //Emit void event to list for refreshing events.
        }
      });
    }
  }


  openDetailModal(): void {
    const dialogRef = this.dialog.open(EventDetailPopupComponent, {
      width: '500px',
      data: this.eventRequest, // Pass event to modal
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }
}
