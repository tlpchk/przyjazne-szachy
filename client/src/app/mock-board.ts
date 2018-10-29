import {Cell} from './cell';
import {Color} from './color';
import {Piece, PieceType} from './piece';

export const whiteKing =  {type: PieceType.KING, color: Color.white};
export const whiteQueen =  {type: PieceType.QUEEN, color: Color.white};
export const whitePawn =  {type: PieceType.PAWN, color: Color.white};
export const whiteRook =  {type: PieceType.ROOK, color: Color.white};
export const whiteKnight =  {type: PieceType.KNIGHT, color: Color.white};
export const whiteBishop =  {type: PieceType.BISHOP, color: Color.white};

export const blackKing =  {type: PieceType.KING, color: Color.black};
export const blackQueen =  {type: PieceType.QUEEN, color: Color.black};
export const blackPawn =  {type: PieceType.PAWN, color: Color.black};
export const blackRook =  {type: PieceType.ROOK, color: Color.black};
export const blackKnight =  {type: PieceType.KNIGHT, color: Color.black};
export const blackBishop =  {type: PieceType.BISHOP, color: Color.black};




export const BOARD: Cell[] = [
    {id : 1 , cell_color : Color.white, piece: whiteRook},
    {id : 2 , cell_color : Color.black, piece: whiteKnight},
    {id : 3 , cell_color : Color.white, piece: whiteBishop},
    {id : 4 , cell_color : Color.black, piece: whiteKing},
    {id : 5 , cell_color : Color.white, piece: whiteQueen},
    {id : 6 , cell_color : Color.black, piece: whiteBishop},
    {id : 7 , cell_color : Color.white, piece: whiteKnight},
    {id : 8 , cell_color : Color.black, piece: whiteRook},
    {id : 9 , cell_color : Color.black, piece: whitePawn},
    {id : 10 , cell_color : Color.white, piece: whitePawn},
    {id : 11 , cell_color : Color.black, piece: whitePawn},
    {id : 12 , cell_color : Color.white, piece: whitePawn},
    {id : 13 , cell_color : Color.black, piece: whitePawn},
    {id : 14 , cell_color : Color.white, piece: whitePawn},
    {id : 15 , cell_color : Color.black, piece: whitePawn},
    {id : 16 , cell_color : Color.white, piece: whitePawn},
    {id : 17 , cell_color : Color.white, piece: null},
    {id : 18 , cell_color : Color.black, piece: null},
    {id : 19 , cell_color : Color.white, piece: null},
    {id : 20 , cell_color : Color.black, piece: null},
    {id : 21 , cell_color : Color.white, piece: null},
    {id : 22 , cell_color : Color.black, piece: null},
    {id : 23 , cell_color : Color.white, piece: null},
    {id : 24 , cell_color : Color.black, piece: null},
    {id : 25 , cell_color : Color.black, piece: null},
    {id : 26 , cell_color : Color.white, piece: null},
    {id : 27 , cell_color : Color.black, piece: null},
    {id : 28 , cell_color : Color.white, piece: null},
    {id : 29 , cell_color : Color.black, piece: null},
    {id : 30 , cell_color : Color.white, piece: null},
    {id : 31 , cell_color : Color.black, piece: null},
    {id : 32 , cell_color : Color.white, piece: null},
    {id : 33 , cell_color : Color.white, piece: null},
    {id : 34 , cell_color : Color.black, piece: null},
    {id : 35 , cell_color : Color.white, piece: null},
    {id : 36 , cell_color : Color.black, piece: null},
    {id : 37 , cell_color : Color.white, piece: null},
    {id : 38 , cell_color : Color.black, piece: null},
    {id : 39 , cell_color : Color.white, piece: null},
    {id : 40 , cell_color : Color.black, piece: null},
    {id : 41 , cell_color : Color.black, piece: null},
    {id : 42 , cell_color : Color.white, piece: null},
    {id : 43 , cell_color : Color.black, piece: null},
    {id : 44 , cell_color : Color.white, piece: null},
    {id : 45 , cell_color : Color.black, piece: null},
    {id : 46 , cell_color : Color.white, piece: null},
    {id : 47 , cell_color : Color.black, piece: null},
    {id : 48 , cell_color : Color.white, piece: null},
    {id : 49 , cell_color : Color.white, piece: blackPawn},
    {id : 50 , cell_color : Color.black, piece: blackPawn},
    {id : 51 , cell_color : Color.white, piece: blackPawn},
    {id : 52 , cell_color : Color.black, piece: blackPawn},
    {id : 53 , cell_color : Color.white, piece: blackPawn},
    {id : 54 , cell_color : Color.black, piece: blackPawn},
    {id : 55 , cell_color : Color.white, piece: blackPawn},
    {id : 56 , cell_color : Color.black, piece: blackPawn},
    {id : 57 , cell_color : Color.black, piece: blackRook},
    {id : 58 , cell_color : Color.white, piece: blackKnight},
    {id : 59 , cell_color : Color.black, piece: blackBishop},
    {id : 60 , cell_color : Color.white, piece: blackKing},
    {id : 61 , cell_color : Color.black, piece: blackQueen},
    {id : 62 , cell_color : Color.white, piece: blackBishop},
    {id : 63 , cell_color : Color.black, piece: blackKnight},
    {id : 64 , cell_color : Color.white, piece: blackRook},
];
