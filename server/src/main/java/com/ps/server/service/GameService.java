package com.ps.server.service;

import com.ps.server.logic.*;
import com.ps.server.logic.pieces.Piece;
import com.ps.server.logic.Set;
import com.ps.server.dto.GameDTO;
import com.ps.server.dto.MoveResponseDTO;
import com.ps.server.logic.Game;
import com.ps.server.dto.MoveUpdateDTO;
import com.ps.server.dto.PieceDTO;
import com.ps.server.entity.GameEntity;
import com.ps.server.entity.PlayerEntity;
import com.ps.server.enums.GameStatus;
import com.ps.server.enums.GameType;
import com.ps.server.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    private HashMap<Long, Game> gamesMap = new HashMap<>();

    private HashMap<Long, MoveUpdateDTO> lastUpdate = new HashMap<>();

    /**
     * Creates new game with {@param firstPlayer} as host.
     *
     * @param firstPlayer Player who is the host
     * @param botPlayer   Bot Player in game. If set to null it means it's two human players game.
     * @return Newly created GameEntity
     */
    public GameEntity createNewGame(PlayerEntity firstPlayer, PlayerEntity botPlayer) {
        synchronized (gamesMap) {
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
    }

    private void createGame(PlayerEntity firstPlayer, PlayerEntity secondPlayer, GameEntity gameEntity) {
        Game newGame = new Game(firstPlayer, secondPlayer);
        Set whitePieceSet = new SetFactory.WhiteSetFactory().createSet();
        Set blackPieceSet = new SetFactory.BlackSetFactory().createSet();
        Board board = new Board(whitePieceSet, blackPieceSet);
        newGame.setBoard(board);
        lastUpdate.put(gameEntity.getId(), new MoveUpdateDTO());
        gamesMap.put(gameEntity.getId(), newGame);
    }

    /**
     * Returns list of games to join(games in status WAITING_FOR_SECOND_PLAYER)
     *
     * @return list of games to join
     */
    public List<GameDTO> getGamesToJoin() {
        synchronized (gamesMap) {
            List<GameEntity> listOfGames = gameRepository.findByGameStatus(GameStatus.WAITING_FOR_SECOND_PLAYER);
            List<GameDTO> listOfDTOS = new ArrayList<>();
            if (listOfGames.isEmpty()) {
                listOfDTOS = null;
            } else {
                for (GameEntity gameEntity : listOfGames) {
                    GameDTO gameDTO = new GameDTO(gameEntity.getId());
                    listOfDTOS.add(gameDTO);
                }
            }
            return listOfDTOS;
        }
    }

    /**
     * Joins {@param secondPlayer} to the game with {@param gameId}
     *
     * @param gameId       Id of the game to join
     * @param secondPlayer Player to join the game
     * @return true if game with {@param gameId} exists, false otherwise
     */
    public boolean joinGame(Long gameId, PlayerEntity secondPlayer) {
        synchronized (gamesMap) {
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
        synchronized (gamesMap) {
            Game game = gamesMap.get(gameId);
            List<Change> listOfChanges = null;
            Board board = game.getBoard();
            board.updateGame(playerEntity.getColor());
            Move move = board.validatePlayersMove(origin, destination, playerEntity.getColor());
            boolean isMoveValid = move != null;
            if (isMoveValid) {
                board.makeMove(move);
                listOfChanges = board.getListOfChanges(move);
                changeTurn(gameId);
            }
            MoveResponseDTO moveDTO = new MoveResponseDTO(isMoveValid, listOfChanges, "");
            Long newId = lastUpdate.get(gameId).getUpdateId() + 1;
            MoveUpdateDTO moveUpdateDTO = new MoveUpdateDTO(newId, moveDTO);
            lastUpdate.put(gameId, moveUpdateDTO);
            return moveDTO;
        }
    }

    private void changeTurn(Long gameId) {
        GameEntity gameEntity = gameRepository.findById(gameId).orElse(null);
        if (gameEntity != null) {
            if (gameEntity.getGameStatus() == GameStatus.FIRST_PLAYER_TURN) {
                gameEntity.setGameStatus(GameStatus.SECOND_PLAYER_TURN);
            } else if (gameEntity.getGameStatus() == GameStatus.SECOND_PLAYER_TURN) {
                gameEntity.setGameStatus(GameStatus.FIRST_PLAYER_TURN);
            }
        }
    }

    public List<PieceDTO> getBoard(Long gameId) {
        synchronized (gamesMap) {
            Game game = gamesMap.get(gameId);
            Board board = game.getBoard();
            return getPieceDTOList(board);
        }
    }

    private List<PieceDTO> getPieceDTOList(Board board) {
        Piece[][] pieces = board.getBoard();
        List<PieceDTO> pieceDTOList = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Piece piece = pieces[row][column];
                List<Position> possibleMoves = null;
                Piece.PieceType type = null;
                Color color = null;
                if (piece != null) {
                    piece.legalMoves();
                    List<Move> legalMoves = piece.getLegalMoves();
                    possibleMoves = getPossibleMoves(legalMoves);
                    type = piece.getType();
                    color = piece.color;
                }

                PieceDTO pieceDTO = new PieceDTO(row,
                        column,
                        type,
                        color,
                        possibleMoves);
                pieceDTOList.add(pieceDTO);
            }
        }
        return pieceDTOList;
    }

    private List<Position> getPossibleMoves(List<Move> legalMoves) {
        List<Position> possibleMoves = new ArrayList<>();
        if (legalMoves != null && !legalMoves.isEmpty()) {
            for (Move move : legalMoves) {
                possibleMoves.add(move.dest);
            }
        }
        return possibleMoves;
    }

    public MoveUpdateDTO getLastUpdate(Long gameId) {
        System.out.println("GET LAST UPDATE " + gameId);
        return lastUpdate.get(gameId);
    }

    public boolean isPlayerTurn(Long gameId, Long playerId) {
        GameEntity gameEntity = gameRepository.findById(gameId).orElse(null);
        if (gameEntity != null) {
            return (gameEntity.getGameStatus() == GameStatus.FIRST_PLAYER_TURN && gameEntity.getFirstPlayer().getId() == playerId)
                    || (gameEntity.getGameStatus() == GameStatus.SECOND_PLAYER_TURN && gameEntity.getSecondPlayer().getId() == playerId);
        }
        return false;
    }

    public List<Position> possibleMoves(Long gameId, Position position) {
        synchronized (gamesMap) {
            Game game = gamesMap.get(gameId);
            Board board = game.getBoard();
            Piece[][] pieces = board.getBoard();
            Piece piece = pieces[position.row][position.column];
            List<Position> possibleMoves = new LinkedList<>();
            if (piece != null) {
                piece.legalMoves();
                List<Move> legalMoves = piece.getLegalMoves();
                possibleMoves = getPossibleMoves(legalMoves);
            }
            return possibleMoves;
        }
    }
}
