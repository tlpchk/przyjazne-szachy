package com.ps.server.logic.player;

import com.ps.server.logic.Color;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player {
    protected Color color;
}
