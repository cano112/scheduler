package pl.edu.agh.wiet.studiesplanner.parser.services;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.model.service.DocumentFetcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("excelSheetImporter")
public class ExcelSheetImporter implements DocumentFetcher {

    @Override
    public List<List<Object>> getDocument(String url) {
        return importExcel(url);
    }

    public List<List<Object>> importExcel(String filePath) {
        List<List<Object>> importedSheet = new ArrayList<>();
        Workbook workbook = null;

        try {workbook = WorkbookFactory.create(new File(filePath));}
        catch (Exception e) {e.printStackTrace();}
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();

        for (Row row: sheet) {
            List<Object> list = new ArrayList<>();
            for(Cell cell: row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
            }
            // remove blank cells from the end
            for(int i=list.size()-1; list.get(i).equals(""); i--) {
                list.remove(i);
            }
            importedSheet.add(list);
        }
        try {workbook.close();}
        catch (IOException e) {e.printStackTrace();}

        return importedSheet;
    }
}