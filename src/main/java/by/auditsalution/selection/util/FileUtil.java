package by.auditsalution.selection.util;

import by.auditsalution.selection.exception.ServiceException;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@UtilityClass
public class FileUtil {

    public void copy(List<MultipartFile> openFiles, String filePath) throws ServiceException{
        try {
            cleanDirectory(filePath);
        } catch (IOException e){
            throw new ServiceException("Не удалось очистить папку");
        }
        for (MultipartFile multipartFile : openFiles) {
            Path resultFilePath = Paths.get(filePath, multipartFile.getOriginalFilename());
            try (OutputStream os = Files.newOutputStream(resultFilePath)) {
                os.write(multipartFile.getBytes());
            } catch (IOException e) {
                throw new ServiceException("Не удалось обработать выбранные файлы");
            }
        }
    }

    private void cleanDirectory(String workFilePath) throws IOException {
        FileUtils.cleanDirectory(new File(workFilePath));
    }

}
