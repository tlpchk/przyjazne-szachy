package com.ps.server.dto;

import com.ps.server.Logic.Change;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MoveResponseDTO {

    @NotNull
    public boolean wasMoveValid;

    public List<Change> listOfChanges;


    @Override
    public String toString() {
        return "MoveResponseDTO{" +
                "listOfChanges=" + listOfChanges +
                '}';
    }
}
