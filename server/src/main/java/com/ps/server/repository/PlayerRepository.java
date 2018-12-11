package com.ps.server.repository;

import com.ps.server.entity.PlayerEntity;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<PlayerEntity,Long> {
}
