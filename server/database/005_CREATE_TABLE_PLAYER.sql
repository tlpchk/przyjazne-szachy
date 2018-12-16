USE `chess_game`;
CREATE TABLE IF NOT EXISTS `player` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `color` enum('WHITE','BLACK') COLLATE utf16_polish_ci NOT NULL,
  `player_type` enum('HUMAN','BOT') COLLATE utf16_polish_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

CREATE TABLE IF NOT EXISTS `player_user` (
  `ID_user` int(11) NOT NULL,
  `ID_player` int(11) NOT NULL,
  UNIQUE KEY `ID_player` (`ID_player`),
  KEY `FK_user_2` (`ID_user`),
  CONSTRAINT `FK_player_2` FOREIGN KEY (`ID_player`) REFERENCES `player` (`ID`),
  CONSTRAINT `FK_user_2` FOREIGN KEY (`ID_user`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;