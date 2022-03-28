package haypsilcn.hotelmanagementsystem.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDB implements Database{

    private final Connection connection;
    private String query;
    private ResultSet resultSet;

    public RoomDB() throws SQLException {
        this.connection = DriverManager.getConnection(database, username, password);

    }

    public ResultSet emptyRoom() throws SQLException {
        query = "SELECT r.* FROM room r LEFT JOIN customer c on r.roomNr = c.roomNr WHERE c.roomNr IS NULL";
        return connection.createStatement().executeQuery(query);
    }
}
