package com.ps.server.entity;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "Game")
public class Game {
    @Id @GeneratedValue
    @Column(name = "ID",nullable = false)
    private int ID;
    @Column(name = "start_time", nullable = false)
    private Timestamp start_time;
    @Column(name = "break_time", nullable = false)
    private Timestamp break_time;
    @Column(name = "playerID_resumption", nullable = false)
    private int playerID_resumption;
    @Column(name = "player_1", nullable = false)
	private int player_1; //INT(11) NOT NULL,
    @Column(name = "player_2", nullable = false)
	private int player_2; //INT(11) NOT NULL,
    @Column(name = "result", nullable = false)
	private Result result; // ENUM('1_Win','2_Win','Draw; //NOT NULL DEFAULT 'Draw' COLLATE 'utf16_polish_ci',
    @Column(name = "move_limit", nullable = false)
    private Time move_limit; // TIME NOT NULL DEFAULT '00:05:00',
    @Column(name = "game_limit", nullable = false)
    private Time game_limit; // TIME NOT NULL DEFAULT '02:00:00',


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
	    this.start_time = start_time;
    }

    public Timestamp getBreak_time() {
        return break_time;
    }

    public void setBreak_time(Timestamp break_time) {
        this.break_time = break_time;
    }

    public int getPlayerID_resumption(int playerID_resumption) {
	    return playerID_resumption;
    }

    public void setPlayerID_resumption(int playerID_resumption) {
        this.playerID_resumption = playerID_resumption;
    }

    public int getPlayer_1() {
        return player_1;
    }

    public void setPlayer_1(int player_1) {
        this.player_1 = player_1;
    }

    public int getPlayer_2() {
        return player_2;
    }

    public void setPlayer_2(int player_2) {
        this.player_2 = player_2;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Time getMove_limit() {
        return move_limit;
    }

    public void setMove_limit(Time move_limit) {
        this.move_limit = move_limit;
    }

    public Time getGame_limit() {
        return game_limit;
    }

    public void setGame_limit(Time game_limit) {
        this.game_limit = game_limit;
    }
}