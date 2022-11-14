package who.programador.excel.interfaces;

import java.io.IOException;
import java.sql.SQLException;

public interface IExcel2MySQLBehavior {
    void saveInDatabase(String excelFilePath) throws IOException, SQLException;
}
