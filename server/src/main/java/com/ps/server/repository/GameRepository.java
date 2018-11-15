package com.ps.server.repository;

import com.ps.server.domain.Game;
import com.ps.server.enums.GameStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game,Long> {
    List<Game> findByGameStatus(GameStatus gameStatus);
}
