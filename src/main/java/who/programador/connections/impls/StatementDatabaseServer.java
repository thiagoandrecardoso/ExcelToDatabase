package who.programador.connections.impls;

import who.programador.connections.interfaces.IJdbcConnection;
import who.programador.connections.interfaces.IStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class StatementDatabaseServer implements IStatement {

    private final IJdbcConnection jdbcConnection;

    public StatementDatabaseServer() {
        jdbcConnection = new JdbcPostgresServerConnection();
    }

    public StatementDatabaseServer(IJdbcConnection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    public PreparedStatement getPreparedStatementInsertIntoStudents() {
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

    public PreparedStatement getPreparedStatementInsertIntoOnCall(){
        String sql = "INSERT INTO oncall (name, totaltime, totalvalue) VALUES (?, ?, ?)";
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
