import { Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { Game } from './classes/game';
import { GameService } from './services/game.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { RoundService } from 'src/app/services/round.service';
import { NgForm } from '@angular/forms';
import { Round } from './classes/round';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  @Input() currentGame: Game;
  @Input() games: Game[];

  rounds: Round[];
  
  @Output() gamesChange = new EventEmitter<Game[]>();

  @ViewChild('gameForm') gameForm: NgForm;
  @ViewChild('error') error: ElementRef;

  constructor(
    private gameService: GameService,
    private roundService: RoundService
  ) {}

  ngOnInit() {
    document.addEventListener('visibilitychange', () => {
      if (this.currentGame) {
        this.roundService.getRoundsByGameId(this.currentGame.id).subscribe(rounds => this.rounds = rounds);
        this.gameService.getAllGames().subscribe(games => this.games = games);
        if (this.games.length === 0) {
          this.currentGame = null;
        }
      }
    });
  }
    
  onGameIdSubmit(gameId: number) {
    this.gameService.getGameById(gameId)
      .pipe(catchError(() => {
        this.gameForm.form.controls['gameId'].setErrors({'not-found': true});
        this.currentGame = null;
        return of();
      }))
      .subscribe(game => {
        this.currentGame = game;
      });
    
    this.roundService.getRoundsByGameId(gameId).subscribe(rounds => {
      this.rounds = rounds;
    });
  }

  newGame() {
    this.gameService.begin().subscribe(game =>{
      this.games.push(game);
      this.gamesChange.emit(this.games);
      this.currentGame = game;
    });

    this.rounds = [];
  }

  hideError() {
    this.error.nativeElement.hidden = true;
    console.log(this.error);
  }
}
