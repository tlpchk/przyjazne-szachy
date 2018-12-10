package com.ps.server.Logic;

public class Position {
    /** row and column describing position of piece on board (both in bounds 0-7) */
    public final int row ,col;

    /**
     * Class constructor for position of a piece on board
     * @param row (on board in bounds 0-7)
     * @param col (on board in bounds 0-7)
     */
    public Position(int row, int col) {
//        assert(row >= 0 && col >= 0 && row < 7 && col < 7);
        this.row = row;
        this.col = col;
    }

    /**
     * Checks if two Position objects are equal in terms of parameters
     * @param position this position will be compared to
     * @return if this change equals to position given as parameter
     */
    public boolean equalsToPos(Position position) {
        return position.row == row && position.col == col;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[ " + row + " , " + col + " ]";
    }
}
