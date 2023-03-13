import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Game } from 'src/app/classes/game';
import { Round } from 'src/app/classes/round';

@Component({
  selector: 'app-round-list',
  templateUrl: './round-list.component.html',
  styleUrls: ['./round-list.component.css']
})
export class RoundListComponent {
  @Input() rounds: Round[];
  @Input() currentGame: Game;
}
