package com.ps.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {
    private Long id;
    private Long gameId;

    public Long getGameId() {
        return gameId;
    }

    public Long getId() {
        return id;
    }
}
