package who.programador.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class StatementPostgresServer implements IStatement {

    private final IJdbcConnection jdbcConnection = new PostgresServerConnection();

    public PreparedStatement getPreparedStatement() {
        String sql = "INSERT INTO students (name, enrolled, progress) VALUES (?, ?, ?)";
        Connection connection = jdbcConnection.getConnection();

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement;
    }
}
