package com.ps.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "Matches")
public class Matches {
    @Id @GeneratedValue
    @Column(name = "ID", nullable = false)
    private int ID;
    @Column(name = "gameID", nullable = false)
    private int gameID;
    @Column(name = "playerID", nullable = false)
    private int playerID;
    @Column(name = "result", nullable = false)
    private short result;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public short getResult() {
        return result;
    }

    public void setResult(short result) {
        this.result = result;
    }
}