package who.programador.excel.interfaces;

import java.io.IOException;
import java.sql.SQLException;

public interface IExcel2DatabaseBehavior {
    void insertInDatabase(String excelFilePath) throws IOException, SQLException;
}
