package who.programador;

import who.programador.excel.Excel2Database;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Excel2Database.iteratorSheet("src/main/resources/who.xlsx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}