package who.programador.excel.model;

import who.programador.excel.abstracts.Excel2Database;
import who.programador.excel.impl.Excel2DatabaseStudent;

public class ExcelStudent extends Excel2Database {
    public ExcelStudent() {
        this.setExcelFilePath("src/main/resources/student.xlsx");
        this.iExcel2DatabaseBehavior = new Excel2DatabaseStudent();
    }
}
