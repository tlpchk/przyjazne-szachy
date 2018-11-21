package com.ps.server.Logic;

import com.ps.server.Logic.Pieces.Piece;

public class Change {
    public final Position location;
    public final Piece.PieceType type;
    public final Color color;

    /**
     * Class constructor.
     * @param location specifies the location on board of the change
     * @param type specifies the type of chess piece standing on that location (after change)
     * @param color specifies the color of chess piece
     */
    public Change(Position location, Piece.PieceType type, Color color) {
        this.location = location;
        this.type = type;
        this.color = color;
    }

    /**
     * Checks if two Change objects are equal in terms of parameters
     * @param change
     * @return if this change equals to change given as parameter
     */
    public boolean eq(Change change) {
        return change.location.equalsToPos(location) && change.type == type && change.color == color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return  location.toString() + " " + type + " " + color;
    }
}
