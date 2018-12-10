package com.ps.server.Logic;

import com.ps.server.Logic.Pieces.*;

import java.util.ArrayList;
import java.util.List;

public abstract class SetFactory {
    private final int pawnRow;
    private final Position[] rookPositions;
    private final Position[] bishopPositions;
    private final Position[] knightPositions;
    private final Position kingsPosition;
    private final Position queensPosition;
    private final Color color;

    SetFactory(int pawnRow, Position[] rookPositions, Position[] bishopPositions,
               Position[] knightPositions, Position kingsPosition, Position queensPosition, Color color) {
        this.pawnRow = pawnRow;
        this.rookPositions = rookPositions;
        this.bishopPositions = bishopPositions;
        this.knightPositions = knightPositions;
        this.kingsPosition = kingsPosition;
        this.queensPosition = queensPosition;
        this.color = color;
    }

    /**
     * Creates an initial set with all pieces in initial positions.
     * @return created set
     */
    public Set createSet() {
        List<Piece> set = new ArrayList<>();
        for (int i = 0; i < Board.COL_NUM; i++) {
            set.add(new Pawn(color, new Position(pawnRow, i)));
        }

        for (int i = 0; i < 2; i++) {
            set.add(new Rook(color, rookPositions[i]));
            set.add(new Bishop(color, bishopPositions[i]));
            set.add(new Knight(color, knightPositions[i]));
        }

        set.add(0, new King(color, kingsPosition));
        set.add(new Queen(color, queensPosition));

        return new Set(color, set);
    }

    public static class WhiteSetFactory extends SetFactory {
        public static final int pawnRow = 6;
        public static final Position[] rookPositions = {new Position(7, 0), new Position(7, 7)};
        public static final Position[] knightPositions = {new Position(7, 1), new Position(7, 6)};
        public static final Position[] bishopPositions = {new Position(7, 2), new Position(7, 5)};
        public static final Position kingsPosition = new Position(7, 4);
        public static final Position queensPosition = new Position(7, 3);
        public static final Color color = Color.WHITE;

        WhiteSetFactory() {
            super(pawnRow, rookPositions, bishopPositions, knightPositions, kingsPosition, queensPosition, color);
        }
    }


    public static class BlackSetFactory extends SetFactory {
        public static final int pawnRow = 1;
        public static final Position[] rookPositions = {new Position(0, 0), new Position(0, 7)};
        public static final Position[] knightPositions = {new Position(0, 1), new Position(0, 6)};
        public static final Position[] bishopPositions = {new Position(0, 2), new Position(0, 5)};
        public static final Position kingsPosition = new Position(0, 4);
        public static final Position queensPosition = new Position(0, 3);
        public static final Color color = Color.BLACK;

        BlackSetFactory() {
            super(pawnRow, rookPositions, bishopPositions, knightPositions, kingsPosition, queensPosition, color);
        }

    }
}
