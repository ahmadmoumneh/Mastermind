import { Component, ElementRef, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Game } from 'src/app/classes/game';
import { Round } from 'src/app/classes/round';
import { GameService } from 'src/app/services/game.service';
import { RoundService } from 'src/app/services/round.service';

@Component({
  selector: 'app-guess',
  templateUrl: './guess.component.html',
  styleUrls: ['./guess.component.css']
})
export class GuessComponent {
  @Input() currentGame: Game;
  @Output() currentGameChange: EventEmitter<Game> = new EventEmitter<Game>();
  
  @Input() rounds: Round[];
  @Output() roundsChange: EventEmitter<Round[]> = new EventEmitter<Round[]>();

  @Input() games: Game[];
  @Output() gamesChange: EventEmitter<Game[]> = new EventEmitter<Game[]>();

  @ViewChild('guessForm') guessForm: NgForm;
  @ViewChild('error') error: ElementRef;

  constructor(
    private gameService: GameService,
    private roundService: RoundService
  ) {}

  onGuessSubmit() {
    let guess = this.guessForm.form.value.guess;

    let redundancy: boolean = guess.split("").some((v: string, i: number, a: string[]) => 
      a.lastIndexOf(v) !== i
    );

    if (redundancy) {
      this.guessForm.form.controls['guess'].setErrors({'redundancy': true});
      return;
    }

    if (guess.length !== 4) {
      this.guessForm.form.controls['guess'].setErrors({'invalid-number': true});
      return;
    }

    else {
      const round = new Round();

      round.guess = guess;
      round.game = this.currentGame;

      this.roundService.guess(round).subscribe(round => {
        this.rounds.push(round);
        this.roundsChange.emit(this.rounds);

        if (round.guessResult === 'e:4:p:0') {
          this.gameService.getGameById(round.game.id).subscribe(game => {
            this.currentGame = game;
            this.currentGameChange.emit(game);
          });

          this.gameService.getAllGames().subscribe(games => {
            this.games = games;
            this.gamesChange.emit(games);
          });
        }
      });
    }
  }

  hideError() {
    this.error.nativeElement.hidden = true;
  }
}
