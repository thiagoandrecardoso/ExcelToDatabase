package who.programador.excel.abstracts;

import who.programador.excel.interfaces.IExcel2MySQLBehavior;

import java.io.IOException;

public abstract class Excel2Database {
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
