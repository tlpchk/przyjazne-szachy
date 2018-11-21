package com.ps.server.service;

import com.ps.server.Logic.Color;
import com.ps.server.entity.PlayerEntity;
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
        playerRepository.save(newPlayer);
        return newPlayer;
    }

    public PlayerEntity getPlayer(Long playerId) {
        return playerRepository.findById(playerId).orElse(null);
    }
}
