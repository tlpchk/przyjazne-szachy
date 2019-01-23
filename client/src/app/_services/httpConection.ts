import {HttpHeaders} from "@angular/common/http";

/** @ignore*/
export const loginUrl = 'http://localhost:8080/login';
/** @ignore*/
export const registerUrl = 'http://localhost:8080/register';
/** @ignore*/
export const  logoutUrl = 'http://localhost:8080/bye';
/** @ignore*/
export const  profileUrl = 'http://localhost:8080/user';
/** @ignore*/
export const  gamesUrl = 'http://localhost:8080/games';
/** @ignore*/
export const playersUrl = 'http://localhost:8080/players';
/** @ignore*/
export const verifyUrl = 'http://localhost:8080/verify';
/** @ignore*/
export const resetUrl = 'http://localhost:8080/reset';
/** @ignore*/
export const changeUrl = 'http://localhost:8080/reset/change';
/** @ignore*/
export const rankingUrl = 'http://localhost:8080/ranking';
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
