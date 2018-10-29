import {Injectable, OnInit} from '@angular/core';
import {BOARD} from './mock-board';
import {Observable, of} from 'rxjs';
import {Cell} from './cell';

@Injectable({
  providedIn: 'root'
})

export class BoardService{

  board: Cell[];

  getBoard(): Observable<Cell[]> {
    return of(BOARD);
  }

    makeMove(srcCell: Cell, dscCell: Cell): void {
      console.log('User want to make move from %d to %d', srcCell.id , dscCell.id);
    }
}
