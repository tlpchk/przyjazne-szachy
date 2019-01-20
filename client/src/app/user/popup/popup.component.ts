import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Router} from '@angular/router';
import {GameState} from '../../_models/gameInfoDTO';
import {BoardService} from '../../_services/board.service';

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.css']
})
export class PopupComponent implements OnInit {

  constructor(private router: Router,
              private boardService: BoardService) { }
  popupType = Object.freeze({
    PROM: 'promotion',
    END: 'message'
  });


  public isDisplayed;
  public message;

  type: string;
  routerLink: string;

  ngOnInit() {
    this.type = this.popupType.END;
  }

  show(data) {
    this.message = data;
    if (!this.isDisplayed) {
      this.isDisplayed = true;
    }

  }

  hide() {
    this.message = '';
    if (this.isDisplayed) {
      this.isDisplayed = false;
    }
    if (this.routerLink != null) {
      this.router.navigateByUrl(this.routerLink);
    }
  }

}
