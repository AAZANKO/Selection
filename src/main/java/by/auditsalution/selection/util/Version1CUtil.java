package by.auditsalution.selection.util;

import by.auditsalution.selection.model.Card1CTemp;
import by.auditsalution.selection.model.Version1C;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class Version1CUtil {

    public Version1C checkVersion1C(List<Card1CTemp> card1CTemps) {
        Version1C version1c = null;
        for (Card1CTemp card1CTemp : card1CTemps) {
            String cell0 = card1CTemp.getCell0();
            if (cell0 != null) {
                if (VariableTypeUtil.isData(cell0)) {
                    if (VariableTypeUtil.isNumber(card1CTemp.getCell3()) && card1CTemp.getCell9() == null) {
                        version1c = Version1C.VERSION_1C_7;
                    } else {
                        version1c = Version1C.VERSION_1C_8;
                    }
                    break;
                }
            }
        }
        return version1c;
    }
}