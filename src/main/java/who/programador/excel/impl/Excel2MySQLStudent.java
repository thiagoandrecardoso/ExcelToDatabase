package who.programador.excel.impl;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import who.programador.connections.IStatement;
import who.programador.excel.interfaces.IExcel2MySQLBehavior;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;

@AllArgsConstructor
public class Excel2MySQLStudent implements IExcel2MySQLBehavior {

    private final IStatement statement;
    @Override
    public void saveInDatabase(String excelFilePath) throws IOException, SQLException {

        final PreparedStatement preparedStatement = statement.getPreparedStatement();
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
                        String name = nextCell.getStringCellValue();
                        preparedStatement.setString(1, name);
                        break;
                    case 1:
                        Date enrollmentDate = nextCell.getDateCellValue();
                        preparedStatement.setDate(2, new java.sql.Date(enrollmentDate.getTime()));
                        break;
                    case 2:
                        int progress = (int) nextCell.getNumericCellValue();
                        preparedStatement.setInt(3, progress);
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
