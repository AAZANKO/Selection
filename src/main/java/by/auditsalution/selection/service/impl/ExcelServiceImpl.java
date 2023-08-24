package by.auditsalution.selection.service.impl;

import by.auditsalution.selection.model.*;
import by.auditsalution.selection.service.ExcelService;
import by.auditsalution.selection.util.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static by.auditsalution.selection.model.ExcelFormatType.XLSX;
import static by.auditsalution.selection.model.InputOutputType.OUTPUT;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public void writeToExcel(OutputResult outputFormResult, SampleResult sampleResult) throws IOException {

        Path originalPath = Paths.get("src","main","resources", "template","templateOutputFile.xlsx");

        String outputFileName = FileNameUtil.getOutputFileName() + "_УНП." + XLSX.getDescription();
        String workFilePath = FilePathUtil.getPathAndCreatePackage(OUTPUT.getDescription());
        Path copiedPath = Paths.get(workFilePath, outputFileName);
        Workbook workbook = null;
        try (FileInputStream inputStream = new FileInputStream(originalPath.toFile());
             FileOutputStream outputStream = new FileOutputStream(copiedPath.toFile());) {
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(1);

            // ----- получение стиля ------
            Row existingRow = sheet.getRow(34);
            Cell existingCell = existingRow.getCell(1);
            CellStyle currentStyle = existingCell.getCellStyle();


            insertIntoRandomTable(outputFormResult, sheet, currentStyle);
            insertIntoMaxTable(outputFormResult, sheet, currentStyle);

            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
    }

    private void insertIntoMaxTable(OutputResult outputFormResult, Sheet sheet, CellStyle currentStyle) {
        Map<Account, List<Card>> accountMaxMap = outputFormResult.getAccountMaxMap();
        for (Account account : accountMaxMap.keySet()) {
            List<Card> card1CS = accountMaxMap.get(account);
            if (card1CS != null && !card1CS.isEmpty()) {
                Coordinates coordinates = CoordinatesUtil.getCoordinates(account);
                int positionRandom = coordinates.getPositionMax();
                int counter = 1;
                for (Card card1C : card1CS) {

                    Row row = sheet.createRow(positionRandom);

                    Cell cell = row.createCell(0);
                    cell.setCellValue(counter);
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(1);
                    cell.setCellValue(card1C.getPeriod());
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(2);
                    cell.setCellValue(card1C.getDebitAccount());
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(3);
                    cell.setCellValue(card1C.getCreditAccount());
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(4);
                    cell.setCellValue(Math.max(card1C.getDebitSum(), card1C.getCreditSum()));
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(5);
                    cell.setCellValue(getAmountValue(card1C));
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(6);
                    cell.setCellValue(card1C.getAmountCurrency() != null ? card1C.getAmountCurrency().getName() : "x");
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(7);
                    cell.setCellValue(card1C.getAmountCurrency() != null ? CurrencyRatesUtil.getExchange(card1C.getPeriod(), card1C.getAmountCurrency().getName()) : "x");
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(8);
                    cell.setCellValue(card1C.getAmountCurrency() != null ? card1C.getAmountCurrency().getValue() : "x");
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(9);
                    cell.setCellValue("выполнен при необх-ти"); // +
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(10);
                    cell.setCellValue("выполнен при необх-ти- гр. 11"); // +
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(11);
                    cell.setCellValue(Math.max(card1C.getDebitSum(), card1C.getCreditSum()));
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(12);
                    cell.setCellValue("х"); // +
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(13);
                    cell.setCellValue(card1C.getDocument());
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(14);
                    cell.setCellValue(card1C.getAnalyticsDt());
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(15);
                    cell.setCellValue(AnalyticsUtil.getAnalyticsDebit(card1C.getDocument()));
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(16);
                    cell.setCellValue(AnalyticsUtil.getAnalyticsCredit(card1C.getDocument()));
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(17);
                    cell.setCellValue("стат.выборка-случайная"); // +
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(18);
                    cell.setCellValue("-"); // +
                    cell.setCellStyle(currentStyle);

                    counter++;
                    positionRandom++;
                }
            }
        }
    }

    private void insertIntoRandomTable(OutputResult outputFormResult, Sheet sheet, CellStyle currentStyle) {
        Map<Account, List<Card>> accountRandomMap = outputFormResult.getAccountRandomMap();
        for (Account account : accountRandomMap.keySet()) {
            List<Card> card1CS = accountRandomMap.get(account);
            if (card1CS != null && !card1CS.isEmpty()) {
                Coordinates coordinates = CoordinatesUtil.getCoordinates(account);
                int positionRandom = coordinates.getPositionRandom();
                int counter = 1;
                for (Card card1C : card1CS) {

                    Row row = sheet.createRow(positionRandom);

                    Cell cell = row.createCell(0);
                    cell.setCellValue(counter);
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(1);
                    cell.setCellValue(card1C.getPeriod());
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(2);
                    cell.setCellValue(card1C.getDebitAccount());
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(3);
                    cell.setCellValue(card1C.getCreditAccount());
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(4);
                    cell.setCellValue(Math.max(card1C.getDebitSum(), card1C.getCreditSum()));
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(5);
                    cell.setCellValue(getAmountValue(card1C));
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(6);
                    cell.setCellValue(card1C.getAmountCurrency() != null ? card1C.getAmountCurrency().getName() : "x");
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(7);
                    cell.setCellValue(card1C.getAmountCurrency() != null ? CurrencyRatesUtil.getExchange(card1C.getPeriod(), card1C.getAmountCurrency().getName()) : "x");
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(8);
                    cell.setCellValue(card1C.getAmountCurrency() != null ? card1C.getAmountCurrency().getValue() : "x");
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(9);
                    cell.setCellValue("выполнен при необх-ти"); // +
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(10);
                    cell.setCellValue("выполнен при необх-ти- гр. 11"); // +
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(11);
                    cell.setCellValue(Math.max(card1C.getDebitSum(), card1C.getCreditSum()));
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(12);
                    cell.setCellValue("х"); // +
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(13);
                    cell.setCellValue(card1C.getDocument());
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(14);
                    cell.setCellValue(card1C.getAnalyticsDt());
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(15);
                    cell.setCellValue(AnalyticsUtil.getAnalyticsDebit(card1C.getDocument()));
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(16);
                    cell.setCellValue(AnalyticsUtil.getAnalyticsCredit(card1C.getDocument()));
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(17);
                    cell.setCellValue("стат.выборка-случайная"); // +
                    cell.setCellStyle(currentStyle);

                    cell = row.createCell(18);
                    cell.setCellValue("-"); // +
                    cell.setCellStyle(currentStyle);

                    counter++;
                    positionRandom++;
                }
            }
        }
    }



    private void insertIntoSaldoMaxTable(OutputResult outputFormResult, Sheet sheet, CellStyle currentStyle) {

    }

    private void insertIntoSaldoRandomTable(OutputResult outputFormResult, Sheet sheet, CellStyle currentStyle) {

    }


    private String getAmountValue(Card card1C) {
        return card1C.getAmount() != null ? getValueString(card1C) : "x";
    }

    private String getValueString(Card card1C) {
        return !Objects.equals(card1C.getAmount().getValue(), "") ? card1C.getAmount().getValue() : "x";
    }


}
