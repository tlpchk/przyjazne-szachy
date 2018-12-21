package com.ps.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "move")
public class MoveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private BigInteger ID;

    @Column(name = "created", nullable = false)
    private Timestamp creationDate;

    @Column(name = "origin_column", nullable = false)
    private int originColumn;

    @Column(name = "origin_row", nullable = false)
    private int originRow;

    @Column(name = "destination_column", nullable = false)
    private int destinationColumn;

    @Column(name = "destination_row", nullable = false)
    private int destinationRow;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private GameEntity game;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerEntity player;
}