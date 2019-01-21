package com.ps.server;


import com.ps.server.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;


public class BotRunner implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(BotRunner.class);

    private GameService gameService;

    private Long gameId;

    public BotRunner(GameService gameService, Long gameId) {
        this.gameService = gameService;
        this.gameId = gameId;
    }

    @Override
    public void run() {
        try {
            gameService.makeMoveBot(gameId);
            gameService.calculateBotTime(gameId, LocalDateTime.now());
        } catch (Exception e) {
            logger.error("BOT ERROR", e);
        }
    }
}
