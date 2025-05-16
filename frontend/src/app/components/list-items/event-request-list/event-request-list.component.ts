import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { EventItemComponent } from '../../single-items/event-item/event-item.component';
import {EventRequest} from "../../../models/eventRequest";
import {EventRequestItemComponent} from "../../single-items/event-request-item/event-request-item.component";

@Component({
  selector: 'app-event-request-list',
  standalone: true,
  imports: [EventItemComponent, EventRequestItemComponent],
  templateUrl: './event-request-list.component.html',
  styleUrl: './event-request-list.component.css',
})
export class EventRequestListComponent implements OnInit {
  @Input() eventRequest: EventRequest[] = [];
  @Output() refreshHomeEventRequest: EventEmitter<void> = new EventEmitter<void>();
  orderByRecent: boolean = true;

  constructor() {}

  ngOnInit(): void {
    this.sortEventRequest();
  }

  refreshEventRequest() {
    this.refreshHomeEventRequest.emit();
    this.sortEventRequest();
  }
  toggleOrderBy() {
    this.orderByRecent = !this.orderByRecent;
    this.sortEventRequest();
  }
  sortEventRequest() {
    if (this.orderByRecent) {
      this.eventRequest.sort(
        (a, b) => new Date(b.date).getTime() - new Date(a.date).getTime()
      );
    } else {
      this.eventRequest.sort(
        (a, b) => new Date(a.date).getTime() - new Date(b.date).getTime()
      );
    }
  }
}
