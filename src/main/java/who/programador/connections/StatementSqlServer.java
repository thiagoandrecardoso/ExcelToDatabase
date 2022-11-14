package who.programador.excel.tosql;

import lombok.AllArgsConstructor;
import who.programador.connections.IJdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@AllArgsConstructor
public class ToSQLServer {

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
