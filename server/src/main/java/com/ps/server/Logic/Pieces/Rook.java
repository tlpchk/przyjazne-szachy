package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.Color;
import static com.ps.server.Logic.Color.*;

public class Rook extends ChessPiece {
    private boolean castleRights;

    public Rook(Color color) {
        super(color);
        castleRights = true;
    }

    @Override
    public String toString() {
        return color == WHITE ? "♜" : "♖";
    }
}