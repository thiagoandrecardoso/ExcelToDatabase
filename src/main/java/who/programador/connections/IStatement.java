package who.programador.connections;

import java.sql.PreparedStatement;

public interface IStatement {
    PreparedStatement getPreparedStatementInsertIntoStudents();
    PreparedStatement getPreparedStatementInsertIntoOnCall();
}
