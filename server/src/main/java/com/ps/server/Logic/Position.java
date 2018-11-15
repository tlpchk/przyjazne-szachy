package com.ps.server.Logic;

public class Position {
    public final int row ,col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean equalsToPos(Position position) {
        return position.row == row && position.col == col;
    }

    @Override
    public String toString() {
        return "[ " + row + " , " + col + " ]";
    }
}
