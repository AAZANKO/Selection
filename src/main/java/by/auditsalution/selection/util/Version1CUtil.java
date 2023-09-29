package by.auditsalution.selection.util;

import by.auditsalution.selection.model.Card1CTemp;
import by.auditsalution.selection.model.Version1C;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Optional;

@UtilityClass
public class Version1CUtil {
    private static final int ITERATOR = 10;

    public Optional<Version1C> checkVersion1C(List<Card1CTemp> card1CTemps) {
        for (int i = 0; i < ITERATOR; i++) {
            String cell0 = card1CTemps.get(i).getCell0();
            if (cell0 != null) {
                if (VariableTypeUtil.isData1C7(cell0)) {
                    if (VariableTypeUtil.isNumber(card1CTemps.get(i).getCell3()) && VariableTypeUtil.isNumber(card1CTemps.get(i).getCell5())) {
                       return Optional.ofNullable(Version1C.VERSION_1C_7);
                    }
                }
            }
        }
        for (int i = 0; i < ITERATOR; i++) {
            String cell0 = card1CTemps.get(i).getCell0();
            if (cell0 != null) {
                if (VariableTypeUtil.isData1C8(cell0)) {
                    if (VariableTypeUtil.isNumber(card1CTemps.get(i).getCell3()) && VariableTypeUtil.isNumber(card1CTemps.get(i).getCell5())) {
                        return Optional.ofNullable(Version1C.VERSION_1C_8);
                    }
                }
            }
        }
        return Optional.empty();
    }
}