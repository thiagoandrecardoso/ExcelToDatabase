package who.programador.excel.abstracts;


import lombok.Getter;
import lombok.Setter;
import who.programador.excel.interfaces.IExcel2MySQLBehavior;

import java.io.IOException;
import java.sql.SQLException;

@Getter
@Setter
public abstract class Excel2Database {

    private String excelFilePath;
    public Excel2Database() {
    }

    public IExcel2MySQLBehavior iExcel2MySQLBehavior;

    public void performSave(String excelFilePath) throws IOException {
        try {
            iExcel2MySQLBehavior.saveInDatabase(excelFilePath);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    The Method is for changing the behavior os saving the data.
     */
    public void setExcel2MySQLBehavior(IExcel2MySQLBehavior iExcel2MySQLBehavior) {
        this.iExcel2MySQLBehavior = iExcel2MySQLBehavior;
    }
}
