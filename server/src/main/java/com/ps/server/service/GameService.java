package com.ps.server.service;

import com.ps.server.Logic.SetFactory;
import com.ps.server.entity.GameEntity;
import com.ps.server.entity.PlayerEntity;
import com.ps.server.enums.GameStatus;
import com.ps.server.repository.GameRepository;
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
    private PieceService pieceService;

    public GameEntity createNewGame(PlayerEntity firstPlayer) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setFirstPlayer(firstPlayer);
        gameEntity.setGameStatus(GameStatus.WAITING_FOR_PLAYER);
        gameEntity.setCreated(new Date());
        gameRepository.save(gameEntity);
        pieceService.createWhiteSetEntity(gameEntity);
        pieceService.createBlackSetEntity(gameEntity);
        return gameEntity;
    }

    public List<GameEntity> getGamesToJoin() {
        return gameRepository.findByGameStatus(GameStatus.WAITING_FOR_PLAYER);
    }

    public GameEntity joinGame(Long gameId, PlayerEntity secondPlayer) {
        GameEntity gameEntity = getGame(gameId);
        if (gameEntity != null) {
            gameEntity.setSecondPlayer(secondPlayer);
            gameEntity.setGameStatus(GameStatus.FIRST_PLAYER_TURN);
            gameRepository.save(gameEntity);
        }
        return gameEntity;
    }

    public GameEntity getGame(Long gameId){
        return gameRepository.findById(gameId).orElse(null);
    }
}
