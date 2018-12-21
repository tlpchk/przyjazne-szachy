package com.ps.server.dto;

import com.ps.server.Logic.Pieces.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDTO {
    private Long playerId;
    private Piece.PieceType pieceType;
}
