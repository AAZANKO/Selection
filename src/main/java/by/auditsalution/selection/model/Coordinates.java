package by.auditsalution.selection.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coordinates {

    private int positionRandom;

    private int positionMax;

    private int positionSaldoRandom;

    private int positionSaldoMax;
}
