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

    public boolean isWasMoveValid() {
        return wasMoveValid;
    }

    public void setWasMoveValid(boolean wasMoveValid) {
        this.wasMoveValid = wasMoveValid;
    }

    public List<Change> getListOfChanges() {
        return listOfChanges;
    }

    public void setListOfChanges(List<Change> listOfChanges) {
        this.listOfChanges = listOfChanges;
    }
}
