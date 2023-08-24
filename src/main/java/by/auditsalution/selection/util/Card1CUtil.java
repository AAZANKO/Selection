package by.auditsalution.selection.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Card1CUtil {

    public static boolean isCardNumber(String value) {
        if (value != null && !value.isBlank()){
            Pattern pattern = Pattern.compile(".*?\\bКарточка счета\\b.*?");
            Matcher matcher = pattern.matcher(value);
            return matcher.matches();
        }
        return false;
    }

    public static String getAccountFromCardNumber(String value) {
        Pattern pattern = Pattern.compile("\\d{2}\\.?.*");
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

}
