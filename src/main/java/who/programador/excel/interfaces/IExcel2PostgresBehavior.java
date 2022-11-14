package who.programador.excel.interfaces;

import java.io.IOException;
import java.sql.SQLException;

public interface IExcel2PostgresBehavior {
    void saveInDatabase(String excelFilePath) throws IOException, SQLException;
}
