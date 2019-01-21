USE `heroku_9fd898a328f4b3c`;

CREATE TABLE IF NOT EXISTS `game` (
  `id`              int(11)            AUTO_INCREMENT,
  `start_time`      timestamp NOT NULL DEFAULT current_timestamp(),
  `first_player`    int(11),
  `second_player`   int(11),
  `game_type`       enum ('COMPETITION_GAME', 'BOT_GAME') COLLATE utf16_polish_ci,
  `result`          enum ('FIRST_PLAYER_WON', 'SECOND_PLAYER_WON', 'DRAW') COLLATE utf16_polish_ci,
  `is_ranked`       bool               DEFAULT FALSE,
  `is_finished`     bool               DEFAULT FALSE,
  `move_time_limit` time               DEFAULT '00:05:00',
  `game_time_limit` time               DEFAULT '02:00:00',
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
  `id`       int(11)                              NOT NULL AUTO_INCREMENT,
  `nick`     varchar(64) COLLATE utf16_polish_ci  NOT NULL,
  `e_mail`   varchar(128) COLLATE utf16_polish_ci,
  `password` varchar(128) COLLATE utf16_polish_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf16
  COLLATE = utf16_polish_ci;


CREATE TABLE IF NOT EXISTS `player` (
  `id`          int(11)                                         NOT NULL AUTO_INCREMENT,
  `color`       enum ('WHITE', 'BLACK') COLLATE utf16_polish_ci NOT NULL,
  `player_type` enum ('HUMAN', 'BOT') COLLATE utf16_polish_ci   NOT NULL,
  `user_id`     int(11),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf16
  COLLATE = utf16_polish_ci;

CREATE TABLE IF NOT EXISTS `matches` (
  `id`            int(11)    NOT NULL AUTO_INCREMENT,
  `game_id`       int(11)    NOT NULL DEFAULT 0,
  `player_id`     int(11)    NOT NULL DEFAULT 0,
  `opponent_id`   int(11)    NOT NULL,
  `player_points` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `player_key` (`player_id`),
  KEY `opponent_key` (`opponent_id`),
  CONSTRAINT `opponent_key` FOREIGN KEY (`opponent_id`) REFERENCES `user` (`id`),
  CONSTRAINT `player_key` FOREIGN KEY (`player_id`) REFERENCES `user` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf16
  COLLATE = utf16_polish_ci;

CREATE TABLE IF NOT EXISTS `ranking` (
  `id`      int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `score`   double  NOT NULL DEFAULT 1000,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_ranking_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf16
  COLLATE = utf16_polish_ci;


CREATE VIEW `ranking_view` AS
  SELECT RANK() OVER (ORDER BY user.nick ASC) AS `position`, user.nick AS `nick` , ranking.score AS `score`
  FROM `user`,
       `ranking`
  WHERE user.id = ranking.user_id ORDER BY user.nick ASC;