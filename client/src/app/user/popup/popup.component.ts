import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Router} from '@angular/router';
import {BoardService} from '../../_services/board.service';
import {PieceType} from '../../_models/piece';

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.scss']
})
export class PopupComponent implements OnInit {

  constructor(private router: Router,
              private boardService: BoardService) { }
  popupType = Object.freeze({
    PROM: 'promotion',
    MSG: 'message'
  });

  @Output() public promotionEvent = new EventEmitter();

  public isDisplayed;
  public message;

  type: string;
  routerLink: string;

  ngOnInit() {
    this.type = this.popupType.PROM;
  }

  show(){
    if (!this.isDisplayed) {
      this.isDisplayed = true;
    }
  }

  showMessage(message) {
    this.message = message;
    this.type = this.popupType.MSG;
    this.show();
  }

  showPromotion(){
    this.message = 'Promocja';
    this.type = this.popupType.PROM;
    this.show();
  }


  hide() {
    this.message = '';
    if (this.isDisplayed) {
      this.isDisplayed = false;
    }
    if (this.routerLink != null) {
      this.router.navigateByUrl(this.routerLink);
      this.routerLink = null;
    }
  }

  promotion(type: PieceType) {
    console.log('EMIT PROMOTION');
    this.promotionEvent.emit(type);
  }

}
