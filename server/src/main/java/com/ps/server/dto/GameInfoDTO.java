package com.ps.server.dto;

import com.ps.server.Logic.GameState;
import com.ps.server.enums.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameInfoDTO {

    private Long lastUpdateId;

    private boolean isMyTurn;

    private boolean isPromotion;

    private GameState gameState;

    private Result gameResult;

    private String opponent;
}
