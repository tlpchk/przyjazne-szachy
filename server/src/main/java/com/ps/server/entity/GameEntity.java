package com.ps.server.entity;

import com.ps.server.enums.GameType;
import com.ps.server.enums.Result;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "game")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long ID;

    @Column(name = "startTime", nullable = false)
    private Timestamp startTime;

    @Column(name = "breakTime")
    private Timestamp breakTime;

    @ManyToOne
    @JoinColumn(name = "first_player")
    private PlayerEntity firstPlayer;

    @ManyToOne
    @JoinColumn(name = "second_player")
    private PlayerEntity secondPlayer;

    @Enumerated(EnumType.STRING)
    @Column(name = "game_type")
    private GameType gameType;

    @Enumerated(EnumType.STRING)
    private Result result;

    @Column(name = "move_time_limit")
    private Time moveTimeLimit;

    @Column(name = "game_time_limit")
    private Time gameTimeLimit;
}