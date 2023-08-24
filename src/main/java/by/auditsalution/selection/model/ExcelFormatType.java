package by.auditsalution.selection.model;

import lombok.Getter;

@Getter
public enum ExcelFormatType {

    XLSX("xlsx"),
    XLS("xls");
    private String description;
    ExcelFormatType(String description){
        this.description = description;
    }

}
