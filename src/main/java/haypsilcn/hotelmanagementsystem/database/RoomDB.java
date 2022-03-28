package haypsilcn.hotelmanagementsystem.database;

import haypsilcn.hotelmanagementsystem.customer.Customer;
import haypsilcn.hotelmanagementsystem.exceptions.RoomAlreadyExist;
import haypsilcn.hotelmanagementsystem.hotel.Room;

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

    public boolean createRoom(Room newRoom) throws SQLException, RoomAlreadyExist {
        boolean executed;
        query = "SELECT * FROM room WHERE roomNr = '" + newRoom.getNumber() + "'";
        resultSet = connection.createStatement().executeQuery(query);
        if (!resultSet.next()) {
            query = "INSERT INTO room (roomNr, type) VALUES ('" + newRoom.getNumber() + "', '" + newRoom.getType() + "')";
            if (connection.createStatement().executeUpdate(query) > 0) {
                System.out.println("Created new <<" + newRoom.getType() + ">> room <<" + newRoom.getNumber() + ">>");
                executed = true;
            } else
                executed = false;
        } else
            throw new RoomAlreadyExist("Room <<" + newRoom.getNumber() + ">> already exists!");
        return executed;
    }

    public ResultSet getEmptyRoom(String roomType) throws SQLException {
        query = "SELECT r.roomNr FROM room r LEFT JOIN customer c ON r.roomNr = c.roomNr WHERE c.roomNr IS NULL  AND r.type = '" + roomType + "'";
        return connection.createStatement().executeQuery(query);
    }

}
