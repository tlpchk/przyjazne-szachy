package com.ps.server.entity;

import com.ps.server.Logic.Color;
import com.ps.server.Logic.Pieces.Piece;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Piece")
public class Piece2 {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private int ID;
    @Column(name = "name", nullable = false)
    private com.ps.server.Logic.Pieces.Piece.PieceType name;
    @Column(name = "color", nullable = false)
    private Color color;
    @Column(name = "position_X", nullable = false)
    private int position_X;
    @Column(name = "position_Y", nullable = false)
    private int position_Y;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public com.ps.server.Logic.Pieces.Piece.PieceType getName() {
        return name;
    }

    public void setName(com.ps.server.Logic.Pieces.Piece.PieceType name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPosition_X() {
        return position_X;
    }

    public void setPosition_X(int position_X) {
        this.position_X = position_X;
    }

    public int getPosition_Y() {
        return position_Y;
    }

    public void setPosition_Y(int position_Y) {
        this.position_Y = position_Y;
    }
}
