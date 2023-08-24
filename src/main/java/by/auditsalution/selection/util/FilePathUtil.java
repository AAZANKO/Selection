package by.auditsalution.selection.util;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@UtilityClass
public class FilePathUtil {

    private static final String ABSOLUT_PATH_ROOT = "result";

    public static String getPathAndCreatePackage(String... param) {
        Path resultFilePath = null;
        if (param.length == 1){
            resultFilePath = Paths.get(ABSOLUT_PATH_ROOT, getCurrentPackage(), param[0]);
        } else {
            resultFilePath = Paths.get(ABSOLUT_PATH_ROOT, getCurrentPackage(), param[0], param[1]);
        }
        String stringResultFilePath = resultFilePath.toString();
        File file = resultFilePath.toFile();
        file.mkdirs();
        return stringResultFilePath;
    }

    private static String getCurrentPackage() {
        LocalDate now = LocalDate.now();
        String stringDate = String.valueOf(now);
        return stringDate.replace("-", "");
    }
}
