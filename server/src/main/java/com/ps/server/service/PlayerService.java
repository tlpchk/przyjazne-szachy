package com.ps.server.service;

import com.ps.server.entity.UserEntity;
import com.ps.server.enums.PlayerType;
import com.ps.server.exception.InvalidRequiredArgumentException;
import com.ps.server.Logic.Color;
import com.ps.server.entity.PlayerEntity;
import com.ps.server.Logic.player.BotPlayer;
import com.ps.server.Logic.player.HumanPlayer;
import com.ps.server.Logic.player.Player;
import com.ps.server.repository.PlayerRepository;
import com.ps.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private UserRepository userRepository;

    public PlayerEntity createNewPlayer(String username, Color color, PlayerType playerType) {
        PlayerEntity newPlayer = new PlayerEntity();
        UserEntity userEntity = userRepository.findByUsername(username);
        newPlayer.setUser(userEntity);
        newPlayer.setColor(color);
        newPlayer.setPlayerType(playerType);
        playerRepository.save(newPlayer);
        return newPlayer;
    }

    /**
     * Returns Player with given {@param playerId}
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
     * @throws InvalidRequiredArgumentException when Player does not have playerType set
     */
    public Player createPlayerFromEntity(PlayerEntity playerEntity) throws InvalidRequiredArgumentException {
        Player player;
        switch (playerEntity.getPlayerType()) {
            case BOT:
                player = new BotPlayer(playerEntity.getColor());
                break;
            case HUMAN:
                player = new HumanPlayer(playerEntity.getColor());
                break;
            default:
                throw new InvalidRequiredArgumentException("playerType not set");
        }
        return player;
    }
}
