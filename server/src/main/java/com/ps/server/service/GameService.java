package com.ps.server.service;

import com.ps.server.dto.MoveResponseDTO;
import com.ps.server.dto.MoveUpdateDTO;
import com.ps.server.dto.PieceDTO;
import com.ps.server.entity.GameEntity;
import com.ps.server.entity.PlayerEntity;
import com.ps.server.enums.GameType;
import com.ps.server.exception.*;
import com.ps.server.Logic.*;
import com.ps.server.Logic.game.Game;
import com.ps.server.Logic.game.GameCreator;
import com.ps.server.Logic.player.Player;
import com.ps.server.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerService playerservice;

    private GameCreator gameCreator = new GameCreator();

    private HashMap<Long, Game> gamesMap = new HashMap<>();

    private HashMap<Long, List<MoveUpdateDTO>> updates = new HashMap<>();


    /**
     * Creates new Game instance and saves it in database.
     *
     * @param firstPlayerEntity  FirstPlayerEntity describes first player in game.
     * @param secondPlayerEntity SecondPlayerEntity describes second player in game.
     * @return Id of newly created game.
     * @throws InvalidRequiredArgumentException when players do not have playerType set
     * @throws SamePlayerException              when firstPlayer is the same as secondPlayer (which means they have the same color)
     */
    public Long createNewGame(PlayerEntity firstPlayerEntity, PlayerEntity secondPlayerEntity) throws InvalidRequiredArgumentException, SamePlayerException {
        synchronized (gamesMap) {
            Game game = createGame(firstPlayerEntity, secondPlayerEntity);
            GameEntity gameEntity = createGameEntity(firstPlayerEntity, secondPlayerEntity);
            Long gameId = gameEntity.getId();
            updateGamesAfterCreation(game, gameId);
            return gameId;
        }
    }

    private Game createGame(PlayerEntity firstPlayerEntity, PlayerEntity secondPlayerEntity) throws InvalidRequiredArgumentException, SamePlayerException {
        Player firstPlayer = playerservice.createPlayerFromEntity(firstPlayerEntity);
        Player secondPlayer = null;
        if (secondPlayerEntity != null) {
            secondPlayer = playerservice.createPlayerFromEntity(secondPlayerEntity);
        }
        return gameCreator.createGame(firstPlayer, secondPlayer);

    }

    private GameEntity createGameEntity(PlayerEntity firstPlayer, PlayerEntity secondPlayer) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setFirstPlayer(firstPlayer);
        if (secondPlayer != null) {
            gameEntity.setSecondPlayer(secondPlayer);
            gameEntity.setGameType(GameType.BOT_GAME);
        } else {
            gameEntity.setGameType(GameType.COMPETITION_GAME);
        }
        gameRepository.save(gameEntity);
        return gameEntity;
    }

    private void updateGamesAfterCreation(Game game, Long gameId) {
        gamesMap.put(gameId, game);
        updates.put(gameId, new ArrayList<>());
        updates.get(gameId).add(new MoveUpdateDTO());
    }


    /**
     * Returns list of ids of games to join(games where secondPlayer is null)
     *
     * @return list of games to join
     */
    public List<Long> getGamesToJoin() {
        synchronized (gamesMap) {
            Map<Long, Game> mapOfGamesToJoin = gamesMap.entrySet().stream().filter(x -> x.getValue().getSecondPlayer() == null).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
            if (mapOfGamesToJoin.isEmpty()) {
                return Collections.emptyList();
            } else {
                return new LinkedList<>(mapOfGamesToJoin.keySet());
            }
        }
    }

    /**
     * Joins {@param secondPlayer} to the game with {@param gameId}
     *
     * @param gameId             Id of the game to join
     * @param secondPlayerEntity SecondPlayerEntity describes Player who wants to join the game
     * @throws InvalidRequiredArgumentException when player does not have playerType set
     * @throws CannotJoinPlayerException        when game has already started (there are two players in game)
     * @throws GameNotExistException            when game with given game id does not exist
     */
    public void joinGame(Long gameId, PlayerEntity secondPlayerEntity) throws InvalidRequiredArgumentException, CannotJoinPlayerException, GameNotExistException {
        synchronized (gamesMap) {
            Player secondPlayer = playerservice.createPlayerFromEntity(secondPlayerEntity);
            Game game = getGameFromGames(gameId);
            game.joinPlayer(secondPlayer);
            joinPlayerToGameEntity(gameId, secondPlayerEntity);
        }
    }

    private Game getGameFromGames(Long gameId) throws GameNotExistException {
        Game game = gamesMap.get(gameId);
        if (game != null) {
            return game;
        } else {
            throw new GameNotExistException();
        }
    }

    private void joinPlayerToGameEntity(Long gameId, PlayerEntity secondPlayerEntity) {
        GameEntity gameEntity = getGameEntity(gameId);
        gameEntity.setSecondPlayer(secondPlayerEntity);
    }

    /**
     * Returns game with given {@param gameId}
     *
     * @param gameId Id of the game
     * @return game with given {@param gameId}, null if sucha a game does not exist
     */
    public GameEntity getGameEntity(Long gameId) {
        return gameRepository.findById(gameId).orElse(null);
    }


    /**
     * Makes given move on board.
     *
     * @param gameId       Id of the game in which move has to be made.
     * @param playerEntity PlayerEntity describes player who wants to make move.
     * @param origin       Origin of the move.
     * @param destination  Destination of the move.
     * @return
     * @throws InvalidRequiredArgumentException when player does not have playerType set
     * @throws NotPlayerTurnException           when {@param playerEntity} wants to make move when it is not its turn
     * @throws GameNotExistException            when game with given game id does not exist
     */
    public MoveResponseDTO makeMove(Long gameId, PlayerEntity playerEntity, Position origin, Position destination) throws InvalidRequiredArgumentException, NotPlayerTurnException, GameNotExistException {
        synchronized (gamesMap) {
            Game game = getGameFromGames(gameId);
            Player player = playerservice.createPlayerFromEntity(playerEntity);
            boolean isMoveValid = true;
            List<Change> listOfChanges;
            try {
                listOfChanges = game.makeMove(origin, destination, player);
            } catch (NotValidMoveException e) {
                isMoveValid = false;
                listOfChanges = Collections.emptyList();
            }
            MoveResponseDTO moveDTO = new MoveResponseDTO(isMoveValid, listOfChanges);
            updateGamesAfterMove(gameId, moveDTO);
            return moveDTO;
        }
    }

    public MoveResponseDTO makeMoveBot(Long gameId) throws GameNotExistException, NotPlayerTurnException {
        synchronized (gamesMap) {
            Game game = getGameFromGames(gameId);
            boolean isMoveValid = true;
            List<Change> listOfChanges = game.makeMoveBot();
            MoveResponseDTO moveDTO = new MoveResponseDTO(isMoveValid, listOfChanges);
            updateGamesAfterMove(gameId, moveDTO);
            return  moveDTO;
        }
    }

    private void updateGamesAfterMove(Long gameId, MoveResponseDTO moveDTO) {
        List<MoveUpdateDTO> updateList = updates.get(gameId);
        MoveUpdateDTO lastUpdate = updateList.get(updateList.size() - 1);
        if ( !lastUpdate.equals(moveDTO)){
            Long newId = lastUpdate.getUpdateId() + 1;
            System.out.println("UPDATE: " + newId);
            MoveUpdateDTO moveUpdateDTO = new MoveUpdateDTO(newId, moveDTO);
            updateList.add(moveUpdateDTO);
        }
    }

    /**
     * Gets board from game.
     *
     * @param gameId Id of the game.
     * @return List of PieceDTO describing Board.
     * @throws GameNotExistException when game with given game id does not exist
     */
    public List<PieceDTO> getBoard(Long gameId) throws GameNotExistException {
        synchronized (gamesMap) {
            Game game = getGameFromGames(gameId);
            return game.getPieceDTOList();
        }
    }

    /**
     * Return possible moves for Piece located on Position.
     *
     * @param gameId   Id of the game on which Piece is located.
     * @param position Postition on which Piece is located.
     * @return List of possible moves.
     */
    public List<Position> getPossibleMoves(Long gameId, Position position) {
        synchronized (gamesMap) {
            Game game = gamesMap.get(gameId);
            return game.getPossibleMovesForPosition(position);
        }
    }

    public MoveUpdateDTO getLastUpdate(Long gameId) {
        List<MoveUpdateDTO> updateList = updates.get(gameId);
        return updateList.get(updateList.size() - 1);
    }

//    public MoveUpdateDTO getUpdateById(Long gameId, Long updateId) {
//        updates.get(gameId)
//    }

}
