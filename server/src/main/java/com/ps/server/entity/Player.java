package com.ps.server.entity;

import com.ps.server.Logic.Color;
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
    @Column(name = "color", nullable = false)
    private Color color;
    @Column(name = "player_type", nullable = false, unique = true)
    private PlayerType player_type;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public PlayerType getPlayer_type() {
        return player_type;
    }

    public void setPlayer_type(PlayerType player_type) {
        this.player_type = player_type;
    }
}
