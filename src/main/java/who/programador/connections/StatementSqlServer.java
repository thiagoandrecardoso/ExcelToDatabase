package who.programador.connections;

import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class StatementSqlServer implements IStatement{

    private final IJdbcConnection jdbcConnection = new SQLServerConnection();
    private Connection connection = null;

    public PreparedStatement getPreparedStatement() {
        String sql = "INSERT INTO students (name, enrolled, progress) VALUES (?, ?, ?)";
        connection = jdbcConnection.getConnection();

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement;
    }

    public Connection getConnection() {
        return connection;
    }
}
