package who.programador.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcPostgresServerConnection implements IJdbcConnection {

    @Override
    public Connection getConnection() {
        String jdbcURL = "jdbc:postgresql://127.0.0.1:5432/postgres";
        String username = "postgres";
        String password = "suasenha";

        Connection connection;

        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}

