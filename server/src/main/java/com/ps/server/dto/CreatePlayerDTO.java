package com.ps.server.dto;

import com.ps.server.Logic.Color;
import com.ps.server.enums.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlayerDTO {
    private String username;
    private Color color;
    private PlayerType playerType;
}
