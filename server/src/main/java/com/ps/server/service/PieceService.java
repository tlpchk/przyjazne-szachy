package com.ps.server.service;

import com.ps.server.Logic.*;
import com.ps.server.Logic.Pieces.*;
import com.ps.server.entity.GameEntity;
import com.ps.server.entity.PieceEntity;
import com.ps.server.entity.PieceSetEntity;
import com.ps.server.repository.PieceRepository;
import com.ps.server.repository.PieceSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PieceService {

    @Autowired
    private PieceRepository pieceRepository;

    @Autowired
    private SetFactory.WhiteSetFactory whiteSetFactory;

    @Autowired
    private SetFactory.BlackSetFactory blackSetFactory;

    @Autowired
    private PieceSetRepository pieceSetRepository;


    public PieceSetEntity createWhiteSetEntity(GameEntity game) {
//        int pawnRow = 6;
//        Position[] rookPositions = {new Position(7, 0), new Position(7, 7)};
//        Position[] knightPositions = {new Position(7, 1), new Position(7, 6)};
//        Position[] bishopPositions = {new Position(7, 2), new Position(7, 5)};
//        Position kingsPosition = new Position(7, 4);
//        Position queensPosition = new Position(7, 3);
//        Color color = Color.WHITE;
//
//        List<Piece> set = new ArrayList<>();
//        for (int i = 0; i < Board.COL_NUM; i++) {
//            Pawn pawn = new Pawn(color, new Position(pawnRow, i));
//            pieceRepository.save(pieceToEntity(pawn));
//            set.add(pawn);
//        }
//
//        for (int i = 0; i < 2; i++) {
//            Rook rook = new Rook(color, rookPositions[i]);
//            pieceRepository.save(pieceToEntity(rook));
//            set.add(rook);
//
//            Bishop bishop = new Bishop(color, bishopPositions[i]);
//            pieceRepository.save(pieceToEntity(bishop));
//            set.add(bishop);
//
//            Knight knight = new Knight(color, knightPositions[i]);
//            pieceRepository.save(pieceToEntity(knight));
//            set.add(knight);
//        }
//        King king = new King(color, kingsPosition);
//        pieceRepository.save(pieceToEntity(king));
//        set.add(king);
//
//        Queen queen = new Queen(color, queensPosition);
//        pieceRepository.save(pieceToEntity(queen));
//        set.add(queen);
        return createSetEntity(game, Color.WHITE);
    }

    public PieceSetEntity createBlackSetEntity(GameEntity game) {
        return createSetEntity(game, Color.BLACK);
    }

    private PieceSetEntity createSetEntity(GameEntity gameEntity, Color color) {
        PieceSetEntity pieceSetEntity = new PieceSetEntity();
        pieceSetEntity.setGame(gameEntity);
        pieceSetEntity.setColor(color);
        List<Piece> pieceSet = color == Color.WHITE ? whiteSetFactory.createSet().getSet() : blackSetFactory.createSet().getSet();
        pieceSetEntity.setPieceSet(getPieceEntityList(pieceSet));
        pieceSetRepository.save(pieceSetEntity);
        return pieceSetEntity;
    }

    private List<PieceEntity> getPieceEntityList(List<Piece> pieceSet) {
        List<PieceEntity> pieceEntityList = new ArrayList<>();
        for (Piece piece : pieceSet) {
            PieceEntity pieceEntity = pieceToEntity(piece);
            pieceRepository.save(pieceEntity);
            pieceEntityList.add(pieceEntity);
        }
        return pieceEntityList;
    }

    private PieceEntity pieceToEntity(Piece piece) {
        PieceEntity pieceEntity = new PieceEntity();
        Piece.PieceType type;
        if (piece instanceof Bishop) {
            type = Piece.PieceType.BISHOP;
        } else if (piece instanceof King) {
            type = Piece.PieceType.KING;
        } else if (piece instanceof Knight) {
            type = Piece.PieceType.KNIGHT;
        } else if (piece instanceof Queen) {
            type = Piece.PieceType.QUEEN;
        } else if (piece instanceof Rook) {
            type = Piece.PieceType.ROOK;
        } else {
            type = Piece.PieceType.PAWN;
        }
        pieceEntity.setPieceType(type);
        return pieceEntity;
    }

}
