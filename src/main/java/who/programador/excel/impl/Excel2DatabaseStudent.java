package who.programador.excel.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import who.programador.connections.interfaces.IStatement;
import who.programador.connections.impls.StatementDatabaseServer;
import who.programador.excel.interfaces.IExcel2DatabaseBehavior;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;

public class Excel2DatabaseStudent implements IExcel2DatabaseBehavior {

    private final PreparedStatement preparedStatement;

    public Excel2DatabaseStudent() {
        IStatement statement = new StatementDatabaseServer();
        preparedStatement = statement.getPreparedStatementInsertIntoStudents();
    }

    @Override
    public void insertInDatabase(String excelFilePath) throws IOException, SQLException {

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
                        Date enrollDate = nextCell.getDateCellValue();
                        preparedStatement.setTimestamp(2, new Timestamp(enrollDate.getTime()));
                        break;
                    case 2:
                        int progress = (int) nextCell.getNumericCellValue();
                        preparedStatement.setInt(3, progress);
                        break;
                }
            }
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        }
        workbook.close();
        // execute the remaining queries
        preparedStatement.executeBatch();

        preparedStatement.getConnection().commit();
        preparedStatement.getConnection().close();
    }

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