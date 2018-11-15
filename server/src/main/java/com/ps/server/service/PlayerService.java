package com.ps.server.service;

import com.ps.server.domain.Player;
import com.ps.server.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player createNewPlayer() {
        Player newPlayer = new Player();
        playerRepository.save(newPlayer);
        return newPlayer;
    }

    public Player getPlayer(Long playerId) {
        return playerRepository.findById(playerId).orElse(null);
    }
}
