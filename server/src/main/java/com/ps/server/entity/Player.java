package com.ps.server.entity;

import com.ps.server.Logic.Pieces.Piece;
import com.ps.server.enums.PlayerType;

import javax.persistence.*;

@Entity
@Table(name = "Player")
public class Player {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private int ID;
    @Column(name = "name", nullable = false)
    private Piece.PieceType name;
    @Column(name = "player_type", nullable = false, unique = true)
    private PlayerType player_type;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Piece.PieceType getName() {
        return name;
    }

    public void setName(Piece.PieceType name) {
        this.name = name;
    }

    public PlayerType getPlayer_type() {
        return player_type;
    }

    public void setPlayer_type(PlayerType player_type) {
        this.player_type = player_type;
    }
}
