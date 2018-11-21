package com.ps.server.repository;

import com.ps.server.entity.GameEntity;
import com.ps.server.enums.GameStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<GameEntity,Long> {
    List<GameEntity> findByGameStatus(GameStatus gameStatus);
}
