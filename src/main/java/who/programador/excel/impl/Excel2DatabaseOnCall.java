package who.programador.excel.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import who.programador.connections.IStatement;
import who.programador.connections.StatementDatabaseServer;
import who.programador.excel.interfaces.IExcel2Database;
import who.programador.excel.model.OmCallData;
import who.programador.excel.shared.ExcelMethod;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Excel2DatabaseOnCall implements IExcel2Database {

    private final PreparedStatement preparedStatement;
    private final List<OmCallData> onCallDataList;

    public Excel2DatabaseOnCall() {
        IStatement statement = new StatementDatabaseServer();
        preparedStatement = statement.getPreparedStatementInsertIntoOnCall();
        onCallDataList = new ArrayList<>();
    }

    private OmCallData getOnCallDataByName(String name) {
        if (onCallDataList.isEmpty()) return null;

        for (OmCallData oc : onCallDataList) {
            if (name.equals(oc.getName())) return oc;
        }
        return null;
    }

    @Override
    public void insertInDatabase(String excelFilePath) throws IOException, SQLException {
        Workbook workbook = ExcelMethod.getWorkbook(excelFilePath);
        Iterator<Row> rowIterator = ExcelMethod.getRowIterator(workbook);
        rowIterator.next();
        populateListOnCallDataList(rowIterator);
        workbook.close();
        onCallDataListtoDataBase();
    }

    private void populateListOnCallDataList(Iterator<Row> rowIterator) {
        while (rowIterator.hasNext()) {
            Row nextRow = rowIterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            OmCallData onCallData = new OmCallData();
            String hourBegin = "00:00:00";

            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                int columnIndex = nextCell.getColumnIndex();

                switch (columnIndex) {
                    case 0:
                        String name = nextCell.getStringCellValue();
                        onCallData = getOnCallDataByName(name);
                        if (onCallData == null) {
                            onCallData = new OmCallData();
                            onCallData.setName(name);
                            onCallDataList.add(onCallData);
                        }
                        break;
                    case 1:
                        hourBegin = nextCell.getStringCellValue();
                        break;
                    case 2:
                        String hourEnd = nextCell.getStringCellValue();
                        LocalTime localTimeBegin = LocalTime.parse(hourBegin);
                        LocalTime localTimeEnd = LocalTime.parse(hourEnd);
                        long diference = localTimeBegin.until(localTimeEnd, ChronoUnit.MINUTES);
                        onCallData.addTimeInMinute(diference);
                        break;
                }
            }
        }
    }

    private void onCallDataListtoDataBase() throws SQLException {
        final long JONAS_VALUE_HOUR = 40L;
        final long MATHEUS_VALUE_HOUR = 33L;
        final long THIAGO_VALUE_HOUR = 43L;
        final long LUCAS_VALUE_HOUR = 55L;

        for (OmCallData omCallData : onCallDataList) {
            preparedStatement.setString(1, omCallData.getName());
            preparedStatement.setLong(2, omCallData.getTotalTimeInMinute());
            if ("Jonas".equals(omCallData.getName())) {
                preparedStatement.setLong(3, omCallData.calculateValue(JONAS_VALUE_HOUR));
                preparedStatement.setLong(4, omCallData.calculateValue(JONAS_VALUE_HOUR / 3));
            } else if ("Matheus".equals(omCallData.getName())) {
                preparedStatement.setLong(3, omCallData.calculateValue(MATHEUS_VALUE_HOUR));
                preparedStatement.setLong(4, omCallData.calculateValue(MATHEUS_VALUE_HOUR / 3));
            } else if ("Thiago".equals(omCallData.getName())) {
                preparedStatement.setLong(3, omCallData.calculateValue(THIAGO_VALUE_HOUR));
                preparedStatement.setLong(4, omCallData.calculateValue(THIAGO_VALUE_HOUR / 3));
            } else if ("Lucas".equals(omCallData.getName())) {
                preparedStatement.setLong(3, omCallData.calculateValue(LUCAS_VALUE_HOUR));
                preparedStatement.setLong(4, omCallData.calculateValue(LUCAS_VALUE_HOUR / 3));
            } else {
                preparedStatement.setLong(3, 0);
                preparedStatement.setLong(4, 0);
            }
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        }

        preparedStatement.getConnection().commit();
        preparedStatement.getConnection().close();
    }
}
