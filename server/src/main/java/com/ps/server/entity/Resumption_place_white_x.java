package com.ps.server.entity;

import javax.persistence.Column;

import javax.persistence.*;

@Entity
@Table(name = "Resumption_place_white_x")
public class Resumption_place_white_x {

    @Id
    @Column(name = "Game_ID", nullable = false, unique = true)
    private int Game_ID;
    @Column(name = "rook_1_8", nullable = false)
    private int rook_1_8;
    @Column(name = "knight_2_8", nullable = false)
    private int knight_2_8;
    @Column(name = "bishop_3_8", nullable = false)
    private int bishop_3_8;
    @Column(name = "king_4_8", nullable = false)
    private int king_4_8;
    @Column(name = "queen_5_8", nullable = false)
    private int queen_5_8;
    @Column(name = "bishop_6_8", nullable = false)
    private int bishop_6_8;
    @Column(name = "knight_7_8", nullable = false)
    private int knight_7_8;
    @Column(name = "rook_8_8", nullable = false)
    private int rook_8_8;
    @Column(name = "pawn_1_7", nullable = false)
    private int pawn_1_7;
    @Column(name = "pawn_2_7", nullable = false)
    private int pawn_2_7;
    @Column(name = "pawn_3_7", nullable = false)
    private int pawn_3_7;
    @Column(name = "pawn_4_7", nullable = false)
    private int pawn_4_7;
    @Column(name = "pawn_5_7", nullable = false)
    private int pawn_5_7;
    @Column(name = "pawn_6_7", nullable = false)
    private int pawn_6_7;
    @Column(name = "pawn_7_7", nullable = false)
    private int pawn_7_7;
    @Column(name = "pawn_8_7", nullable = false)
    private int pawn_8_7;

    public int getGame_ID() {
        return Game_ID;
    }

    public void setGame_ID(int game_ID) {
        Game_ID = game_ID;
    }

    public int getRook_1_8() {
        return rook_1_8;
    }

    public void setRook_1_8(int rook_1_8) {
        this.rook_1_8 = rook_1_8;
    }

    public int getKnight_2_8() {
        return knight_2_8;
    }

    public void setKnight_2_8(int knight_2_8) {
        this.knight_2_8 = knight_2_8;
    }

    public int getBishop_3_8() {
        return bishop_3_8;
    }

    public void setBishop_3_8(int bishop_3_8) {
        this.bishop_3_8 = bishop_3_8;
    }

    public int getKing_4_8() {
        return king_4_8;
    }

    public void setKing_4_8(int king_4_8) {
        this.king_4_8 = king_4_8;
    }

    public int getQueen_5_8() {
        return queen_5_8;
    }

    public void setQueen_5_8(int queen_5_8) {
        this.queen_5_8 = queen_5_8;
    }

    public int getBishop_6_8() {
        return bishop_6_8;
    }

    public void setBishop_6_8(int bishop_6_8) {
        this.bishop_6_8 = bishop_6_8;
    }

    public int getKnight_7_8() {
        return knight_7_8;
    }

    public void setKnight_7_8(int knight_7_8) {
        this.knight_7_8 = knight_7_8;
    }

    public int getRook_8_8() {
        return rook_8_8;
    }

    public void setRook_8_8(int rook_8_8) {
        this.rook_8_8 = rook_8_8;
    }

    public int getPawn_1_7() {
        return pawn_1_7;
    }

    public void setPawn_1_7(int pawn_1_7) {
        this.pawn_1_7 = pawn_1_7;
    }

    public int getPawn_2_7() {
        return pawn_2_7;
    }

    public void setPawn_2_7(int pawn_2_7) {
        this.pawn_2_7 = pawn_2_7;
    }

    public int getPawn_3_7() {
        return pawn_3_7;
    }

    public void setPawn_3_7(int pawn_3_7) {
        this.pawn_3_7 = pawn_3_7;
    }

    public int getPawn_4_7() {
        return pawn_4_7;
    }

    public void setPawn_4_7(int pawn_4_7) {
        this.pawn_4_7 = pawn_4_7;
    }

    public int getPawn_5_7() {
        return pawn_5_7;
    }

    public void setPawn_5_7(int pawn_5_7) {
        this.pawn_5_7 = pawn_5_7;
    }

    public int getPawn_6_7() {
        return pawn_6_7;
    }

    public void setPawn_6_7(int pawn_6_7) {
        this.pawn_6_7 = pawn_6_7;
    }

    public int getPawn_7_7() {
        return pawn_7_7;
    }

    public void setPawn_7_7(int pawn_7_7) {
        this.pawn_7_7 = pawn_7_7;
    }

    public int getPawn_8_7() {
        return pawn_8_7;
    }

    public void setPawn_8_7(int pawn_8_7) {
        this.pawn_8_7 = pawn_8_7;
    }
}
