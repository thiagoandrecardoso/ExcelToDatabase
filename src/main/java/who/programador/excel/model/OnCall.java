package who.programador.excel.model;

import who.programador.excel.abstracts.Excel2Database;
import who.programador.excel.impl.Excel2DatabaseOnCall;

public class OnCall extends Excel2Database {
    public OnCall() {
        this.setExcelFilePath("src/main/resources/call.xlsx");
        this.iExcel2DatabaseBehavior = new Excel2DatabaseOnCall();
    }
}
