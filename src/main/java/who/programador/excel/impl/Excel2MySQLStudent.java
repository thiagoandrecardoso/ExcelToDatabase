package who.programador.excel.impl;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import who.programador.connections.IStatement;
import who.programador.connections.StatementSqlServer;
import who.programador.excel.interfaces.IExcel2MySQLBehavior;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;

public class Excel2MySQLStudent implements IExcel2MySQLBehavior {

    private final IStatement statement = new StatementSqlServer();
    @Override
    public void saveInDatabase(String excelFilePath) throws IOException, SQLException {

        final PreparedStatement preparedStatement = statement.getPreparedStatement();
        Workbook workbook = getWorkbook(excelFilePath);
        Iterator<Row> rowIterator = getRowIterator(workbook);
        rowIterator.next();
        int batchSize = 20;
        int count = 0;

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

    public static Iterator<Row> getRowIterator(Workbook workbook ){
        Sheet firstSheet = workbook.getSheetAt(0);
        return firstSheet.iterator();
    }
}
