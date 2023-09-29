package by.auditsalution.selection.service;

import by.auditsalution.selection.model.InitialData;
import by.auditsalution.selection.model.OutputResult;
import by.auditsalution.selection.model.SampleResult;

import java.io.IOException;

public interface ExcelService {

    void writeToExcel(OutputResult outputFormResult, SampleResult sampleResult, InitialData initialValue) throws IOException;

}
