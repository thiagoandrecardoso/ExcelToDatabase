package who.programador.excel.model;

import who.programador.excel.abstracts.Excel2Database;
import who.programador.excel.impl.Excel2DatabaseStudent;

public class Student extends Excel2Database {
    public Student() {
        this.setExcelFilePath("src/main/resources/student.xlsx");
        this.iExcel2DatabaseBehavior = new Excel2DatabaseStudent();
    }
}
