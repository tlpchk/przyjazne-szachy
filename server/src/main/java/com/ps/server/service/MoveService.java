package com.ps.server.service;

import com.ps.server.entity.GameEntity;
import com.ps.server.entity.PlayerEntity;
import com.ps.server.enums.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MoveService {

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    public boolean isPlayerTurn(GameEntity gameEntity, PlayerEntity player) {
        return (gameEntity.getGameStatus() == GameStatus.FIRST_PLAYER_TURN && gameEntity.getFirstPlayer().equals(player))
                || (gameEntity.getGameStatus() == GameStatus.SECOND_PLAYER_TURN && gameEntity.getSecondPlayer().equals(player));
    }

//    public Move createMove(CreateMoveDTO createMoveDTO) {
//        Move move = new Move();
//        move.setGameEntity(gameService.getGameEntity(createMoveDTO.getGameId()));
//        move.setPlayer(playerService.getPlayer());
//    }
}
