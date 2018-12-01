import {Injectable} from '@angular/core';
import {PositionDTO} from "../_models/positionDTO";

@Injectable({
    providedIn: 'root'
})
export class CoordinatesAdapterService {

    constructor() {
    }

    backendToFrontend(row: number, column: number): number {
        return 8 * row + column + 1;
    }

    frontendToBackend(cellId: number): PositionDTO {
        let row = Math.floor((cellId - 1) / 8);
        let column = cellId % 8 - 1;
        if (column < 0)
            column = 7;
        return new PositionDTO(row, column);
    }
}
