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
        query = "SELECT r.*, cr.roomNr FROM room r LEFT JOIN customer_room cr on r.roomNr = cr.roomNr WHERE cr.roomNr IS NULL";
        return (resultSet = connection.createStatement().executeQuery(query));
    }

    public ResultSet customerRoom() throws SQLException {
        query = "SELECT c.id ,c.first_name, c.last_name, cus_room.roomNr, cus_room.checkin, cus_room.checkout FROM customer_room cus_room JOIN customer c ON cus_room.customerID = c.id";
        return (resultSet = connection.createStatement().executeQuery(query));
    }
}
