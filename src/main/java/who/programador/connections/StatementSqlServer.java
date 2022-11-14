package who.programador.connections;

import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@AllArgsConstructor
public class StatementSqlServer implements IStatement{

    private final IJdbcConnection jdbcConnection;

    public PreparedStatement getPreparedStatement() {
        String sql = "INSERT INTO students (name, enrolled, progress) VALUES (?, ?, ?)";
        final Connection connection = jdbcConnection.getConnection();

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement;
    }
}
