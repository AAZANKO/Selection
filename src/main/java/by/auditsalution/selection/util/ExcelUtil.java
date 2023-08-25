package by.auditsalution.selection.util;

import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static by.auditsalution.selection.model.ExcelFormatType.XLSX;

@UtilityClass
public class ExcelUtil {

    private static final int EXTENSION_COUNT_CHAR = 4;

    public static boolean isValidNameFiles(List<MultipartFile> files) {
        for (MultipartFile file : files) {
            String originalFileName = file.getOriginalFilename().trim();
            String extension = originalFileName.substring(originalFileName.length() - EXTENSION_COUNT_CHAR);
            String extensionReplacePunctuation = extension.replace(".", "");
            if (!XLSX.getDescription().equals(extensionReplacePunctuation.toLowerCase())) {
                return false;
            }
        }
        return true;
    }
}
