package by.auditsalution.selection.service.impl;

import by.auditsalution.selection.converter.CardConverter;
import by.auditsalution.selection.exception.ServiceException;
import by.auditsalution.selection.model.Account;
import by.auditsalution.selection.model.Card;
import by.auditsalution.selection.model.Card1CTemp;
import by.auditsalution.selection.model.Version1C;
import by.auditsalution.selection.service.CardService;
import by.auditsalution.selection.util.FileExtensionUtil;
import by.auditsalution.selection.util.Version1CUtil;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static by.auditsalution.selection.model.ExcelFormatType.XLS;
import static by.auditsalution.selection.model.ExcelFormatType.XLSX;

/**
 * Excel 5.0/95     =>  new OldExcelExtractor()
 * Excel 97-2003    =>  new HSSFWorkbook() -> (HSSFWorkbook, HSSFSheet, HSSFRow и HSSFCell)
 * Excel 2007+      =>  new XSSFWorkbook() -> (XSSFWorkbook, XSSFSheet, XSSFRow и XSSFCell)
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CardServiceImpl implements CardService {
    private final CardConverter cardConverter;
    public static final int FIRST_SHEET = 0;

    @Override
    public List<Card1CTemp> createCardTempFromFiles(String inputFilePath) throws ServiceException {
        File dir = new File(inputFilePath);
        List<File> listFile = Arrays.asList(dir.listFiles());
        List<Card1CTemp> card1CTempList = new ArrayList<>();
        try {
            for (File file : listFile) {
                String extensionFile = FileExtensionUtil.getExtension(file.getName());
                if (XLSX.getDescription().equals(extensionFile)) {
                    card1CTempList.addAll(getTempCards(file));
                } else if (XLS.getDescription().equals(extensionFile)) {
//                    getTempCardOfOldExcel(file);
                    throw new ServiceException(".xls - Данный тип файлов не обрабатывается, пересохраните файл с расширением xlsx");
                } else {
                    throw new ServiceException("Данный файл: " + file.getName() + " не является файлом Excel!");
                }
            }
        } catch (ServiceException e) {
            throw new ServiceException("Не удалось прочитать файл...", e);
        }
        return card1CTempList;
    }

    @Override
    public Map<Account, List<Card>> convertToCards(List<Card1CTemp> card1CTempList) {
        if (Version1C.VERSION_1C_7.equals(Version1CUtil.checkVersion1C(card1CTempList))) {
            return cardConverter.getCard1CV7Of(card1CTempList);
        } else {
            return cardConverter.getCard1CV8Of(card1CTempList);
        }
    }

    private ArrayList<Card1CTemp> getTempCards(File file) throws ServiceException {
        ArrayList<Card1CTemp> card1CTempList = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(file);) {
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(FIRST_SHEET);
            boolean isNewRow = false;
            int counterCell = 0;
            Card1CTemp card1CTemp = new Card1CTemp();
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (isNewRow) {
                        card1CTempList.add(card1CTemp);
                        card1CTemp = new Card1CTemp();
                        counterCell = 0;
                        isNewRow = false;
                    }
                    setValueByTempCard(counterCell, card1CTemp, cell);
                    counterCell++;
                }
                isNewRow = true;
            }
            if (card1CTemp != null) {
                card1CTempList.add(card1CTemp);
            }
            workbook.close();
        } catch (IOException e) {
            throw new ServiceException("Не удалось распарсить файл " + file);
        }
        return card1CTempList;
    }

    private void setValueByTempCard(int counterCell, Card1CTemp card1CTemp, Cell cell) {
        switch (counterCell) {
            case 0:
                card1CTemp.setCell0(extractedValueByString(cell));
                break;
            case 1:
                card1CTemp.setCell1(extractedValueByString(cell));
                break;
            case 2:
                card1CTemp.setCell2(extractedValueByString(cell));
                break;
            case 3:
                card1CTemp.setCell3(extractedValueByString(cell));
                break;
            case 4:
                card1CTemp.setCell4(extractedValueByString(cell));
                break;
            case 5:
                card1CTemp.setCell5(extractedValueByString(cell));
                break;
            case 6:
                card1CTemp.setCell6(extractedValueByString(cell));
                break;
            case 7:
                card1CTemp.setCell7(extractedValueByString(cell));
                break;
            case 8:
                card1CTemp.setCell8(extractedValueByString(cell));
                break;
            case 9:
                card1CTemp.setCell9(extractedValueByString(cell));
                break;
            default:
                break;
        }
    }

    private String extractedValueByString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return String.valueOf(cell.getStringCellValue());
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return String.valueOf(cell.getCellFormula());
            default:
                return "";
        }
    }


}

