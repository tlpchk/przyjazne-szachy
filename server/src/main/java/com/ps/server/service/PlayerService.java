package com.ps.server.service;

import com.ps.server.enums.PlayerType;
import com.ps.server.exception.InvalidRequiredArgumentException;
import com.ps.server.logic.Color;
import com.ps.server.entity.PlayerEntity;
import com.ps.server.logic.player.BotPlayer;
import com.ps.server.logic.player.HumanPlayer;
import com.ps.server.logic.player.Player;
import com.ps.server.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public PlayerEntity createNewPlayer(Color color) {
        PlayerEntity newPlayer = new PlayerEntity();
        newPlayer.setColor(color);
        newPlayer.setPlayerType(PlayerType.HUMAN);
        playerRepository.save(newPlayer);
        return newPlayer;
    }

    /**
     * Returns player with given {@param playerId}
     *
     * @param playerId Id of Player.
     * @return Player with given Id if such a Player exists, null otherwise (includes case when playerId is null).
     */
    public PlayerEntity getPlayerEntity(Long playerId) {
        if (playerId != null) {
            return playerRepository.findById(playerId).orElse(null);
        }
        return null;
    }


    /**
     * Creates Player from PlayerEntity.
     *
     * @param playerEntity PlayerEntity describes Player who will be created.
     * @return Created Player.
     * @throws InvalidRequiredArgumentException when player does not have playerType set
     */
    public Player createPlayerFromEntity(PlayerEntity playerEntity) throws InvalidRequiredArgumentException {
        Player player;
        switch (playerEntity.getPlayerType()) {
            case BOT:
                player = new BotPlayer();
                break;
            case HUMAN:
                player = new HumanPlayer();
                break;
            default:
                throw new InvalidRequiredArgumentException();
        }
        return player.builder().color(playerEntity.getColor()).build();
    }
}
