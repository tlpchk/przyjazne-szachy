import {Component, OnInit} from '@angular/core';
import {Cell} from '../cell';
import {BoardService} from '../board.service';
import {forkJoin} from 'rxjs';

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
      this.makeMove();
  }

  makeMove(): void {
      if (this.move.length === 0) {
        if (this.selectedCell.piece !== null) {
            this.move[0] = this.selectedCell;
        }
      } else {
        this.move[1] = this.selectedCell;
        this.move[1].piece = this.move[0].piece;
        this.move[0].piece = null;
        forkJoin(
          this.boardService.updateCell(this.move[0]),
          this.boardService.updateCell(this.move[1])
        ).subscribe();
        this.move = [];
        this.selectedCell = null;
      }
  }

  getBoard(): void {
    this.boardService.getBoard()
    .subscribe(board => this.board = board);
  }
}
