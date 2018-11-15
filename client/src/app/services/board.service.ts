import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Cell} from '../cell';

import {catchError, map, tap} from 'rxjs/operators';
import {MessageService} from './message.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})

export class BoardService{

  board: Cell[];
  private boardUrl = 'http://localhost:8080/board';

  constructor(private http: HttpClient,
              private messageService: MessageService){}

  getBoard(): Observable<Cell[]> {
    return this.http.get<Cell[]>(this.boardUrl)
        .pipe(
        tap(_ => this.log('fetched board')),
        catchError(this.handleError('getBoard', []))
    );
  }

  private log(message: string) {
      this.messageService.add(`BoardService: ${message}`);
  }


  updateCell(cell: Cell): Observable<Cell>{
      return this.http.put(this.boardUrl,
          cell
          , httpOptions).pipe(
             tap(_ => this.log(`udated cell=${cell.id}`)),
            catchError(this.handleError<any>('updateCell'))
      );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
      return (error: any): Observable<T> => {

          // TODO: send the error to remote logging infrastructure
          console.error(error); // log to console instead

          // TODO: better job of transforming error for user consumption
          this.log(`${operation} failed: ${error.message}`);

          // Let the app keep running by returning an empty result.
          return of(result as T);
      };
  }

    /*TODO: Don't pipe logs*/
    /*makeMove(moveElement: Cell, moveElement2: Cell) {
        forkJoin(
            this.updateCell(moveElement),
            this.updateCell(moveElement2)
        ).pipe(
            map(([first, second]) => {
                // combineLatest returns an array of values, here we map those values to an object
                return { first, second };
            })
        );
    }*/
}
