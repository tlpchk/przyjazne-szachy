package com.ps.server.service;

import com.ps.server.domain.Game;
import com.ps.server.domain.Move;
import com.ps.server.domain.Player;
import com.ps.server.dto.CreateMoveDTO;
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

    public boolean isPlayerTurn(Game game, Player player) {
        return (game.getGameStatus() == GameStatus.FIRST_PLAYER_TURN && game.getFirstPlayer().equals(player))
                || (game.getGameStatus() == GameStatus.SECOND_PLAYER_TURN && game.getSecondPlayer().equals(player));
    }

//    public Move createMove(CreateMoveDTO createMoveDTO) {
//        Move move = new Move();
//        move.setGame(gameService.getGame(createMoveDTO.getGameId()));
//        move.setPlayer(playerService.getPlayer());
//    }
}
