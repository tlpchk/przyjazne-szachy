package com.ps.server.Logic;

public class Move {
    final Position loc;
    final Position dest;
    final Color color;

    public Move(Position loc, Position dest, Color color) {
        this.loc = loc;
        this.dest = dest;
        this.color = color;
    }
}
