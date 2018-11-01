package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.Color;

public abstract class ChessPiece {
    final Color color;

    ChessPiece(Color color) {
        this.color = color;
    }
}
