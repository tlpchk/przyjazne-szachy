package com.ps.server.dto;

import com.ps.server.Logic.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMoveDTO {
    private Long playerId;
    private Position origin;
    private Position destination;

    public Long getPlayerId() {
        return playerId;
    }

    public Position getOrigin() {
        return origin;
    }

    public Position getDestination() {
        return destination;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public void setOrigin(Position origin) {
        this.origin = origin;
    }

    public void setDestination(Position destination) {
        this.destination = destination;
    }
}
