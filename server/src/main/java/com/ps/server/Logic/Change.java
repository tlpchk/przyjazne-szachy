package com.ps.server.Logic;

import com.ps.server.Logic.Pieces.Piece;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Change {
    public Position location;
    public Piece.PieceType type;
    public Color color;

    /**
     * Class constructor.
     * @param location specifies the location on board of the change
     * @param type specifies the type of chess Piece standing on that location (after change)
     * @param color specifies the color of chess Piece
     */
    public Change(Position location, Piece.PieceType type, Color color) {
        this.location = location;
        this.type = type;
        this.color = color;
    }

    /**
     * Checks if two Change objects are equal in terms of parameters
     * @param change other Change object
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
