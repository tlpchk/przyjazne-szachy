USE `chess_game`;

DELIMITER //
CREATE TRIGGER `TR_user_after_insert`
  AFTER INSERT
  ON `user`
  FOR EACH ROW
  INSERT INTO `ranking` (`user_id`)
  VALUES (new.id);
DELIMITER ;

DELIMITER //
CREATE TRIGGER `TR_game_after_update`
  AFTER INSERT
  ON `matches`
  FOR EACH ROW
  BEGIN
    #     set isRanked = (SELECT game.is_ranked FROM game WHERE game.id = new.game_id);
    #     IF(isRanked = TRUE )
    #       THEN
    #     END IF ;
    declare current float;
    declare pnkGet float;
    declare pnkEX float;
    declare result float;
    set current = (SELECT ranking.score
                   FROM ranking
                   WHERE ranking.user_id = new.player_id);
    set pnkGet = (SELECT matches.player_points FROM matches WHERE matches.id = new.id);
    set pnkEX = ((SELECT ranking.score
                  FROM ranking
                  WHERE ranking.user_id = new.player_id) /
                 (SELECT ranking.score
                  FROM ranking
                  WHERE ranking.user_id = new.opponent_id));
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
    set ranking.score = result
    WHERE ranking.user_id = new.player_id;

  END//
DELIMITER ;