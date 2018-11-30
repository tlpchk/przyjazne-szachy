package com.ps.server.logic;

import com.ps.server.entity.PlayerEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {

    private PlayerEntity firstPlayer;

    private PlayerEntity secondPlayer;

    private Board board;

    public Game(PlayerEntity firstPlayer, PlayerEntity secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }
}
