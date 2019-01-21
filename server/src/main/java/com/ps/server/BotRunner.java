package com.ps.server;


import com.ps.server.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;


public class BotRunner implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(BotRunner.class);

    @Autowired
    private GameService gameService;

    private Long gameId;

    public BotRunner(Long gameId) {
        this.gameId = gameId;
    }

    @Override
    public void run() {
        try {
            gameService.makeMoveBot(gameId);
        } catch (Exception e) {
            logger.error("BOT ERROR", e);
        }
    }
}
