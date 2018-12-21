package com.ps.server.dto;

import com.ps.server.Logic.Color;
import com.ps.server.Logic.Pieces.Piece;
import com.ps.server.Logic.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class PieceDTO {
    private int row;
    private int column;
    private Piece.PieceType type;
    private Color color;
    private List<Position> possibleMoves;

    public PieceDTO(int row, int column, Piece.PieceType type, Color color, List<Position> possibleMoves) {
        this.row = row;
        this.column = column;
        this.type = type;
        this.color = color;
        this.possibleMoves = possibleMoves;
    }


    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Color getColor() {
        return color;
    }

    public Piece.PieceType getType() {
        return type;
    }

    public List<Position> getPossibleMoves() {
        return possibleMoves;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setType(Piece.PieceType type) {
        this.type = type;
    }

    public void setPossibleMoves(List<Position> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }
}
