package com.ps.server.Logic;

import com.ps.server.Logic.Pieces.Piece;

public class Change {
    public final Position location;
    public final Piece.PieceType type;
    public final Color color;

    public Change(Position location, Piece.PieceType type, Color color) {
        this.location = location;
        this.type = type;
        this.color = color;
    }

    public boolean eq(Change change) {
        return change.location.equalsToPos(location) && change.type == type && change.color == color;
    }

    @Override
    public String toString() {
        return  location.toString() + " " + type + " " + color;
    }
}
