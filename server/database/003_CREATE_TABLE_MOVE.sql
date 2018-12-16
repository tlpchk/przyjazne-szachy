USE `chess_game`;
CREATE TABLE IF NOT EXISTS `move` (
  `id` bigint(20) NOT NULL,
  `created` timestamp NOT NULL DEFAULT current_timestamp(),
  `origin_column` int(11) NOT NULL,
  `origin_row` int(11) NOT NULL,
  `destination_column` int(11) NOT NULL,
  `destination_row` int(11) NOT NULL,
  `game_id` bigint(20) NOT NULL,
  `player_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6khbbqe4c9jmywgvi9nehpkd1` (`game_id`),
  KEY `FKfyg07nm0qyopn03emleo4jhxi` (`player_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;