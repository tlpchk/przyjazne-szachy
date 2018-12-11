package com.ps.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "resumption_place_black_x")
public class Resumption_place_black_x {

    @Column(name = "Game_ID", nullable = false, unique = true)
    private int Game_ID;
    @Column(name = "rook_1_1", nullable = false)
    private int rook_1_1;
    @Column(name = "knight_2_1", nullable = false)
    private int knight_2_1;
    @Column(name = "bishop_3_1", nullable = false)
    private int bishop_3_1;
    @Column(name = "king_4_1", nullable = false)
    private int king_4_1;
    @Column(name = "queen_5_1", nullable = false)
    private int queen_5_1;
    @Column(name = "bishop_6_1", nullable = false)
    private int bishop_6_1;
    @Column(name = "knight_7_1", nullable = false)
    private int knight_7_1;
    @Column(name = "rook_8_1", nullable = false)
    private int rook_8_1;
    @Column(name = "pawn_1_2", nullable = false)
    private int pawn_1_2;
    @Column(name = "pawn_2_2", nullable = false)
    private int pawn_2_2;
    @Column(name = "pawn_3_2", nullable = false)
    private int pawn_3_2;
    @Column(name = "pawn_4_2", nullable = false)
    private int pawn_4_2;
    @Column(name = "pawn_5_2", nullable = false)
    private int pawn_5_2;
    @Column(name = "pawn_6_2", nullable = false)
    private int pawn_6_2;
    @Column(name = "pawn_7_2", nullable = false)
    private int pawn_7_2;
    @Column(name = "pawn_8_2", nullable = false)
    private int pawn_8_2;

    public int getGame_ID() {
        return Game_ID;
    }

    public void setGame_ID(int game_ID) {
        Game_ID = game_ID;
    }

    public int getRook_1_1() {
        return rook_1_1;
    }

    public void setRook_1_1(int rook_1_1) {
        this.rook_1_1 = rook_1_1;
    }

    public int getKnight_2_1() {
        return knight_2_1;
    }

    public void setKnight_2_1(int knight_2_1) {
        this.knight_2_1 = knight_2_1;
    }

    public int getBishop_3_1() {
        return bishop_3_1;
    }

    public void setBishop_3_1(int bishop_3_1) {
        this.bishop_3_1 = bishop_3_1;
    }

    public int getKing_4_1() {
        return king_4_1;
    }

    public void setKing_4_1(int king_4_1) {
        this.king_4_1 = king_4_1;
    }

    public int getQueen_5_1() {
        return queen_5_1;
    }

    public void setQueen_5_1(int queen_5_1) {
        this.queen_5_1 = queen_5_1;
    }

    public int getBishop_6_1() {
        return bishop_6_1;
    }

    public void setBishop_6_1(int bishop_6_1) {
        this.bishop_6_1 = bishop_6_1;
    }

    public int getKnight_7_1() {
        return knight_7_1;
    }

    public void setKnight_7_1(int knight_7_1) {
        this.knight_7_1 = knight_7_1;
    }

    public int getRook_8_1() {
        return rook_8_1;
    }

    public void setRook_8_1(int rook_8_1) {
        this.rook_8_1 = rook_8_1;
    }

    public int getPawn_1_2() {
        return pawn_1_2;
    }

    public void setPawn_1_2(int pawn_1_2) {
        this.pawn_1_2 = pawn_1_2;
    }

    public int getPawn_2_2() {
        return pawn_2_2;
    }

    public void setPawn_2_2(int pawn_2_2) {
        this.pawn_2_2 = pawn_2_2;
    }

    public int getPawn_3_2() {
        return pawn_3_2;
    }

    public void setPawn_3_2(int pawn_3_2) {
        this.pawn_3_2 = pawn_3_2;
    }

    public int getPawn_4_2() {
        return pawn_4_2;
    }

    public void setPawn_4_2(int pawn_4_2) {
        this.pawn_4_2 = pawn_4_2;
    }

    public int getPawn_5_2() {
        return pawn_5_2;
    }

    public void setPawn_5_2(int pawn_5_2) {
        this.pawn_5_2 = pawn_5_2;
    }

    public int getPawn_6_2() {
        return pawn_6_2;
    }

    public void setPawn_6_2(int pawn_6_2) {
        this.pawn_6_2 = pawn_6_2;
    }

    public int getPawn_7_2() {
        return pawn_7_2;
    }

    public void setPawn_7_2(int pawn_7_2) {
        this.pawn_7_2 = pawn_7_2;
    }

    public int getPawn_8_2() {
        return pawn_8_2;
    }

    public void setPawn_8_2(int pawn_8_2) {
        this.pawn_8_2 = pawn_8_2;
    }
}
