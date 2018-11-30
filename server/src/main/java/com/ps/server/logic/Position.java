package com.ps.server.logic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Position {
    public int row , column;

//    public Position(int row, int column) {
//        this.row = row;
//        this.column = column;
//    }

    /**
     * Checks if two Position objects are equal in terms of parameters
     * @param position
     * @return if this change equals to position given as parameter
     */
    public boolean equalsToPos(Position position) {
        return position.row == row && position.column == column;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[ " + row + " , " + column + " ]";
    }
}
