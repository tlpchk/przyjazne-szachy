package com.ps.server.logic.game;

import com.ps.server.enums.GameType;
import com.ps.server.exception.InvalidRequiredArgumentException;
import com.ps.server.exception.SamePlayerException;
import com.ps.server.logic.Board;
import com.ps.server.logic.Set;
import com.ps.server.logic.SetFactory;
import com.ps.server.logic.player.BotPlayer;
import com.ps.server.logic.player.Player;

public class GameCreator {

    /**
     * Creates new game.
     *
     * @param firstPlayer  First player in game.
     * @param secondPlayer Second player in game. If set to null competition game is created. BotPlayer game is created otherwise.
     * @return Newly created Game.
     * @throws IllegalArgumentException when {@param firstPlayer} is null
     * @throws SamePlayerException      when firstPlayer is the same as secondPlayer (which means they have the same color)
     */
    public Game createGame(Player firstPlayer, Player secondPlayer) throws InvalidRequiredArgumentException, SamePlayerException {
        if (firstPlayer == null) {
            throw new InvalidRequiredArgumentException("firstPlayer is null");
        } else if (firstPlayer.equals(secondPlayer)) {
            throw new SamePlayerException();
        } else {
            Game game = new Game();
            game.setFirstPlayer(firstPlayer);
            createBoard(game);
            setGameParameters(game, secondPlayer);
            return game;
        }
    }

    private void createBoard(Game game) {
        Set whitePieceSet = new SetFactory.WhiteSetFactory().createSet();
        Set blackPieceSet = new SetFactory.BlackSetFactory().createSet();
        game.setBoard(new Board(whitePieceSet, blackPieceSet));
    }


    //TODO RS: Think twice if this should work like that.
    private void setGameParameters(Game game, Player secondPlayer) {
        if (secondPlayer == null) {
            setCompetitionGame(game);
        } else {
            setBotGame(game);
        }
    }

    private void setCompetitionGame(Game game) {
        game.setGameType(GameType.COMPETITION_GAME);
        game.setGameStatus(GameStatus.WAITING_FOR_SECOND_PLAYER);
        game.setSecondPlayer(null);
    }


    private void setBotGame(Game game) {
        game.setGameType(GameType.BOT_GAME);
        game.setGameStatus(GameStatus.FIRST_PLAYER_TURN);
        game.setSecondPlayer(new BotPlayer());
    }


}
