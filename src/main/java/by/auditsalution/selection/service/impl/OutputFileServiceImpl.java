package by.auditsalution.selection.service.impl;

import by.auditsalution.selection.service.OutputFileService;
import org.springframework.stereotype.Service;

@Service
public class OutputFileServiceImpl implements OutputFileService {

    /**
     * XLSX Classes
     * <p>
     * XSSFWorkbook: это класс, представляющий файл XLSX.
     * XSSFSheet: это класс, представляющий лист в файле XLSX.
     * XSSFRow: это класс, представляющий строку на листе файла XLSX.
     * XSSFCell: это класс, представляющий ячейку в строке файла XLSX.
     */

    /** СОхранить стиль Экселя
     *
     * //read existing style
     * Row existingRow = sheet.getRow(headerRowIndex);
     * Cell existingCell = existingRow.getCell(0);
     * CellStyle currentStyle = existingCell.getCellStyle();
     *
     *
     * //apply date style here
     * Date date = StringUtil.toDate(map.get(column.getHeaderName()));
     * cell.setCellValue(date);
     * //apply previous style
     * cell.setCellStyle(currentStyle);
     *
     */
    @Override
    public void copyOutputTemplateFile(){



    }

}
