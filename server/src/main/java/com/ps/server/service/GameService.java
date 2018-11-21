package com.ps.server.service;

import com.ps.server.Logic.*;
import com.ps.server.dto.MoveResponseDTO;
import com.ps.server.Logic.Game;
import com.ps.server.entity.GameEntity;
import com.ps.server.entity.PlayerEntity;
import com.ps.server.enums.GameStatus;
import com.ps.server.enums.GameType;
import com.ps.server.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    private HashMap<Long, Game> gamesMap = new HashMap<>();

    /**
     * Creates new game with {@param firstPlayer} as host.
     *
     * @param firstPlayer Player who is the host
     * @param botPlayer   Bot Player in game. If set to null it means it's two human players game.
     * @return Newly created GameEntity
     */
    public GameEntity createNewGame(PlayerEntity firstPlayer, PlayerEntity botPlayer) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setFirstPlayer(firstPlayer);
        if (botPlayer != null) {
            gameEntity.setSecondPlayer(botPlayer);
            gameEntity.setGameType(GameType.BOT_GAME);
            gameEntity.setGameStatus(GameStatus.FIRST_PLAYER_TURN);
        } else {
            gameEntity.setGameType(GameType.COMPETITION_GAME);
            gameEntity.setGameStatus(GameStatus.WAITING_FOR_SECOND_PLAYER);
        }
        gameRepository.save(gameEntity);
        createGame(firstPlayer, botPlayer, gameEntity);
        return gameEntity;
    }

    private void createGame(PlayerEntity firstPlayer, PlayerEntity secondPlayer, GameEntity gameEntity) {
        Game newGame = new Game(firstPlayer, secondPlayer);
        Set whitePieceSet = new SetFactory.WhiteSetFactory().createSet();
        Set blackPieceSet = new SetFactory.BlackSetFactory().createSet();
        Board board = new Board(whitePieceSet, blackPieceSet);
        newGame.setBoard(board);
        gamesMap.put(gameEntity.getId(), newGame);
    }

    /**
     * Returns list of games to join(games in status WAITING_FOR_SECOND_PLAYER)
     *
     * @return list of games to join
     */
    public List<GameEntity> getGamesToJoin() {
        List<GameEntity> listOfGames = gameRepository.findByGameStatus(GameStatus.WAITING_FOR_SECOND_PLAYER);
        return (listOfGames.isEmpty()) ? null : listOfGames;
    }

    /**
     * Joins {@param secondPlayer} to the game with {@param gameId}
     *
     * @param gameId       Id of the game to join
     * @param secondPlayer Player to join the game
     * @return true if game with {@param gameId} exists, false otherwise
     */
    public boolean joinGame(Long gameId, PlayerEntity secondPlayer) {
        GameEntity gameEntity = getGameEntity(gameId);
        boolean isGameValid = (gameEntity != null);
        if (isGameValid) {
            gameEntity.setSecondPlayer(secondPlayer);
            gameEntity.setGameStatus(GameStatus.FIRST_PLAYER_TURN);
            gameRepository.save(gameEntity);
            Game gameToJoin = gamesMap.get(gameId);
            gameToJoin.setSecondPlayer(secondPlayer);
        }
        return isGameValid;
    }

    /**
     * Returns game with given {@param gameId}
     *
     * @param gameId id of the game
     * @return game with given {@param gameId}, null if sucha a game does not exist
     */
    public GameEntity getGameEntity(Long gameId) {
        return gameRepository.findById(gameId).orElse(null);
    }

    /**
     * @param gameId
     * @param playerEntity
     * @param origin
     * @param destination
     * @return
     */
    public MoveResponseDTO makeMove(Long gameId, PlayerEntity playerEntity, Position origin, Position destination) {
        Game game = gamesMap.get(gameId);
        List<Change> listOfChanges = null;
        Board board = game.getBoard();
        board.updateGame(playerEntity.getColor());
        Move move = board.validatePlayersMove(origin, destination, playerEntity.getColor());
        boolean isMoveValid = move != null;
        if (isMoveValid) {
            listOfChanges = board.getListOfChanges(move);
        }
        return new MoveResponseDTO(isMoveValid, listOfChanges);
    }

}
