import { Time } from '@angular/common';
import { Stadium } from './stadium';
import { Team } from './team';

export interface EventRequest {
  uuid?: string;
  requesterName?:string;
  stadium?: Stadium;
  date: Date;
  time: Time;
  homeTeam: Team;
  awayTeam: Team;
  spectators: number;

}
