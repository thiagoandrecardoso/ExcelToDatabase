package who.programador.connections.impls;

import who.programador.connections.interfaces.IJdbcConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcPostgresServerConnection implements IJdbcConnection {

    @Override
    public Connection getConnection() {
        String jdbcURL = "jdbc:postgresql://127.0.0.1:5432/postgres";
        String username = "postgres";
        String password = "suasenha";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}

