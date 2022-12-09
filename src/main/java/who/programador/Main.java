package who.programador;

import who.programador.excel.abstracts.Excel2Database;
import who.programador.excel.model.OnCall;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Excel2Database excel2Database = new OnCall();
        try {
            excel2Database.performSave(excel2Database.getExcelFilePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}