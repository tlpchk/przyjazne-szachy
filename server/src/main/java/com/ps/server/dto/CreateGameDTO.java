package com.ps.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateGameDTO {

    private Long firstPlayerId;

    private Long secondPlayerId;

    public Long getFirstPlayerId() {
        return firstPlayerId;
    }

    public Long getSecondPlayerId() {
        return secondPlayerId;
    }

    public void setFirstPlayerId(Long firstPlayerId) {
        this.firstPlayerId = firstPlayerId;
    }

    public void setSecondPlayerId(Long secondPlayerId) {
        this.secondPlayerId = secondPlayerId;
    }
}
