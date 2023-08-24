package by.auditsalution.selection.util;

import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class FileExtensionUtil {

    /** получить расширение файла
     *
     * @param fileName
     * @return
     */
    public static String getExtension(String fileName){
        String fileExtension = null;
        Optional<String> s = Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1));
        if (s.isPresent()){
            fileExtension = s.get();
        }
        return fileExtension;
    }

}
