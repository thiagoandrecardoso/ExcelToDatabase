package who.programador.excel.model;

import who.programador.connections.SQLServerConnection;
import who.programador.connections.StatementSqlServer;
import who.programador.excel.abstracts.Excel2Database;
import who.programador.excel.impl.Excel2MySQLStudent;

public class ExcelStudent extends Excel2Database {
    public ExcelStudent() {
        this.setExcelFilePath("src/main/resources/student.xlsx");
        this.iExcel2MySQLBehavior = new Excel2MySQLStudent();
    }
}
