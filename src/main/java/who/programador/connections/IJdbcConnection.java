package who.programador.connections;

import java.sql.Connection;

public interface JdbcConnection {
    Connection getConnection();
}
