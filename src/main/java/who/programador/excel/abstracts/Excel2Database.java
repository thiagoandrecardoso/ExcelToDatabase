package who.programador.excel.abstracts;


import lombok.Getter;
import lombok.Setter;
import who.programador.excel.interfaces.IExcel2MySQLBehavior;

import java.io.IOException;

@Getter
@Setter
public abstract class Excel2Database {

    private String excelFilePath;
    public Excel2Database() {
    }

    public IExcel2MySQLBehavior iExcel2MySQLBehavior;

    public void performSave(String excelFilePath) throws IOException {
        iExcel2MySQLBehavior.saveInDatabase(excelFilePath);
    }

    public void setiExcel2MySQLBehavior(IExcel2MySQLBehavior iExcel2MySQLBehavior) {
        this.iExcel2MySQLBehavior = iExcel2MySQLBehavior;
    }
}
