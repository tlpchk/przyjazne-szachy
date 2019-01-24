import {HttpHeaders} from "@angular/common/http";

/** @ignore*/
export const loginUrl = 'https://przyjazne-szachy.herokuapp.com/api/login';
/** @ignore*/
export const registerUrl = 'https://przyjazne-szachy.herokuapp.com/api/register';
/** @ignore*/
export const  logoutUrl = 'https://przyjazne-szachy.herokuapp.com/api/bye';
/** @ignore*/
export const  profileUrl = 'https://przyjazne-szachy.herokuapp.com/api/user';
/** @ignore*/
export const  gamesUrl = 'https://przyjazne-szachy.herokuapp.com/api/games';
/** @ignore*/
export const playersUrl = 'https://przyjazne-szachy.herokuapp.com/api/players';
/** @ignore*/
export const verifyUrl = 'https://przyjazne-szachy.herokuapp.com/api/verify';
/** @ignore*/
export const resetUrl = 'https://przyjazne-szachy.herokuapp.com/api/reset';
/** @ignore*/
export const changeUrl = 'https://przyjazne-szachy.herokuapp.com/api/reset/change';
/** @ignore*/
export const rankingUrl = 'https://przyjazne-szachy.herokuapp.com/api/ranking';
/** @ignore*/
export const  moveSubUrl = '/move';
/** @ignore*/
export const  boardSubUrl = '/board';
/** @ignore*/
export const  updateSubUrl = '/update';
/** @ignore*/
export const  possibleMovesSubUrl = '/possibleMoves';
/** @ignore*/
export const  promotionSubUrl = '/promote';
/** @ignore*/
export const joinSubUrl = "/join";
/** @ignore*/
export const timerSubUrl = '/timer';


/** @ignore*/
export const  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

/** @ignore*/
export interface authResponse {
    success: boolean;
    message: string;
}

/** @ignore*/
export interface UserDetails {
    position: number;
    username: string;
    games: number;
    wonGames: number;
    lostGames: number;
    drawGames: number;
}

/** @ignore*/
export interface RankingRecord{
    position: number;
    user: string;
    score: number;
    numberOfWonGames;
    numberOfLostGames;
    numberOfDrawGames
}

/** @ignore*/
export class PasswordReset {
    token: string;
    password: string;
}

/** @ignore*/
export class User {
    username: string;
    password: string;
    email: string;

    constructor(username: string, password: string, email: string = ""){
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
