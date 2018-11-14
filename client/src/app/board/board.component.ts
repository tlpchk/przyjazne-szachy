import {Component, OnInit} from '@angular/core';
import {Cell} from '../cell';
import {BoardService} from '../board.service';
import {forkJoin} from 'rxjs';
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss']
})
export class BoardComponent implements OnInit {
  selectedCell: Cell;
  move: Cell[];
  board: Cell[];

  constructor(private boardService: BoardService) { }

  ngOnInit() {
    this.getBoard();
    this.move = [];
   }

  onSelect(cell: Cell) {
      this.selectedCell = cell;
      console.log(this.move.length);
      this.makeMove();
  }

  makeMove(): void {
      if (this.move.length === 0) {
        this.move[0] = this.selectedCell;

      } else {
        this.move[1] = this.selectedCell;
        this.move[1].piece = this.move[0].piece;
        this.move[0].piece = null;

        /*TODO: uptade only changed cells*/
        this.boardService.updateCell(this.move[0]).subscribe(cell => this.updateCellLocal(cell));
        this.boardService.updateCell(this.move[1]).subscribe(cell => this.updateCellLocal(cell));

        /*this.boardService.makeMove(this.move[0], this.move[1]);
        let changes$;
        changes$ = forkJoin(
          this.boardService.updateCell(this.move[0]),
          this.boardService.updateCell(this.move[1])
        ).pipe(
          map(([first, second]) => {this.getBoard();})
        );*/
          this.move = [];
          this.selectedCell = null;
      }
  }

  updateCellLocal(cell: Cell) {
      this.board[cell.id - 1] = cell;
  }

  getBoard(): void {
    this.boardService.getBoard()
    .subscribe(board => this.board = board);
  }
}
