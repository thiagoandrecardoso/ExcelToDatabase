package who.programador.excel.model;

import who.programador.excel.abstracts.Excel2Database;
import who.programador.excel.impl.Excel2MySQLPerson;

public class ExcelPerson extends Excel2Database {
    public ExcelPerson() {
        this.iExcel2MySQLBehavior = new Excel2MySQLPerson();
    }
}
