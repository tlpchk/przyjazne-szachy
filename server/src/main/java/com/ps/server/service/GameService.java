package com.ps.server.service;

import com.ps.server.domain.Game;
import com.ps.server.domain.Player;
import com.ps.server.enums.GameStatus;
import com.ps.server.repository.GameRepository;
import com.ps.server.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerService playerService;

    public Game createNewGame() {
        Game game = new Game();
        Player firstPlayer = playerService.createNewPlayer();
        game.setFirstPlayer(firstPlayer);
        game.setGameStatus(GameStatus.WAITING_FOR_PLAYER);
        game.setCreated(new Date());
        gameRepository.save(game);
        return game;
    }

    public List<Game> getGamesToJoin() {
        return gameRepository.findByGameStatus(GameStatus.WAITING_FOR_PLAYER);
    }

    public Game joinGame(Long gameId) {
        Game game = getGame(gameId);
        if (game != null) {
            Player secondPlayer = playerService.createNewPlayer();
            game.setSecondPlayer(secondPlayer);
            game.setGameStatus(GameStatus.FIRST_PLAYER_TURN);
            gameRepository.save(game);
        }
        return game;
    }

    public Game getGame(Long gameId){
        return gameRepository.findById(gameId).orElse(null);
    }
}
