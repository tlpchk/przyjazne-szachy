package com.ps.server.service;

import com.ps.server.entity.GameEntity;
import com.ps.server.entity.MatchEntity;
import com.ps.server.entity.UserEntity;
import com.ps.server.enums.Result;
import com.ps.server.repository.MatchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MatchService {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MatchesRepository matchesRepository;

    private final short WINNER_POINTS = 2;
    private final short DRAWER_POINTS = 1;
    private final short LOSER_POINTS = 0;

    public void saveResultForGameEntity(GameEntity gameEntity, Result result) {
        MatchEntity firstPlayerMatch = new MatchEntity();
        MatchEntity secondPlayerMatch = new MatchEntity();
        firstPlayerMatch.setGame(gameEntity);
        secondPlayerMatch.setGame(gameEntity);
        UserEntity firstUser = playerService.getUserForPlayer(gameEntity.getFirstPlayer());
        UserEntity secondUser = playerService.getUserForPlayer(gameEntity.getSecondPlayer());
        firstPlayerMatch.setPlayer(firstUser);
        firstPlayerMatch.setOpponent(secondUser);
        secondPlayerMatch.setPlayer(secondUser);
        secondPlayerMatch.setOpponent(firstUser);
        switch (result) {
            case FIRST_PLAYER_WON:
                firstPlayerMatch.setPlayerPoints(WINNER_POINTS);
                secondPlayerMatch.setPlayerPoints(LOSER_POINTS);
                break;
            case SECOND_PLAYER_WON:
                firstPlayerMatch.setPlayerPoints(LOSER_POINTS);
                secondPlayerMatch.setPlayerPoints(WINNER_POINTS);
                break;
            case DRAW:
                firstPlayerMatch.setPlayerPoints(DRAWER_POINTS);
                secondPlayerMatch.setPlayerPoints(DRAWER_POINTS);
                break;
            default:
                throw new IllegalArgumentException();
        }
        matchesRepository.save(firstPlayerMatch);
        matchesRepository.save(secondPlayerMatch);
    }
}
