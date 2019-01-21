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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "start_time", nullable = false)
    private Timestamp startTime;

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

    @Column(name="is_ranked")
    private boolean isRanked;

    @Column(name="is_finished")
    private boolean isFinished;

    @Column(name = "move_time_limit")
    private Time moveTimeLimit;

    @Column(name = "game_time_limit")
    private Time gameTimeLimit;
}