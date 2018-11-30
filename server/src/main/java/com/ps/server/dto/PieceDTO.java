package com.ps.server.dto;

import com.ps.server.logic.Color;
import com.ps.server.logic.pieces.Piece;
import com.ps.server.logic.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PieceDTO {
    private int row;
    private int column;
    private Piece.PieceType type;
    private Color color;
    private List<Position> possibleMoves;
}
