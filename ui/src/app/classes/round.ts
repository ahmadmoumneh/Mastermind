import { Time } from "@angular/common";
import { Game } from "./game";

export class Round {
  roundId: number;
  guess: string;
  guessTime: Time;
  guessResult: string;
  game: Game;
}
