package by.auditsalution.selection.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class VariableTypeUtil {

    public boolean isData(String value) {
        Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{2}");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public boolean isNumber(String value) {
        Pattern pattern = Pattern.compile("[0-9]+.?[0-9]+");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
