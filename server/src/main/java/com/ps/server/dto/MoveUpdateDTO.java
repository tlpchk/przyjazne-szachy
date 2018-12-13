package com.ps.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MoveUpdateDTO {

    private Long updateId = 0L;

    @NotNull
    private MoveResponseDTO moveDTO;

    public Long getUpdateId() {
        return updateId;
    }

    public MoveResponseDTO getMoveDTO() {
        return moveDTO;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public void setMoveDTO(MoveResponseDTO moveDTO) {
        this.moveDTO = moveDTO;
    }
}
