package com.ps.server.repository;

import com.ps.server.entity.RankingEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RankingRepository extends CrudRepository<RankingEntity, Long> {
    List<RankingEntity> findAll();
}

