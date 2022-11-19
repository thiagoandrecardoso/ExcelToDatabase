package who.programador.excel.model;

import who.programador.excel.abstracts.Excel2Database;
import who.programador.excel.impl.Excel2DatabaseOnCall;

public class ExcelOnCall extends Excel2Database {
    public ExcelOnCall() {
        this.setExcelFilePath("src/main/resources/call.xlsx");
        this.iExcel2DatabaseBehavior = new Excel2DatabaseOnCall();
    }
}
