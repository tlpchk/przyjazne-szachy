package com.ps.server.repository;

import com.ps.server.entity.MatchEntity;
import org.springframework.data.repository.CrudRepository;

public interface MatchesRepository extends CrudRepository<MatchEntity, Long> {
}
