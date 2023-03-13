import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { GuessResultPipe } from './pipes/guess-result.pipe';
import { GameListComponent } from './components/game-list/game-list.component';
import { RoundListComponent } from './components/round-list/round-list.component';
import { GuessComponent } from './components/guess/guess.component';

@NgModule({
  declarations: [
    AppComponent,
    GuessResultPipe,
    GameListComponent,
    RoundListComponent,
    GuessComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
