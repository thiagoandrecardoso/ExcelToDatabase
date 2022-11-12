package who.programador;

import who.programador.excel.abstracts.Excel2Database;
import who.programador.excel.model.ExcelPerson;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Excel2Database excel2Database = new ExcelPerson();
        try {
            excel2Database.performSave(excel2Database.getExcelFilePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}