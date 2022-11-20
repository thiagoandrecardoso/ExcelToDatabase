package who.programador.excel.shared;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelMethod {
    public static Workbook getWorkbook(String excelFilePath) {
        try {
            FileInputStream inputStream = new FileInputStream(excelFilePath);
            return new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Iterator<Row> getRowIterator(Workbook workbook) {
        Sheet firstSheet = workbook.getSheetAt(0);
        return firstSheet.iterator();
    }
}
