import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Game } from '../classes/game';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private gameUrl: string;

  constructor(private http: HttpClient) { 
    this.gameUrl = 'http://localhost:8080/api/mastermind/game';
  }

  begin(): Observable<Game> {
    return this.http.post<Game>(this.gameUrl, {})
  }

  getAllGames(): Observable<Game[]> {
    return this.http.get<Game[]>(this.gameUrl + 's');
  }

  getGameById(id: number): Observable<Game> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("gameId", id);

    return this.http.get<Game>(this.gameUrl, {params: queryParams});
  }
}
