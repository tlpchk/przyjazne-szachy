package com.ps.server.entity;

import com.ps.server.enums.GameType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "first_player_id", nullable = false)
    private PlayerEntity firstPlayer;

    @ManyToOne
    @JoinColumn(name = "second_player_id")
    private PlayerEntity secondPlayer;

    @Enumerated(EnumType.STRING)
    private GameType gameType;

    @Column(name = "isGameFinished", nullable = false)
    private boolean isGameFinished;

    @Column(name = "starting_time")
    private Date startingTime;

    public Long getId() {
        return id;
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }

    public PlayerEntity getFirstPlayer() {
        return firstPlayer;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public PlayerEntity getSecondPlayer() {
        return secondPlayer;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstPlayer(PlayerEntity firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public void setSecondPlayer(PlayerEntity secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public void setGameFinished(boolean gameFinished) {
        isGameFinished = gameFinished;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }
}
