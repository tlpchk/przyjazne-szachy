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

-- Zrzut struktury tabela chess_game.Game
CREATE TABLE IF NOT EXISTS `game` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `break_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `playerID_resumption` int(11) NOT NULL,
  `player_1` int(11) NOT NULL,
  `player_2` int(11) NOT NULL,
  `result` enum('1_Win','2_Win','Draw') COLLATE utf16_polish_ci NOT NULL DEFAULT 'Draw',
  `move_limit` time NOT NULL DEFAULT '00:05:00',
  `game_limit` time NOT NULL DEFAULT '02:00:00',
  PRIMARY KEY (`ID`),
  KEY `FK_player` (`playerID_resumption`),
  CONSTRAINT `FK_player` FOREIGN KEY (`playerID_resumption`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.Game: ~0 rows (około)
/*!40000 ALTER TABLE `Game` DISABLE KEYS */;
/*!40000 ALTER TABLE `Game` ENABLE KEYS */;

-- Zrzut struktury tabela chess_game.Hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.Hibernate_sequence: 3 rows
/*!40000 ALTER TABLE `Hibernate_sequence` DISABLE KEYS */;
REPLACE INTO `hibernate_sequence` (`next_val`) VALUES
	(1),
	(1),
	(1);
/*!40000 ALTER TABLE `Hibernate_sequence` ENABLE KEYS */;

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

-- Zrzut struktury tabela chess_game.Matches
CREATE TABLE IF NOT EXISTS `matches` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `gameID` int(11) NOT NULL DEFAULT 0,
  `playerID` int(11) NOT NULL DEFAULT 0,
  `result` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`ID`),
  KEY `player_key` (`playerID`),
  CONSTRAINT `player_key` FOREIGN KEY (`playerID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.Matches: ~0 rows (około)
/*!40000 ALTER TABLE `Matches` DISABLE KEYS */;
/*!40000 ALTER TABLE `Matches` ENABLE KEYS */;

-- Zrzut struktury tabela chess_game.Move
CREATE TABLE IF NOT EXISTS `move` (
  `id` int(11) NOT NULL,
  `created` datetime NOT NULL,
  `destination_column` int(11) NOT NULL,
  `destination_row` int(11) NOT NULL,
  `origin_column` int(11) NOT NULL,
  `origin_row` int(11) NOT NULL,
  `game_id` bigint(20) NOT NULL,
  `player_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6khbbqe4c9jmywgvi9nehpkd1` (`game_id`),
  KEY `FKfyg07nm0qyopn03emleo4jhxi` (`player_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.Move: 0 rows
/*!40000 ALTER TABLE `Move` DISABLE KEYS */;
/*!40000 ALTER TABLE `Move` ENABLE KEYS */;

-- Zrzut struktury tabela chess_game.Piece
CREATE TABLE IF NOT EXISTS `piece` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` enum('pawn','rook','bishop','knight','queen','king','free') COLLATE utf16_polish_ci NOT NULL,
  `color` enum('white','black') COLLATE utf16_polish_ci NOT NULL,
  `position_X` int(11) NOT NULL DEFAULT 0,
  `position_Y` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.Piece: ~32 rows (około)
/*!40000 ALTER TABLE `Piece` DISABLE KEYS */;
REPLACE INTO `piece` (`ID`, `name`, `color`, `position_X`, `position_Y`) VALUES
	(1, 'rook', 'black', 1, 1),
	(2, 'knight', 'black', 2, 1),
	(3, 'bishop', 'black', 3, 1),
	(4, 'queen', 'black', 4, 1),
	(5, 'king', 'black', 5, 1),
	(6, 'bishop', 'black', 6, 1),
	(7, 'knight', 'black', 7, 1),
	(8, 'rook', 'black', 8, 1),
	(9, 'pawn', 'black', 1, 2),
	(10, 'pawn', 'black', 2, 2),
	(11, 'pawn', 'black', 3, 2),
	(12, 'pawn', 'black', 4, 2),
	(13, 'pawn', 'black', 5, 2),
	(14, 'pawn', 'black', 6, 2),
	(15, 'pawn', 'black', 7, 2),
	(16, 'pawn', 'black', 8, 2),
	(17, 'pawn', 'white', 1, 7),
	(18, 'pawn', 'white', 2, 7),
	(19, 'pawn', 'white', 3, 7),
	(20, 'pawn', 'white', 4, 7),
	(21, 'pawn', 'white', 5, 7),
	(22, 'pawn', 'white', 6, 7),
	(23, 'pawn', 'white', 7, 7),
	(24, 'pawn', 'white', 8, 7),
	(25, 'rook', 'white', 1, 8),
	(26, 'knight', 'white', 2, 8),
	(27, 'bishop', 'white', 3, 8),
	(28, 'king', 'white', 4, 8),
	(29, 'queen', 'white', 5, 8),
	(30, 'bishop', 'white', 6, 8),
	(31, 'knight', 'white', 7, 8),
	(32, 'rook', 'white', 8, 8);
/*!40000 ALTER TABLE `Piece` ENABLE KEYS */;

-- Zrzut struktury tabela chess_game.Player
CREATE TABLE IF NOT EXISTS `player` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `color` enum('White','Black') COLLATE utf16_polish_ci NOT NULL,
  `player_type` enum('Player','Bot') COLLATE utf16_polish_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.Player: ~0 rows (około)
/*!40000 ALTER TABLE `Player` DISABLE KEYS */;
/*!40000 ALTER TABLE `Player` ENABLE KEYS */;

-- Zrzut struktury tabela chess_game.Player_user
CREATE TABLE IF NOT EXISTS `player_user` (
  `ID_user` int(11) NOT NULL,
  `ID_player` int(11) NOT NULL,
  UNIQUE KEY `ID_player` (`ID_player`),
  KEY `FK_user_2` (`ID_user`),
  CONSTRAINT `FK_player_2` FOREIGN KEY (`ID_player`) REFERENCES `player` (`ID`),
  CONSTRAINT `FK_user_2` FOREIGN KEY (`ID_user`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.Player_user: ~0 rows (około)
/*!40000 ALTER TABLE `Player_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `Player_user` ENABLE KEYS */;

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

-- Zrzut struktury tabela chess_game.Resumption_place_black_y
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

-- Zrzucanie danych dla tabeli chess_game.Resumption_place_black_y: ~0 rows (około)
/*!40000 ALTER TABLE `Resumption_place_black_y` DISABLE KEYS */;
/*!40000 ALTER TABLE `Resumption_place_black_y` ENABLE KEYS */;

-- Zrzut struktury tabela chess_game.Resumption_place_white_x
CREATE TABLE IF NOT EXISTS `resumption_place_white_x` (
  `Game_ID` int(11) NOT NULL,
  `rook_1_7` int(11) NOT NULL,
  `knight_2_7` int(11) NOT NULL,
  `bishop_3_7` int(11) NOT NULL,
  `king_4_7` int(11) NOT NULL,
  `queen_5_7` int(11) NOT NULL,
  `bishop_6_7` int(11) NOT NULL,
  `knight_7_7` int(11) NOT NULL,
  `rook_8_7` int(11) NOT NULL,
  `pawn_1_8` int(11) NOT NULL,
  `pawn_2_8` int(11) NOT NULL,
  `pawn_3_8` int(11) NOT NULL,
  `pawn_4_8` int(11) NOT NULL,
  `pawn_5_8` int(11) NOT NULL,
  `pawn_6_8` int(11) NOT NULL,
  `pawn_7_8` int(11) NOT NULL,
  `pawn_8_8` int(11) NOT NULL,
  UNIQUE KEY `Game_ID` (`Game_ID`),
  CONSTRAINT `FK_game_play2` FOREIGN KEY (`Game_ID`) REFERENCES `game` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.Resumption_place_white_x: ~0 rows (około)
/*!40000 ALTER TABLE `Resumption_place_white_x` DISABLE KEYS */;
/*!40000 ALTER TABLE `Resumption_place_white_x` ENABLE KEYS */;

-- Zrzut struktury tabela chess_game.Resumption_place_white_y
CREATE TABLE IF NOT EXISTS `resumption_place_white_y` (
  `Game_ID` int(11) NOT NULL,
  `rook_1_7` int(11) NOT NULL,
  `knight_2_7` int(11) NOT NULL,
  `bishop_3_7` int(11) NOT NULL,
  `king_4_7` int(11) NOT NULL,
  `queen_5_7` int(11) NOT NULL,
  `bishop_6_7` int(11) NOT NULL,
  `knight_7_7` int(11) NOT NULL,
  `rook_8_7` int(11) NOT NULL,
  `pawn_1_8` int(11) NOT NULL,
  `pawn_2_8` int(11) NOT NULL,
  `pawn_3_8` int(11) NOT NULL,
  `pawn_4_8` int(11) NOT NULL,
  `pawn_5_8` int(11) NOT NULL,
  `pawn_6_8` int(11) NOT NULL,
  `pawn_7_8` int(11) NOT NULL,
  `pawn_8_8` int(11) NOT NULL,
  UNIQUE KEY `Game_ID_Y` (`Game_ID`),
  CONSTRAINT `FK_game_play2_Y` FOREIGN KEY (`Game_ID`) REFERENCES `game` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.Resumption_place_white_y: ~0 rows (około)
/*!40000 ALTER TABLE `Resumption_place_white_y` DISABLE KEYS */;
/*!40000 ALTER TABLE `Resumption_place_white_y` ENABLE KEYS */;

-- Zrzut struktury tabela chess_game.User
CREATE TABLE IF NOT EXISTS `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `nick` varchar(64) COLLATE utf16_polish_ci NOT NULL DEFAULT '0',
  `e-mail` varchar(128) COLLATE utf16_polish_ci NOT NULL DEFAULT '0',
  `password` varchar(128) COLLATE utf16_polish_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- Zrzucanie danych dla tabeli chess_game.User: ~1 rows (około)
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
REPLACE INTO `user` (`ID`, `nick`, `e-mail`, `password`) VALUES
	(1, 'abc', 'def', '\'); DROP TABLE user; (');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;

-- Zrzut struktury wyzwalacz chess_game.move_after_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `move_after_insert` AFTER INSERT ON `Move` FOR EACH ROW BEGIN
	if (Move.pieceID = 1) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_1_1 
		= Move.positionEnd_X
		WHERE resumption_place_black.Game_ID = Move.gameID;
		UPDATE Resumption_place_black_y SET resumption_place_black.rook_1_1
		= Move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = Move.gameID;
	elseif (Move.pieceID = 2) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_2_1 
		= Move.positionEnd_X
		WHERE resumption_place_black.Game_ID = Move.gameID;
		UPDATE Resumption_place_black_y SET resumption_place_black.rook_2_1
		= Move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = Move.gameID;
	elseif (Move.pieceID = 3) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_3_1 
		= Move.positionEnd_X
		WHERE resumption_place_black.Game_ID = Move.gameID;
		UPDATE Resumption_place_black_y SET resumption_place_black.rook_3_1
		= Move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = Move.gameID;
	elseif (Move.pieceID = 4) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_4_1 
		= Move.positionEnd_X
		WHERE resumption_place_black.Game_ID = Move.gameID;
		UPDATE Resumption_place_black_y SET resumption_place_black.rook_4_1
		= Move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = Move.gameID;
	elseif (Move.pieceID = 5) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_5_1 
		= Move.positionEnd_X
		WHERE resumption_place_black.Game_ID = Move.gameID;
		UPDATE Resumption_place_black_y SET resumption_place_black.rook_5_1
		= Move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = Move.gameID;
	elseif (Move.pieceID = 6) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_6_1 
		= Move.positionEnd_X
		WHERE resumption_place_black.Game_ID = Move.gameID;
		UPDATE Resumption_place_black_y SET resumption_place_black.rook_6_1
		= Move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = Move.gameID;
	elseif (Move.pieceID = 7) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_7_1 
		= Move.positionEnd_X
		WHERE resumption_place_black.Game_ID = Move.gameID;
		UPDATE Resumption_place_black_y SET resumption_place_black.rook_7_1
		= Move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = Move.gameID;
	elseif (Move.pieceID = 8) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_8_1 
		= Move.positionEnd_X
		WHERE resumption_place_black.Game_ID = Move.gameID;
		UPDATE Resumption_place_black_y SET resumption_place_black.rook_8_1
		= Move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = Move.gameID;
	elseif (Move.pieceID = 9) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_1_2 
		= Move.positionEnd_X
		WHERE resumption_place_black.Game_ID = Move.gameID;
		UPDATE Resumption_place_black_y SET resumption_place_black.rook_1_2
		= Move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = Move.gameID;
	elseif (Move.pieceID = 10) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_2_2 
		= Move.positionEnd_X
		WHERE resumption_place_black.Game_ID = Move.gameID;
		UPDATE Resumption_place_black_y SET resumption_place_black.rook_2_2
		= Move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = Move.gameID;
	elseif (Move.pieceID = 11) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_3_2 
		= Move.positionEnd_X
		WHERE resumption_place_black.Game_ID = Move.gameID;
		UPDATE Resumption_place_black_y SET resumption_place_black.rook_3_2
		= Move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = Move.gameID;
	elseif (Move.pieceID = 12) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_4_2 
		= Move.positionEnd_X
		WHERE resumption_place_black.Game_ID = Move.gameID;
		UPDATE Resumption_place_black_y SET resumption_place_black.rook_4_2
		= Move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = Move.gameID;
	elseif (Move.pieceID = 13) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_5_2 
		= Move.positionEnd_X
		WHERE resumption_place_black.Game_ID = Move.gameID;
		UPDATE Resumption_place_black_y SET resumption_place_black.rook_5_2
		= Move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = Move.gameID;
	elseif (Move.pieceID = 14) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_6_2 
		= Move.positionEnd_X
		WHERE resumption_place_black.Game_ID = Move.gameID;
		UPDATE Resumption_place_black_y SET resumption_place_black.rook_6_2
		= Move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = Move.gameID;
	elseif (Move.pieceID = 15) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_7_2 
		= Move.positionEnd_X
		WHERE resumption_place_black.Game_ID = Move.gameID;
		UPDATE Resumption_place_black_y SET resumption_place_black.rook_7_2
		= Move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = Move.gameID;
	elseif (Move.pieceID = 16) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_8_2 
		= Move.positionEnd_X
		WHERE resumption_place_black.Game_ID = Move.gameID;
		UPDATE Resumption_place_black_y SET resumption_place_black.rook_8_2
		= Move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = Move.gameID;
	elseif (Move.pieceID = 17) then
		UPDATE resumption_place_white SET resumption_place_white.rook_1_7 
		= Move.positionEnd_X
		WHERE resumption_place_white.Game_ID = Move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_1_7 
		= Move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = Move.gameID;
	elseif (Move.pieceID = 18) then
		UPDATE resumption_place_white SET resumption_place_white.rook_2_7 
		= Move.positionEnd_X
		WHERE resumption_place_white.Game_ID = Move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_2_7 
		= Move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = Move.gameID;
	elseif (Move.pieceID = 19) then
		UPDATE resumption_place_white SET resumption_place_white.rook_3_7 
		= Move.positionEnd_X
		WHERE resumption_place_white.Game_ID = Move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_3_7 
		= Move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = Move.gameID;
	elseif (Move.pieceID = 20) then
		UPDATE resumption_place_white SET resumption_place_white.rook_4_7 
		= Move.positionEnd_X
		WHERE resumption_place_white.Game_ID = Move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_4_7 
		= Move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = Move.gameID;
	elseif (Move.pieceID = 21) then
		UPDATE resumption_place_white SET resumption_place_white.rook_5_7 
		= Move.positionEnd_X
		WHERE resumption_place_white.Game_ID = Move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_5_7 
		= Move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = Move.gameID;
	elseif (Move.pieceID = 22) then
		UPDATE resumption_place_white SET resumption_place_white.rook_6_7 
		= Move.positionEnd_X
		WHERE resumption_place_white.Game_ID = Move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_6_7 
		= Move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = Move.gameID;
	elseif (Move.pieceID = 23) then
		UPDATE resumption_place_white SET resumption_place_white.rook_7_7 
		= Move.positionEnd_X
		WHERE resumption_place_white.Game_ID = Move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_7_7 
		= Move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = Move.gameID;
	elseif (Move.pieceID = 24) then
		UPDATE resumption_place_white SET resumption_place_white.rook_8_7 
		= Move.positionEnd_X
		WHERE resumption_place_white.Game_ID = Move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_8_7 
		= Move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = Move.gameID;
	elseif (Move.pieceID = 25) then
		UPDATE resumption_place_white SET resumption_place_white.rook_1_8 
		= Move.positionEnd_X
		WHERE resumption_place_white.Game_ID = Move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_1_8 
		= Move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = Move.gameID;
	elseif (Move.pieceID = 26) then
		UPDATE resumption_place_white SET resumption_place_white.rook_2_8 
		= Move.positionEnd_X
		WHERE resumption_place_white.Game_ID = Move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_2_8 
		= Move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = Move.gameID;
	elseif (Move.pieceID = 27) then
		UPDATE resumption_place_white SET resumption_place_white.rook_3_8 
		= Move.positionEnd_X
		WHERE resumption_place_white.Game_ID = Move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_3_8 
		= Move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = Move.gameID;
	elseif (Move.pieceID = 28) then
		UPDATE resumption_place_white SET resumption_place_white.rook_4_8 
		= Move.positionEnd_X
		WHERE resumption_place_white.Game_ID = Move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_4_8 
		= Move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = Move.gameID;
	elseif (Move.pieceID = 29) then
		UPDATE resumption_place_white SET resumption_place_white.rook_5_8 
		= Move.positionEnd_X
		WHERE resumption_place_white.Game_ID = Move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_5_8 
		= Move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = Move.gameID;
	elseif (Move.pieceID = 30) then
		UPDATE resumption_place_white SET resumption_place_white.rook_6_8 
		= Move.positionEnd_X
		WHERE resumption_place_white.Game_ID = Move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_6_8 
		= Move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = Move.gameID;
	elseif (Move.pieceID = 31) then
		UPDATE resumption_place_white SET resumption_place_white.rook_7_8 
		= Move.positionEnd_X
		WHERE resumption_place_white.Game_ID = Move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_7_8 
		= Move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = Move.gameID;
	elseif (Move.pieceID = 32) then
		UPDATE resumption_place_white SET resumption_place_white.rook_8_8 
		= Move.positionEnd_X
		WHERE resumption_place_white.Game_ID = Move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_8_8 
		= Move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = Move.gameID;

	end if;
	
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Zrzut struktury wyzwalacz chess_game.move_after_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `move_after_update` AFTER UPDATE ON `move` FOR EACH ROW BEGIN


	if (move.pieceID = 1) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_1_1 
		= move.positionEnd_X
		WHERE resumption_place_black.Game_ID = move.gameID;
		UPDATE resumption_place_black_y SET resumption_place_black.rook_1_1 
		= move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = move.gameID;  
	elseif (move.pieceID = 2) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_2_1 
		= move.positionEnd_X
		WHERE resumption_place_black.Game_ID = move.gameID;
		UPDATE resumption_place_black_y SET resumption_place_black.rook_2_1 
		= move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = move.gameID;  
	elseif (move.pieceID = 3) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_3_1 
		= move.positionEnd_X
		WHERE resumption_place_black.Game_ID = move.gameID;
		UPDATE resumption_place_black_y SET resumption_place_black.rook_3_1 
		= move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = move.gameID; 
	elseif (move.pieceID = 4) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_4_1 
		= move.positionEnd_X
		WHERE resumption_place_black.Game_ID = move.gameID;
		UPDATE resumption_place_black_y SET resumption_place_black.rook_4_1 
		= move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = move.gameID; 
	elseif (move.pieceID = 5) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_5_1 
		= move.positionEnd_X
		WHERE resumption_place_black.Game_ID = move.gameID;
		UPDATE resumption_place_black_y SET resumption_place_black.rook_5_1 
		= move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = move.gameID; 
	elseif (move.pieceID = 6) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_6_1 
		= move.positionEnd_X
		WHERE resumption_place_black.Game_ID = move.gameID;
		UPDATE resumption_place_black_y SET resumption_place_black.rook_6_1 
		= move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = move.gameID; 
	elseif (move.pieceID = 7) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_7_1 
		= move.positionEnd_X
		WHERE resumption_place_black.Game_ID = move.gameID;
		UPDATE resumption_place_black_y SET resumption_place_black.rook_7_1 
		= move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = move.gameID; 
	elseif (move.pieceID = 8) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_8_1 
		= move.positionEnd_X
		WHERE resumption_place_black.Game_ID = move.gameID;
		UPDATE resumption_place_black_y SET resumption_place_black.rook_8_1 
		= move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = move.gameID; 
	elseif (move.pieceID = 9) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_1_2 
		= move.positionEnd_X
		WHERE resumption_place_black.Game_ID = move.gameID;
		UPDATE resumption_place_black_y SET resumption_place_black.rook_1_2 
		= move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = move.gameID; 
	elseif (move.pieceID = 10) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_2_2 
		= move.positionEnd_X
		WHERE resumption_place_black.Game_ID = move.gameID;
		UPDATE resumption_place_black_y SET resumption_place_black.rook_2_2 
		= move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = move.gameID; 
	elseif (move.pieceID = 11) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_3_2 
		= move.positionEnd_X
		WHERE resumption_place_black.Game_ID = move.gameID;
		UPDATE resumption_place_black_y SET resumption_place_black.rook_3_2 
		= move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = move.gameID; 
	elseif (move.pieceID = 12) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_4_2 
		= move.positionEnd_X
		WHERE resumption_place_black.Game_ID = move.gameID;
		UPDATE resumption_place_black_y SET resumption_place_black.rook_4_2 
		= move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = move.gameID; 
	elseif (move.pieceID = 13) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_5_2 
		= move.positionEnd_X
		WHERE resumption_place_black.Game_ID = move.gameID;
		UPDATE resumption_place_black_y SET resumption_place_black.rook_5_2 
		= move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = move.gameID; 
	elseif (move.pieceID = 14) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_6_2 
		= move.positionEnd_X
		WHERE resumption_place_black.Game_ID = move.gameID;
		UPDATE resumption_place_black_y SET resumption_place_black.rook_6_2 
		= move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = move.gameID; 
	elseif (move.pieceID = 15) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_7_2 
		= move.positionEnd_X
		WHERE resumption_place_black.Game_ID = move.gameID;
		UPDATE resumption_place_black_y SET resumption_place_black.rook_7_2 
		= move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = move.gameID; 
	elseif (move.pieceID = 16) then
		UPDATE resumption_place_black_x SET resumption_place_black.rook_8_2 
		= move.positionEnd_X
		WHERE resumption_place_black.Game_ID = move.gameID;
		UPDATE resumption_place_black_y SET resumption_place_black.rook_8_2 
		= move.positionEnd_Y
		WHERE resumption_place_black.Game_ID = move.gameID; 
	elseif (move.pieceID = 17) then
		UPDATE resumption_place_white SET resumption_place_white.rook_1_7 
		= move.positionEnd_X
		WHERE resumption_place_white.Game_ID = move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_1_7 
		= move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = move.gameID;
	elseif (move.pieceID = 18) then
		UPDATE resumption_place_white SET resumption_place_white.rook_2_7 
		= move.positionEnd_X
		WHERE resumption_place_white.Game_ID = move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_2_7 
		= move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = move.gameID;
	elseif (move.pieceID = 19) then
		UPDATE resumption_place_white SET resumption_place_white.rook_3_7 
		= move.positionEnd_X
		WHERE resumption_place_white.Game_ID = move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_3_7 
		= move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = move.gameID;
	elseif (move.pieceID = 20) then
		UPDATE resumption_place_white SET resumption_place_white.rook_4_7 
		= move.positionEnd_X
		WHERE resumption_place_white.Game_ID = move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_4_7 
		= move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = move.gameID;
	elseif (move.pieceID = 21) then
		UPDATE resumption_place_white SET resumption_place_white.rook_5_7 
		= move.positionEnd_X
		WHERE resumption_place_white.Game_ID = move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_5_7 
		= move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = move.gameID;
	elseif (move.pieceID = 22) then
		UPDATE resumption_place_white SET resumption_place_white.rook_6_7 
		= move.positionEnd_X
		WHERE resumption_place_white.Game_ID = move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_6_7 
		= move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = move.gameID;
	elseif (move.pieceID = 23) then
		UPDATE resumption_place_white SET resumption_place_white.rook_7_7 
		= move.positionEnd_X
		WHERE resumption_place_white.Game_ID = move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_7_7 
		= move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = move.gameID;
	elseif (move.pieceID = 24) then
		UPDATE resumption_place_white SET resumption_place_white.rook_8_7 
		= move.positionEnd_X
		WHERE resumption_place_white.Game_ID = move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_8_7 
		= move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = move.gameID;
	elseif (move.pieceID = 25) then
		UPDATE resumption_place_white SET resumption_place_white.rook_1_8 
		= move.positionEnd_X
		WHERE resumption_place_white.Game_ID = move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_1_8 
		= move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = move.gameID;
	elseif (move.pieceID = 26) then
		UPDATE resumption_place_white SET resumption_place_white.rook_2_8 
		= move.positionEnd_X
		WHERE resumption_place_white.Game_ID = move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_2_8 
		= move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = move.gameID;
	elseif (move.pieceID = 27) then
		UPDATE resumption_place_white SET resumption_place_white.rook_3_8 
		= move.positionEnd_X
		WHERE resumption_place_white.Game_ID = move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_3_8 
		= move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = move.gameID;
	elseif (move.pieceID = 28) then
		UPDATE resumption_place_white SET resumption_place_white.rook_4_8 
		= move.positionEnd_X
		WHERE resumption_place_white.Game_ID = move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_4_8 
		= move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = move.gameID;
	elseif (move.pieceID = 29) then
		UPDATE resumption_place_white SET resumption_place_white.rook_5_8 
		= move.positionEnd_X
		WHERE resumption_place_white.Game_ID = move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_5_8 
		= move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = move.gameID;
	elseif (move.pieceID = 30) then
		UPDATE resumption_place_white SET resumption_place_white.rook_6_8 
		= move.positionEnd_X
		WHERE resumption_place_white.Game_ID = move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_6_8 
		= move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = move.gameID;
	elseif (move.pieceID = 31) then
		UPDATE resumption_place_white SET resumption_place_white.rook_7_8 
		= move.positionEnd_X
		WHERE resumption_place_white.Game_ID = move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_7_8 
		= move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = move.gameID;
	elseif (move.pieceID = 32) then
		UPDATE resumption_place_white SET resumption_place_white.rook_8_8 
		= move.positionEnd_X
		WHERE resumption_place_white.Game_ID = move.gameID;
		UPDATE resumption_place_white SET resumption_place_white.rook_8_8 
		= move.positionEnd_Y
		WHERE resumption_place_white.Game_ID = move.gameID;

	end if;
		
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
