import {Component, OnInit} from '@angular/core';
import {BOARD} from '../mock-board';
import {Cell, Color} from '../cell';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss']
})
export class BoardComponent implements OnInit {
  constructor() { }

  board = BOARD;
  selectedCell: Cell;

  ngOnInit() {
  }

    onSelect(cell: Cell) {
        this.selectedCell = cell;
    }
}
