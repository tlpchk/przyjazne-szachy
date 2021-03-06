import {Injectable} from '@angular/core';
import {PositionDTO} from "../DTO/positionDTO";
import {Color} from "../_models/color";

/** Adaptor danych */
@Injectable({
    providedIn: 'root'
})
export class CoordinatesAdapterService {
    /** Adaptacja danych z serwera do klienta*/
    backendToFrontend(row: number, column: number, playerColor: Color): number {
        let r = row;
        let c = column;
        if (playerColor == Color.black) {
            r = 7 - row;
            c = 7 - column;
        }
        return 8 * r + c + 1;
    }

    /** Adaptacja danych z klienta do serwera*/
    frontendToBackend(cellId: number, playerColor: Color): PositionDTO {
        let row = Math.floor((cellId - 1) / 8);
        let column = cellId % 8 - 1;
        if (column < 0)
            column = 7;
        if (playerColor == Color.black) {
            row = 7 - row;
            column = 7 - column;
        }
        return new PositionDTO(row, column);
    }
}
