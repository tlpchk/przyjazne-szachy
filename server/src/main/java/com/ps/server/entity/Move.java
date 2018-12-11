package com.ps.server.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "Move")
public class Move {
    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    private BigInteger id;
    @Column(name = "created", nullable = false)
    private Timestamp created;
    @Column(name = "destination_column", nullable = false)
    private int destination_column;
    @Column(name = "destination_row", nullable = false)
    private int destination_row;
    @Column(name = "origin_column", nullable = false)
    private int origin_column; //INT(11) NOT NULL,
    @Column(name = "origin_row", nullable = false)
    private int origin_row; //INT(11) NOT NULL,
    @Column(name = "game_id", nullable = false)
    private BigInteger game_id;
    @Column(name = "player_id", nullable = false)
    private BigInteger player_id;



    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public int getDestination_column() {
        return destination_column;
    }

    public void setDestination_column(int destination_column) {
        this.destination_column = destination_column;
    }

    public int getDestination_row() {
        return destination_row;
    }

    public void setDestination_row(int destination_row) {
        this.destination_row = destination_row;
    }

    public int getOrigin_column() {
        return origin_column;
    }

    public void setOrigin_column(int origin_column) {
        this.origin_column = origin_column;
    }

    public int getOrigin_row() {
        return origin_row;
    }

    public void setOrigin_row(int origin_row) {
        this.origin_row = origin_row;
    }

    public BigInteger getGame_id() {
        return game_id;
    }

    public void setGame_id(BigInteger game_id) {
        this.game_id = game_id;
    }

    public BigInteger getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(BigInteger player_id) {
        this.player_id = player_id;
    }
}