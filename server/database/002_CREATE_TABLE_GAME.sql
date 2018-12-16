USE `chess_game`;
CREATE TABLE IF NOT EXISTS `game` (
  `ID`              int(11)   NOT NULL AUTO_INCREMENT,
  `start_time`      timestamp NOT NULL DEFAULT current_timestamp(),
  `break_time`      timestamp,
  `first_player`    int(11),
  `second_player`   int(11),
  `game_type`       enum ('COMPETITION_GAME', 'BOT_GAME') COLLATE utf16_polish_ci,
  `result`          enum ('FIRST_PLAYER_WIN', 'SECOND_PLAYER_WIN', 'DRAW') COLLATE utf16_polish_ci,
  `move_time_limit` time               DEFAULT '00:05:00',
  `game_time_limit` time               DEFAULT '02:00:00',
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_player` FOREIGN KEY (`playerID_resumption`) REFERENCES `user` (`ID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf16
  COLLATE = utf16_polish_ci;