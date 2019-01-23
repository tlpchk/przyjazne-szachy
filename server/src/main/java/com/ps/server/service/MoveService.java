package com.ps.server.service;

import com.ps.server.Logic.Position;
import com.ps.server.entity.GameEntity;
import com.ps.server.entity.MoveEntity;
import com.ps.server.entity.PlayerEntity;
import com.ps.server.repository.MoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;

@Service
@Transactional
public class MoveService {

    @Autowired
    private MoveRepository moveRepository;

    /**
     * Saves move in database
     *
     * @param game Game for which Move should be saved
     * @param player Player who made Move
     * @param origin Origin of Move
     * @param destination Destination of Move
     */
    public void persistMove(GameEntity game, PlayerEntity player, Position origin, Position destination) {
        MoveEntity moveEntity = new MoveEntity();
        moveEntity.setCreationDate(new Timestamp(new Date().getTime()));
        moveEntity.setGame(game);
        moveEntity.setPlayer(player);
        moveEntity.setOriginRow(origin.getRow());
        moveEntity.setOriginColumn(origin.getCol());
        moveEntity.setDestinationRow(destination.getRow());
        moveEntity.setDestinationColumn(destination.getCol());
        moveRepository.save(moveEntity);
    }
}
