-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Wersja serwera:               10.2.9-MariaDB - mariadb.org binary distribution
-- Serwer OS:                    Win64
-- HeidiSQL Wersja:              9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Zrzut struktury bazy danych chess_game
CREATE DATABASE IF NOT EXISTS `chess_game` /*!40100 DEFAULT CHARACTER SET utf16 COLLATE utf16_polish_ci */;
USE `chess_game`;

-- Zrzut struktury procedura chess_game.add_player
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_player`(
	IN `nick1` VARCHAR(64),
	IN `e-mail1` VARCHAR(128),
	IN `password1` VARCHAR(128)


)
BEGIN
	INSERT INTO user(user.nick,user.`e-mail`,user.`password`) VALUES (`nick1`, `e-mail1`, `password1`);
END//
DELIMITER ;

-- Zrzut struktury tabela chess_game.game
CREATE TABLE IF NOT EXISTS `game` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `break_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `playerID_resumption` int(11) NOT NULL,
  `player_1` int(11) NOT NULL,
  `player_2` int(11) NOT NULL,
  `result` enum('Win_1','Win_2','Draw') COLLATE utf16_polish_ci NOT NULL DEFAULT 'Draw',
  `move_limit` time NOT NULL DEFAULT '00:05:00',
  `game_limit` time NOT NULL DEFAULT '02:00:00',
  PRIMARY KEY (`ID`),
  KEY `FK_player` (`playerID_resumption`),
  CONSTRAINT `FK_player` FOREIGN KEY (`playerID_resumption`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.game: ~0 rows (około)
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
/*!40000 ALTER TABLE `game` ENABLE KEYS */;

-- Zrzut struktury tabela chess_game.hibernate_sequence_ok
CREATE TABLE IF NOT EXISTS `hibernate_sequence_ok` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `next_val` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.hibernate_sequence_ok: 3 rows
/*!40000 ALTER TABLE `hibernate_sequence_ok` DISABLE KEYS */;
REPLACE INTO `hibernate_sequence_ok` (`ID`, `next_val`) VALUES
	(1, 1),
	(2, 1),
	(3, 1);
/*!40000 ALTER TABLE `hibernate_sequence_ok` ENABLE KEYS */;

-- Zrzut struktury procedura chess_game.login
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `login`(
	IN `log1` VARCHAR(128),
	IN `pass1` VARCHAR(128)

)
BEGIN
	if (SELECT player.ID FROM player WHERE player.`e-mail`=log1 AND player.`password`=pass1) IS NULL then	
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Blad! Błędny login lub hasło!";
	end if;
END//
DELIMITER ;

-- Zrzut struktury tabela chess_game.matches
CREATE TABLE IF NOT EXISTS `matches` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `gameID` int(11) NOT NULL DEFAULT 0,
  `playerID` int(11) NOT NULL DEFAULT 0,
  `opponentID` int(11) NOT NULL,
  `result` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`ID`),
  KEY `player_key` (`playerID`),
  KEY `opponent_key` (`opponentID`),
  CONSTRAINT `opponent_key` FOREIGN KEY (`opponentID`) REFERENCES `player` (`ID`),
  CONSTRAINT `player_key` FOREIGN KEY (`playerID`) REFERENCES `player` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.matches: ~2 rows (około)
/*!40000 ALTER TABLE `matches` DISABLE KEYS */;
REPLACE INTO `matches` (`ID`, `gameID`, `playerID`, `opponentID`, `result`) VALUES
	(1, 1, 1, 2, 2),
	(2, 1, 2, 1, 0);
/*!40000 ALTER TABLE `matches` ENABLE KEYS */;

-- Zrzut struktury tabela chess_game.move
CREATE TABLE IF NOT EXISTS `move` (
  `id` bigint(20) NOT NULL,
  `created` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `destination_column` int(11) NOT NULL,
  `destination_row` int(11) NOT NULL,
  `origin_column` int(11) NOT NULL,
  `origin_row` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK10000` (`game_id`),
  KEY `FK200000` (`player_id`),
  CONSTRAINT `FK10000` FOREIGN KEY (`game_id`) REFERENCES `game` (`ID`),
  CONSTRAINT `FK200000` FOREIGN KEY (`player_id`) REFERENCES `player` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.move: ~0 rows (około)
/*!40000 ALTER TABLE `move` DISABLE KEYS */;
/*!40000 ALTER TABLE `move` ENABLE KEYS */;

-- Zrzut struktury tabela chess_game.piece
CREATE TABLE IF NOT EXISTS `piece` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` enum('Pawn','Rook','Bishop','Knight','Queen','King','Free') COLLATE utf16_polish_ci NOT NULL,
  `color` enum('White','Black') COLLATE utf16_polish_ci NOT NULL,
  `position_X` int(11) NOT NULL DEFAULT 0,
  `position_Y` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.piece: ~32 rows (około)
/*!40000 ALTER TABLE `piece` DISABLE KEYS */;
REPLACE INTO `piece` (`ID`, `name`, `color`, `position_X`, `position_Y`) VALUES
	(1, 'Rook', 'Black', 1, 1),
	(2, 'Knight', 'Black', 2, 1),
	(3, 'Bishop', 'Black', 3, 1),
	(4, 'Queen', 'Black', 4, 1),
	(5, 'King', 'Black', 5, 1),
	(6, 'Bishop', 'Black', 6, 1),
	(7, 'Knight', 'Black', 7, 1),
	(8, 'Rook', 'Black', 8, 1),
	(9, 'Pawn', 'Black', 1, 2),
	(10, 'Pawn', 'Black', 2, 2),
	(11, 'Pawn', 'Black', 3, 2),
	(12, 'Pawn', 'Black', 4, 2),
	(13, 'Pawn', 'Black', 5, 2),
	(14, 'Pawn', 'Black', 6, 2),
	(15, 'Pawn', 'Black', 7, 2),
	(16, 'Pawn', 'Black', 8, 2),
	(17, 'Pawn', 'White', 1, 7),
	(18, 'Pawn', 'White', 2, 7),
	(19, 'Pawn', 'White', 3, 7),
	(20, 'Pawn', 'White', 4, 7),
	(21, 'Pawn', 'White', 5, 7),
	(22, 'Pawn', 'White', 6, 7),
	(23, 'Pawn', 'White', 7, 7),
	(24, 'Pawn', 'White', 8, 7),
	(25, 'Rook', 'White', 1, 8),
	(26, 'Knight', 'White', 2, 8),
	(27, 'Bishop', 'White', 3, 8),
	(28, 'King', 'White', 4, 8),
	(29, 'Queen', 'White', 5, 8),
	(30, 'Bishop', 'White', 6, 8),
	(31, 'Knight', 'White', 7, 8),
	(32, 'Rook', 'White', 8, 8);
/*!40000 ALTER TABLE `piece` ENABLE KEYS */;

-- Zrzut struktury tabela chess_game.player
CREATE TABLE IF NOT EXISTS `player` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `color` enum('White','Black') COLLATE utf16_polish_ci NOT NULL,
  `player_type` enum('Player','Bot') COLLATE utf16_polish_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.player: ~2 rows (około)
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
REPLACE INTO `player` (`ID`, `color`, `player_type`) VALUES
	(1, 'Black', 'Player'),
	(2, 'White', 'Player');
/*!40000 ALTER TABLE `player` ENABLE KEYS */;

-- Zrzut struktury tabela chess_game.player_user
CREATE TABLE IF NOT EXISTS `player_user` (
  `ID_user` int(11) NOT NULL,
  `ID_player` int(11) NOT NULL,
  UNIQUE KEY `ID_player` (`ID_player`),
  KEY `FK_user_2` (`ID_user`),
  CONSTRAINT `FK_player_2` FOREIGN KEY (`ID_player`) REFERENCES `player` (`ID`),
  CONSTRAINT `FK_user_2` FOREIGN KEY (`ID_user`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.player_user: ~2 rows (około)
/*!40000 ALTER TABLE `player_user` DISABLE KEYS */;
REPLACE INTO `player_user` (`ID_user`, `ID_player`) VALUES
	(1, 1),
	(2, 2);
/*!40000 ALTER TABLE `player_user` ENABLE KEYS */;

-- Zrzut struktury tabela chess_game.ranking
CREATE TABLE IF NOT EXISTS `ranking` (
  `ID_User` int(11) NOT NULL,
  `Score` float NOT NULL DEFAULT 1000,
  PRIMARY KEY (`ID_User`),
  CONSTRAINT `FK_user` FOREIGN KEY (`ID_User`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.ranking: ~3 rows (około)
/*!40000 ALTER TABLE `ranking` DISABLE KEYS */;
REPLACE INTO `ranking` (`ID_User`, `Score`) VALUES
	(1, 1010),
	(2, 990.099),
	(3, 1000);
/*!40000 ALTER TABLE `ranking` ENABLE KEYS */;

-- Zrzut struktury procedura chess_game.register_controll
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `register_controll`(
	IN `log1` VARCHAR(128)

,
	IN `pass1` VARCHAR(128)
)
BEGIN
	declare i int;
	set i=1;
	if (SELECT COUNT(player.ID) FROM player WHERE player.`e-mail`=log1>0.01) then
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Odmowa! Podany e-mail już istnieje!";
	end if;
	if (length(pass1)<10 OR length(pass1)>127 
	OR (SELECT pass1 REGEXP '^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{10,}$')<1) then
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Odmowa! Hasło nie spełnia wymogów formalnych!";	
	end if;

END//
DELIMITER ;

-- Zrzut struktury tabela chess_game.resumption_place_black_x
CREATE TABLE IF NOT EXISTS `resumption_place_black_x` (
  `Game_ID` int(11) NOT NULL,
  `rook_1_1` int(11) NOT NULL,
  `knight_2_1` int(11) NOT NULL,
  `bishop_3_1` int(11) NOT NULL,
  `king_4_1` int(11) NOT NULL,
  `queen_5_1` int(11) NOT NULL,
  `bishop_6_1` int(11) NOT NULL,
  `knight_7_1` int(11) NOT NULL,
  `rook_8_1` int(11) NOT NULL,
  `pawn_1_2` int(11) NOT NULL,
  `pawn_2_2` int(11) NOT NULL,
  `pawn_3_2` int(11) NOT NULL,
  `pawn_4_2` int(11) NOT NULL,
  `pawn_5_2` int(11) NOT NULL,
  `pawn_6_2` int(11) NOT NULL,
  `pawn_7_2` int(11) NOT NULL,
  `pawn_8_2` int(11) NOT NULL,
  UNIQUE KEY `Game_ID` (`Game_ID`),
  CONSTRAINT `FK_game_play` FOREIGN KEY (`Game_ID`) REFERENCES `game` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.resumption_place_black_x: ~0 rows (około)
/*!40000 ALTER TABLE `resumption_place_black_x` DISABLE KEYS */;
/*!40000 ALTER TABLE `resumption_place_black_x` ENABLE KEYS */;

-- Zrzut struktury tabela chess_game.resumption_place_black_y
CREATE TABLE IF NOT EXISTS `resumption_place_black_y` (
  `Game_ID` int(11) NOT NULL,
  `rook_1_1` int(11) NOT NULL,
  `knight_2_1` int(11) NOT NULL,
  `bishop_3_1` int(11) NOT NULL,
  `king_4_1` int(11) NOT NULL,
  `queen_5_1` int(11) NOT NULL,
  `bishop_6_1` int(11) NOT NULL,
  `knight_7_1` int(11) NOT NULL,
  `rook_8_1` int(11) NOT NULL,
  `pawn_1_2` int(11) NOT NULL,
  `pawn_2_2` int(11) NOT NULL,
  `pawn_3_2` int(11) NOT NULL,
  `pawn_4_2` int(11) NOT NULL,
  `pawn_5_2` int(11) NOT NULL,
  `pawn_6_2` int(11) NOT NULL,
  `pawn_7_2` int(11) NOT NULL,
  `pawn_8_2` int(11) NOT NULL,
  UNIQUE KEY `Game_ID_Y` (`Game_ID`),
  CONSTRAINT `FK_game_play_Y` FOREIGN KEY (`Game_ID`) REFERENCES `game` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.resumption_place_black_y: ~0 rows (około)
/*!40000 ALTER TABLE `resumption_place_black_y` DISABLE KEYS */;
/*!40000 ALTER TABLE `resumption_place_black_y` ENABLE KEYS */;

-- Zrzut struktury tabela chess_game.resumption_place_white_x
CREATE TABLE IF NOT EXISTS `resumption_place_white_x` (
  `Game_ID` int(11) NOT NULL,
  `rook_1_8` int(11) NOT NULL,
  `knight_2_8` int(11) NOT NULL,
  `bishop_3_8` int(11) NOT NULL,
  `king_4_8` int(11) NOT NULL,
  `queen_5_8` int(11) NOT NULL,
  `bishop_6_8` int(11) NOT NULL,
  `knight_7_8` int(11) NOT NULL,
  `rook_8_8` int(11) NOT NULL,
  `pawn_1_7` int(11) NOT NULL,
  `pawn_2_7` int(11) NOT NULL,
  `pawn_3_7` int(11) NOT NULL,
  `pawn_4_7` int(11) NOT NULL,
  `pawn_5_7` int(11) NOT NULL,
  `pawn_6_7` int(11) NOT NULL,
  `pawn_7_7` int(11) NOT NULL,
  `pawn_8_7` int(11) NOT NULL,
  UNIQUE KEY `Game_ID` (`Game_ID`),
  CONSTRAINT `FK_game_play2` FOREIGN KEY (`Game_ID`) REFERENCES `game` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.resumption_place_white_x: ~0 rows (około)
/*!40000 ALTER TABLE `resumption_place_white_x` DISABLE KEYS */;
/*!40000 ALTER TABLE `resumption_place_white_x` ENABLE KEYS */;

-- Zrzut struktury tabela chess_game.resumption_place_white_y
CREATE TABLE IF NOT EXISTS `resumption_place_white_y` (
  `Game_ID` int(11) NOT NULL,
  `rook_1_8` int(11) NOT NULL,
  `knight_2_8` int(11) NOT NULL,
  `bishop_3_8` int(11) NOT NULL,
  `king_4_8` int(11) NOT NULL,
  `queen_5_8` int(11) NOT NULL,
  `bishop_6_8` int(11) NOT NULL,
  `knight_7_8` int(11) NOT NULL,
  `rook_8_8` int(11) NOT NULL,
  `pawn_1_7` int(11) NOT NULL,
  `pawn_2_7` int(11) NOT NULL,
  `pawn_3_7` int(11) NOT NULL,
  `pawn_4_7` int(11) NOT NULL,
  `pawn_5_7` int(11) NOT NULL,
  `pawn_6_7` int(11) NOT NULL,
  `pawn_7_7` int(11) NOT NULL,
  `pawn_8_7` int(11) NOT NULL,
  UNIQUE KEY `Game_ID_Y` (`Game_ID`),
  CONSTRAINT `FK_game_play2_Y` FOREIGN KEY (`Game_ID`) REFERENCES `game` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.resumption_place_white_y: ~0 rows (około)
/*!40000 ALTER TABLE `resumption_place_white_y` DISABLE KEYS */;
/*!40000 ALTER TABLE `resumption_place_white_y` ENABLE KEYS */;

-- Zrzut struktury tabela chess_game.user
CREATE TABLE IF NOT EXISTS `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `nick` varchar(64) COLLATE utf16_polish_ci NOT NULL DEFAULT '0',
  `e_mail` varchar(128) COLLATE utf16_polish_ci NOT NULL DEFAULT '0',
  `password` varchar(128) COLLATE utf16_polish_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.user: ~3 rows (około)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`ID`, `nick`, `e_mail`, `password`) VALUES
	(1, 'abc', 'def', '\'); DROP TABLE user; ('),
	(2, 'zsi', 'edu', 'zxcvbnm'),
	(3, 'magda', 'asdfg', '1234567890');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Zrzut struktury wyzwalacz chess_game.ranking_trigger
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
-- Zrzut struktury wyzwalacz chess_game.ranking_trigger
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `ranking_trigger` AFTER INSERT ON `matches` FOR EACH ROW BEGIN
	declare current float;
	declare pnkGet float;
	declare pnkEX float;
	declare result float;
	set current = (SELECT ranking.Score FROM ranking
	WHERE ranking.ID_User = (SELECT player_user.ID_user FROM player_user
	WHERE player_user.ID_player = new.playerID));
	set pnkGet = (SELECT matches.result FROM matches
	WHERE matches.ID = new.ID);
	set pnkEX = ((SELECT ranking.Score FROM ranking
	WHERE ranking.ID_user = (SELECT player_user.ID_user FROM player_user
	WHERE player_user.ID_player = new.playerID)) /
	(SELECT ranking.Score FROM ranking
	WHERE ranking.ID_user = (SELECT player_user.ID_user FROM player_user
	WHERE player_user.ID_player = new.opponentID)));
	IF (pnkEX > 1.75) THEN
		SET pnkEX = 1.75;
	ELSEIF (pnkEX < 0.25) THEN
		SET pnkEX = 0.25;
	END IF;
	set result = current + 10 * (pnkGet - pnkEX);

	IF (result < 0.0) THEN
		set result = 0.0;
	END IF;
	
	UPDATE ranking set ranking.Score = result 
	WHERE ranking.ID_User = (SELECT player_user.ID_user FROM player_user
	WHERE player_user.ID_player = new.playerID); 
	
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
