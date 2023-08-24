package by.auditsalution.selection.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalTime;

@UtilityClass
public class FileNameUtil {

    public static String getOutputFileName() {
        String currentPackage = getCurrentPackage();
        String time = getTime();
        return currentPackage + "_" + time;
    }

    private static String getTime() {
        LocalTime now = LocalTime.now();
        String stringDate = String.valueOf(now);
        return stringDate.replace(":", "");
    }

    private static String getCurrentPackage() {
        LocalDate now = LocalDate.now();
        String stringDate = String.valueOf(now);
        return stringDate.replace("-", "");
    }

}
