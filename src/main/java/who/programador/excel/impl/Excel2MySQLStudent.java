package who.programador.excel.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import who.programador.excel.interfaces.IExcel2MySQLBehavior;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class Excel2MySQLStudent implements IExcel2MySQLBehavior {
    @Override
    public void saveInDatabase(String excelFilePath) throws IOException {
        Workbook workbook = getWorkbook(excelFilePath);
        Iterator<Row> rowIterator = getRowIterator(workbook);
        rowIterator.next();

        while (rowIterator.hasNext()) {
            Row nextRow = rowIterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                int columnIndex = nextCell.getColumnIndex();

                switch (columnIndex) {
                    case 0:
                        System.out.println(nextCell.getStringCellValue());
                        break;
                    case 1:
                        System.out.println(nextCell.getDateCellValue());
                        break;
                    case 2:
                        System.out.println(nextCell.getNumericCellValue());
                        break;
                }
            }
        }
        workbook.close();
    }

    public static Workbook getWorkbook(String excelFilePath) {
        try {
            FileInputStream inputStream = new FileInputStream(excelFilePath);
            return new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Iterator<Row> getRowIterator(Workbook workbook ){
        Sheet firstSheet = workbook.getSheetAt(0);
        return firstSheet.iterator();
    }
}
