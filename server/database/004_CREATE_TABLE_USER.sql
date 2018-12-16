USE `chess_game`;
CREATE TABLE IF NOT EXISTS `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `nick` varchar(64) COLLATE utf16_polish_ci NOT NULL DEFAULT '0',
  `e_mail` varchar(128) COLLATE utf16_polish_ci NOT NULL DEFAULT '0',
  `password` varchar(128) COLLATE utf16_polish_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;