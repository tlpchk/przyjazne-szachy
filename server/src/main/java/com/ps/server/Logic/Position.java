package com.ps.server.Logic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Position {
    public int row , col;

//    public Position(int row, int col) {
//        this.row = row;
//        this.col = col;
//    }

    /**
     * Checks if two Position objects are equal in terms of parameters
     * @param position
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
