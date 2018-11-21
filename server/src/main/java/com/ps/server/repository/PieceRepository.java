package com.ps.server.repository;

import com.ps.server.entity.PieceEntity;
import org.springframework.data.repository.CrudRepository;

public interface PieceRepository extends CrudRepository<PieceEntity,Long> {
}
