package who.programador.excel.abstracts;

import lombok.Getter;
import lombok.Setter;
import who.programador.excel.interfaces.IExcel2Database;

import java.io.IOException;
import java.sql.SQLException;

@Getter
@Setter
public abstract class Excel2Database {

    private String excelFilePath;

    public Excel2Database() {
    }

    public IExcel2Database iExcel2DatabaseBehavior;

    public void performSave(String excelFilePath) throws IOException {
        try {
            iExcel2DatabaseBehavior.insertInDatabase(excelFilePath);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    The Method is for changing the behavior os saving the data.
     */
    public void setExcel2DatabaseBehavior(IExcel2Database iExcel2DatabaseBehavior) {
        this.iExcel2DatabaseBehavior = iExcel2DatabaseBehavior;
    }
}
