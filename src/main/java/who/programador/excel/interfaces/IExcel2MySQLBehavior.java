package who.programador.excel.interfaces;

import java.io.IOException;

public interface IExcel2MySQLBehavior {
    public void saveInDatabase(String excelFilePath) throws IOException;
}
