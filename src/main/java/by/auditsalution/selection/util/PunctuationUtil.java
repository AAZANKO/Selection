package by.auditsalution.selection.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PunctuationUtil {

    public String reverseCommaToPoint(String value){
        return value.contains(",") ? value.replace(",", ".") : value;
    }

    public String reversePointToComma(String value){
        return value.contains(".") ? value.replace(".", ",") : value;
    }
}
