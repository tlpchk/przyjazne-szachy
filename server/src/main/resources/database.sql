DROP DATABASE `chess_game_app`;
CREATE DATABASE IF NOT EXISTS `chess_game_app`;
USE `chess_game_app`;

CREATE TABLE IF NOT EXISTS `game` (
  `id`            bigint(11)         AUTO_INCREMENT,
  `start_time`    timestamp NOT NULL DEFAULT current_timestamp(),
  `first_player`  bigint(11),
  `second_player` bigint(11),
  `game_type`     enum ('COMPETITION_GAME', 'BOT_GAME') COLLATE utf16_polish_ci,
  `result`        enum ('FIRST_PLAYER_WON', 'SECOND_PLAYER_WON', 'DRAW') COLLATE utf16_polish_ci,
  `is_ranked`     bool               DEFAULT FALSE,
  `is_finished`   bool               DEFAULT FALSE,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf16
  COLLATE = utf16_polish_ci;

CREATE TABLE IF NOT EXISTS `move` (
  `id`                 bigint(20) NOT NULL AUTO_INCREMENT,
  `created`            timestamp  NOT NULL DEFAULT current_timestamp(),
  `origin_column`      int(11)    NOT NULL,
  `origin_row`         int(11)    NOT NULL,
  `destination_column` int(11)    NOT NULL,
  `destination_row`    int(11)    NOT NULL,
  `game_id`            bigint(20) NOT NULL,
  `player_id`          bigint(20)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_move_game_game_id` (`game_id`),
  KEY `FK_move_player_player_id` (`player_id`)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = utf16
  COLLATE = utf16_polish_ci;

CREATE TABLE IF NOT EXISTS `user` (
  `id`                 int(11)                              NOT NULL AUTO_INCREMENT,
  `nick`               varchar(64) COLLATE utf16_polish_ci  NOT NULL,
  `e_mail`             varchar(128) COLLATE utf16_polish_ci,
  `password`           varchar(128) COLLATE utf16_polish_ci NOT NULL DEFAULT '0',
  `is_active`          bool                                          DEFAULT FALSE,
  `verification_token` VARCHAR(255),
  `reset_token`        VARCHAR(255),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf16
  COLLATE = utf16_polish_ci;


CREATE TABLE IF NOT EXISTS `player` (
  `id`          bigint(11)                                      NOT NULL AUTO_INCREMENT,
  `color`       enum ('WHITE', 'BLACK') COLLATE utf16_polish_ci NOT NULL,
  `player_type` enum ('HUMAN', 'BOT') COLLATE utf16_polish_ci   NOT NULL,
  `user_id`     int(11),
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf16
  COLLATE = utf16_polish_ci;

CREATE TABLE IF NOT EXISTS `matches` (
  `id`            bigint(11)  NOT NULL AUTO_INCREMENT,
  `game_id`       bigint(11)  NOT NULL,
  `player_id`     int(11),
  `opponent_id`   int(11),
  `player_points` smallint(4) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf16
  COLLATE = utf16_polish_ci;

CREATE TABLE IF NOT EXISTS `ranking` (
  `id`      int(11)    NOT NULL AUTO_INCREMENT,
  `user_id` int(11)    NOT NULL,
  `won`     bigint(11) NOT NULL DEFAULT 0,
  `lost`    bigint(11) NOT NULL DEFAULT 0,
  `draw`    bigint(11) NOT NULL DEFAULT 0,
  `score`   double     NOT NULL DEFAULT 1000,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_ranking_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf16
  COLLATE = utf16_polish_ci;


CREATE VIEW `ranking_view` AS
  SELECT RANK() OVER(ORDER BY ranking.score DESC) AS `position`,
         user.nick                                AS `nick`,
         ranking.won                              AS `won`,
         ranking.lost                             AS `lost`,
         ranking.draw                             AS `draw`,
         ranking.score                            AS `score`
  FROM `user`,
       `ranking`
  WHERE user.id = ranking.user_id
    AND user.id NOT IN (SELECT user.id FROM `user` WHERE user.nick = "bot"
                                                      OR user.nick = "noname")
  ORDER BY ranking.score ASC;

DELIMITER //
CREATE TRIGGER `TR_user_after_insert`
  AFTER INSERT
  ON `user`
  FOR EACH ROW
  BEGIN
    INSERT INTO `ranking` (`user_id`) VALUES (new.id);
  END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER `TR_game_after_update`
  AFTER INSERT
  ON `matches`
  FOR EACH ROW
  BEGIN

    declare isRanked boolean;
    set isRanked = (SELECT game.is_ranked FROM game WHERE game.id = new.game_id);
    IF (isRanked = TRUE)
    THEN
      BEGIN
        declare current float;
        declare pnkGet float;
        declare pnkEX float;
        declare result float;
        declare won int;
        declare lost int;
        declare draw int;

        set current = (SELECT ranking.score FROM ranking WHERE ranking.user_id = new.player_id);
        set pnkGet = (SELECT matches.player_points FROM matches WHERE matches.id = new.id);
        set pnkEX = ((SELECT ranking.score FROM ranking WHERE ranking.user_id = new.player_id) /
                     (SELECT ranking.score FROM ranking WHERE ranking.user_id = new.opponent_id));

        set won = (select count(id)
                   from (SELECT *
                         from chess_game_app.matches
                         WHERE matches.player_id = 5
                           AND matches.player_points = 2
                         group by game_id, player_id) as won);
        set draw = (select count(id)
                    from (SELECT *
                          from chess_game_app.matches
                          WHERE matches.player_id = 5
                            AND matches.player_points = 1
                          group by game_id, player_id) as won);
        set lost = (select count(id)
                    from (SELECT *
                          from chess_game_app.matches
                          WHERE matches.player_id = 5
                            AND matches.player_points = 0
                          group by game_id, player_id) as won);
        IF (pnkEX > 1.75)
        THEN
          SET pnkEX = 1.75;
        ELSEIF (pnkEX < 0.25)
          THEN
            SET pnkEX = 0.25;
        END IF;
        set result = current + 10 * (pnkGet - pnkEX);

        IF (result < 0.0)
        THEN
          set result = 0.0;
        END IF;

        UPDATE ranking
        set ranking.score = result,
            ranking.won   = won,
            ranking.lost  = lost,
            ranking.draw  = draw
        WHERE ranking.user_id = new.player_id;
      END;
    END IF;


  END//
DELIMITER ;

# password: noname
INSERT INTO `user` (`nick`, `password`, `is_active`)
VALUES ("noname", "$2a$10$rEPSup5zQg2RdJ32hn6Ud.bKTghrKrbYZ4zmBRB1sY8oiXiYsECFy", TRUE);

# password: bot
INSERT INTO `user` (`nick`, `password`, `is_active`)
VALUES ("bot", "$2a$10$0Y0mJx7gIx6/2Cyc4TkUne6T8.S1cHD3BcGMICsecvr2rqA6OMjv2", TRUE);