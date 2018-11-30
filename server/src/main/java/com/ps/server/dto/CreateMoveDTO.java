package com.ps.server.dto;

import com.ps.server.logic.Position;
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
}
