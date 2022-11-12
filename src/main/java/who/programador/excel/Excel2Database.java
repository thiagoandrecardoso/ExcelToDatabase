package who.programador.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class Excel2Database {

    public static Workbook getWorkbook(String excelFilePath) {
        try {
            FileInputStream inputStream = new FileInputStream(excelFilePath);
            return new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Iterator<Row> getRowIterator( Workbook workbook ){
        Sheet firstSheet = workbook.getSheetAt(0);
        return firstSheet.iterator();
    }

    public static void saveInDatabase(String excelFilePath) throws IOException {
        Workbook workbook = getWorkbook(excelFilePath);
        Iterator<Row> rowIterator = getRowIterator(workbook);
        rowIterator.next();

        while (rowIterator.hasNext()) {
            Row nextRow = rowIterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();

                int columnIndex = nextCell.getColumnIndex();
                if (columnIndex == 0) {
                    System.out.println(nextCell.getStringCellValue());
                }
                if (columnIndex == 1) {
                    System.out.println(nextCell.getNumericCellValue());
                }
            }
        }
        workbook.close();
    }
}
