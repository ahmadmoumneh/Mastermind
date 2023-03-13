import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Game } from 'src/app/classes/game';
import { GameService } from 'src/app/services/game.service';


@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']
})
export class GameListComponent implements OnInit {
  @Input() games: Game[];
  @Output() gamesChange = new EventEmitter<Game[]>();

  @Output() currentGame = new EventEmitter<Number>();

  constructor(private gameService: GameService) {}

  ngOnInit() {
    this.gameService.getAllGames().subscribe(games => {
      this.games = games;
      this.gamesChange.emit(games);
    });
  }

  chooseGame(id: number) {
    this.currentGame.emit(id);
  }
}
