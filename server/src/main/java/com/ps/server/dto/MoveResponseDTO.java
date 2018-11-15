package com.ps.server.dto;

import com.ps.server.Logic.Position;
import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MoveResponseDTO {

    @NotNull
    public boolean isMoveValid;

    public List<Pair<Position, Position>> additionalMoves;

    public String message;
}
