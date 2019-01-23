package com.ps.server.service;

import com.ps.server.BotRunner;
import com.ps.server.Logic.Change;
import com.ps.server.Logic.Pieces.Piece;
import com.ps.server.Logic.Position;
import com.ps.server.Logic.game.Game;
import com.ps.server.Logic.game.GameCreator;
import com.ps.server.Logic.player.BotPlayer;
import com.ps.server.Logic.player.Player;
import com.ps.server.dto.GameInfoDTO;
import com.ps.server.dto.MoveResponseDTO;
import com.ps.server.dto.MoveUpdateDTO;
import com.ps.server.dto.PieceDTO;
import com.ps.server.entity.GameEntity;
import com.ps.server.entity.PlayerEntity;
import com.ps.server.enums.GameType;
import com.ps.server.enums.PlayerType;
import com.ps.server.enums.Result;
import com.ps.server.exception.*;
import com.ps.server.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private MoveService moveService;

    @Autowired
    private MatchService matchService;

    @Autowired
    private PlayerService playerservice;

    private GameCreator gameCreator = new GameCreator();

    private final HashMap<Long, Game> gamesMap = new HashMap<>();

    private HashMap<Long, List<MoveUpdateDTO>> updates = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(GameService.class);


    /**
     * Creates new Game instance and saves it in database.
     *
     * @param firstPlayerEntity  FirstPlayerEntity describes first Player in Game.
     * @param secondPlayerEntity SecondPlayerEntity describes second Player in Game.
     * @return Id of newly created Game.
     * @throws InvalidRequiredArgumentException when players do not have playerType set
     * @throws SamePlayerException              when firstPlayer is the same as secondPlayer (which means they have the same color)
     */
    public Long createNewGame(PlayerEntity firstPlayerEntity, PlayerEntity secondPlayerEntity, boolean isRanked) throws InvalidRequiredArgumentException, SamePlayerException {
        synchronized (gamesMap) {
            Game game = createGame(firstPlayerEntity, secondPlayerEntity);
            GameEntity gameEntity = createGameEntity(game, firstPlayerEntity, secondPlayerEntity);
            Long gameId = gameEntity.getId();
            updateGamesAfterCreation(game, gameId);
            logger.info("Game id: " + gameId + ". Game created.");
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

    private GameEntity createGameEntity(Game game, PlayerEntity firstPlayer, PlayerEntity secondPlayer) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setStartTime(new Timestamp(System.currentTimeMillis()));
        gameEntity.setFirstPlayer(firstPlayer);
        if (secondPlayer != null) {
            gameEntity.setSecondPlayer(secondPlayer);
            gameEntity.setGameType(GameType.BOT_GAME);
            game.setFirstPlayerTurnStartedDate(LocalDateTime.now());
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
    //TODO RS: break it into ran and unranked game
    public List<Long> getGamesToJoin() {
        synchronized (gamesMap) {
            logger.info("Returned list of games.");
            Map<Long, Game> mapOfGamesToJoin = gamesMap.entrySet().stream().filter(x -> x.getValue().getSecondPlayer() == null).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
            if (mapOfGamesToJoin.isEmpty()) {
                return Collections.emptyList();
            } else {
                return new LinkedList<>(mapOfGamesToJoin.keySet());
            }
        }
    }

    /**
     * Joins {@param secondPlayer} to the Game with {@param gameId}
     *
     * @param gameId             Id of the Game to join
     * @param secondPlayerEntity SecondPlayerEntity describes Player who wants to join the Game
     * @throws InvalidRequiredArgumentException when Player does not have playerType set
     * @throws CannotJoinPlayerException        when Game has already started (there are two players in Game)
     * @throws GameNotExistException            when Game with given Game id does not exist
     */
    public void joinGame(Long gameId, PlayerEntity secondPlayerEntity) throws InvalidRequiredArgumentException, CannotJoinPlayerException, GameNotExistException {
        synchronized (gamesMap) {
            Player secondPlayer = playerservice.createPlayerFromEntity(secondPlayerEntity);
            Game game = getGameFromGames(gameId);
            game.joinPlayer(secondPlayer);
            joinPlayerToGameEntity(gameId, secondPlayerEntity);
            game.setFirstPlayerTurnStartedDate(LocalDateTime.now());
            logger.info("Game id: " + gameId + ". Player " + secondPlayerEntity.getId() + " is joining game.");
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
        gameRepository.save(gameEntity);
    }

    /**
     * Returns GameEntity with given {@param gameId}
     *
     * @param gameId Id of the Game
     * @return GameEntity with given {@param gameId}, null if such a GameEntity does not exist
     */
    public GameEntity getGameEntity(Long gameId) {
        return gameRepository.findById(gameId).orElse(null);
    }


    /**
     * Makes given Move on board.
     *
     * @param gameId       Id of the Game in which Move has to be made.
     * @param playerEntity PlayerEntity describes Player who wants to make Move.
     * @param origin       Origin of the Move.
     * @param destination  Destination of the Move.
     * @return
     * @throws InvalidRequiredArgumentException when Player does not have playerType set
     * @throws NotPlayerTurnException           when {@param playerEntity} wants to make Move when it is not its turn
     * @throws GameNotExistException            when Game with given Game id does not exist
     */
    public MoveResponseDTO makeMove(Long gameId, PlayerEntity playerEntity, Position origin, Position destination) throws InvalidRequiredArgumentException, NotPlayerTurnException, GameNotExistException, GameHasFinishedException {
        synchronized (gamesMap) {
            Game game = getGameFromGames(gameId);
            Player player = playerservice.createPlayerFromEntity(playerEntity);
            logger.info("Game id: " + gameId + ". Player " + playerEntity.getId() + " is trying to move.");
            boolean isMoveValid = true;
            List<Change> listOfChanges;
            try {
                listOfChanges = game.makeMove(origin, destination, player);

                moveService.persistMove(getGameEntity(gameId), playerEntity, origin, destination);
                logger.info("Game id: " + gameId + ". Player " + playerEntity.getId() + " moves successfully from: " + origin + " to: " + destination);

            } catch (NotValidMoveException e) {
                isMoveValid = false;
                listOfChanges = Collections.emptyList();
                logger.info("Game id: " + gameId + ". Player " + playerEntity.getId() + " attempted to make invalid move from" + origin + " to: " + destination);
            }
            MoveResponseDTO moveDTO = new MoveResponseDTO(isMoveValid, listOfChanges);
            updateGamesAfterMove(gameId, moveDTO);
            return moveDTO;
        }
    }

    public void runBotIfRelevant(Long gameId) throws GameNotExistException {
        synchronized (gamesMap) {
            Game game = getGameFromGames(gameId);
            if (game.getSecondPlayer() instanceof BotPlayer) {
                Thread t = new Thread(new BotRunner(this,gameId));
                t.start();
            }
        }
    }


    public MoveResponseDTO promote(Long gameId, PlayerEntity playerEntity, Piece.PieceType pieceType) throws GameNotExistException, InvalidRequiredArgumentException {
        synchronized (gamesMap) {
            Game game = getGameFromGames(gameId);
            Player player = playerservice.createPlayerFromEntity(playerEntity);
            logger.info("Game id: " + gameId + ". Player " + playerEntity.getId() + " is trying to promote");
            boolean isMoveValid = true;
            List<Change> listOfChanges = game.promote(player, pieceType);
            MoveResponseDTO moveDTO = new MoveResponseDTO(isMoveValid, listOfChanges);
            updateGamesAfterMove(gameId, moveDTO);
            return moveDTO;
        }
    }

    public void makeMoveBot(Long gameId) throws GameNotExistException, NotPlayerTurnException, GameHasFinishedException {
        synchronized (gamesMap) {
            Game game = getGameFromGames(gameId);
            logger.info("Game id: " + gameId + ". Bot is trying to move.");
            boolean isMoveValid = true;
            List<Change> listOfChanges = game.makeMoveBot();
            moveService.persistMove(getGameEntity(gameId), null, game.getLastBotMove().loc, game.getLastBotMove().dest);
            MoveResponseDTO moveDTO = new MoveResponseDTO(isMoveValid, listOfChanges);
            updateGamesAfterMove(gameId, moveDTO);
            logger.info("Game id: " + gameId + ". Bot moves successfully.");
        }
    }

    private void updateGamesAfterMove(Long gameId, MoveResponseDTO moveDTO) throws GameNotExistException {
        List<MoveUpdateDTO> updateList = updates.get(gameId);
        MoveUpdateDTO lastUpdate = updateList.get(updateList.size() - 1);
        if (!lastUpdate.equals(moveDTO)) {
            Long newId = lastUpdate.getUpdateId() + 1;
            MoveUpdateDTO moveUpdateDTO = new MoveUpdateDTO(newId, moveDTO);
            updateList.add(moveUpdateDTO);
        }
        checkForFinishedTimer(gameId);
    }

    private void checkForFinishedTimer(Long gameId) throws GameNotExistException {
        synchronized (gamesMap) {
            Game game = getGameFromGames(gameId);
            game.checkForFinishedTimer();
            GameEntity gameEntity = getGameEntity(gameId);

            if (game.getResult() != null && !gameEntity.isFinished()) {
                saveFinishedGame(gameEntity, game.getResult());
            }
        }
    }

    /**
     * Gets board from Game.
     *
     * @param gameId Id of the Game.
     * @return List of PieceDTO describing Board.
     * @throws GameNotExistException when Game with given Game id does not exist
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
     * @param gameId   Id of the Game on which Piece is located.
     * @param position Postition on which Piece is located.
     * @return List of possible moves.
     */
    public List<Position> getPossibleMoves(Long gameId, Position position) {
        synchronized (gamesMap) {
            Game game = gamesMap.get(gameId);
            return game.getPossibleMovesForPosition(position);
        }
    }

    public MoveUpdateDTO getUpdate(Long gameId, Integer updateId) {
        List<MoveUpdateDTO> updateList = updates.get(gameId);
        return updateList.get(updateId);
    }

    public GameInfoDTO getGameInfo(Long gameId, PlayerEntity playerEntity) throws GameNotExistException, InvalidRequiredArgumentException {
        checkForFinishedTimer(gameId);
        synchronized (gamesMap) {
            List<MoveUpdateDTO> updateList = updates.get(gameId);
            Game game = getGameFromGames(gameId);
            GameEntity gameEntity = getGameEntity(gameId);
            Player player = playerservice.createPlayerFromEntity(playerEntity);

            GameInfoDTO gameInfo = new GameInfoDTO();
            gameInfo.setLastUpdateId(updateList.get(updateList.size() - 1).getUpdateId());
            gameInfo.setMyTurn(game.isPlayerTurn(player));
            gameInfo.setPromotion(game.isPromotion());

            gameInfo.setOpponent(getOpponent(gameEntity, playerEntity));
            gameInfo.setGameResult(game.getResult());
            if (gameInfo.getGameResult() != null && !gameEntity.isFinished()) {
                saveFinishedGame(gameEntity, gameInfo.getGameResult());
            }
            return gameInfo;
        }

    }

    private String getOpponent(GameEntity gameEntity, PlayerEntity playerEntity) {
        PlayerEntity opponent = (gameEntity.getFirstPlayer() == playerEntity) ? gameEntity.getSecondPlayer() : gameEntity.getFirstPlayer();
        String opponentUsername = "";
        if (opponent != null) {
            opponentUsername = opponent.getUser().getUsername();
        }
//        if (opponent != null && opponent.getPlayerType() == PlayerType.BOT) {
//            opponentUsername = "BOT";
//        } else if (opponent != null && opponent.getUser() != null) {
//            opponentUsername = opponent.getUser().getUsername();
//        }
        return opponentUsername;
    }


    public void saveFinishedGame(GameEntity gameEntity, Result result) {
        gameEntity.setFinished(true);
        gameEntity.setResult(result);
        matchService.saveResultForGameEntity(gameEntity, result);
        gameRepository.save(gameEntity);
    }


    public Long getTimer(Long gameId, PlayerEntity playerEntity) throws GameNotExistException, InvalidRequiredArgumentException {
        synchronized (gamesMap) {
            Game game = getGameFromGames(gameId);
            Player player = playerservice.createPlayerFromEntity(playerEntity);
            return game.getLeftTimeInSeconds(player);
        }
    }

    public List<GameEntity> getAllUnfinishedGamesForPlayer(PlayerEntity playerEntity) {
        Iterable<GameEntity> games = gameRepository.findAll();
        List<GameEntity> gamesToReturn = new LinkedList<>();
        for (GameEntity g : games) {
            if (!g.isFinished() && (g.getFirstPlayer().getId().equals(playerEntity.getId()) || g.getSecondPlayer().getId().equals(playerEntity.getId()))) {
                gamesToReturn.add(g);
            }
        }
        return gamesToReturn;
    }
}
