import { Pipe, PipeTransform } from '@angular/core';
import { GuessResult } from '../classes/guess-result';

@Pipe({
  name: 'guessResult'
})
export class GuessResultPipe implements PipeTransform {

  transform(value: String): GuessResult {
    const exacts = +value.substring(0,3).substring(2);
    const partials = +value.substring(4).substring(2);

    return {exacts: new Array(exacts), partials: new Array(partials)};
  }

}
