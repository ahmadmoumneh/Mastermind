import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Round } from '../classes/round';

@Injectable({
  providedIn: 'root'
})
export class RoundService {

  private roundUrl: string;

  constructor(private http: HttpClient) { 
    this.roundUrl = 'http://localhost:8080/api/mastermind/round';
  }

  getRoundsByGameId(id: number): Observable<Round[]> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("gameId", id);

    return this.http.get<Round[]>(this.roundUrl + 's', {params: queryParams});
  }

  guess(round: Round): Observable<Round> {
    return this.http.post<Round>(this.roundUrl, round);
  }
}
