package com.ps.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.ps.server.Logic.Pieces.Piece.PieceType;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PieceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "piece_type")
    private PieceType pieceType;

    @ManyToOne
    @JoinColumn(name = "piece_set")
    private PieceSetEntity pieceSet;

    @Column(name = "position_X")
    private int positionX;

    @Column(name = "position_Y")
    private int positionY;

    @Column(name = "castle_rights")
    @ColumnDefault("true")
    private boolean castleRights;

    @Column(name = "first_move")
    @ColumnDefault("true")
    private boolean isFirstMove;

    //TODO RS: giveEnPassantRights for Pawn
}
