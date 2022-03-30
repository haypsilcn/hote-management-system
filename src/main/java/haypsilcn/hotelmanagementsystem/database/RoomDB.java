package haypsilcn.hotelmanagementsystem.database;

import haypsilcn.hotelmanagementsystem.customer.Customer;
import haypsilcn.hotelmanagementsystem.exceptions.RoomAlreadyExist;
import haypsilcn.hotelmanagementsystem.hotel.Room;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RoomDB implements Database{

    private final Connection connection;
    private String query;
    private ResultSet resultSet;
    private boolean executed;

    public RoomDB() throws SQLException {
        this.connection = DriverManager.getConnection(database, username, password);

    }

    public ResultSet emptyRoom() throws SQLException {
        query = "SELECT r.* FROM room r LEFT JOIN customer c on r.roomNr = c.roomNr WHERE c.roomNr IS NULL";
        return connection.createStatement().executeQuery(query);
    }

    public boolean createRoom(Room newRoom) throws SQLException, RoomAlreadyExist {
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

    public boolean checkOut(int roomNumber) throws SQLException {
        query = "DELETE FROM customer WHERE roomNr = '" + roomNumber + "'";
        if (connection.createStatement().executeUpdate(query) > 0) {
            System.out.println("Room <<" + roomNumber + ">> has been checkout");
            executed = true;
        } else
            executed = false;
        return executed;
    }

    public boolean checkIn(Customer newCustomer) throws SQLException {
        query = "INSERT INTO customer(first_name, last_name, birthday, gender, roomNr, checkin, checkout) " +
                "VALUES ('" + newCustomer.getFirstName() + "', '" + newCustomer.getLastName() + "', '" +
                newCustomer.getBirthday() + "', '" + newCustomer.getGender() + "', '" +
                newCustomer.getRoom() + "', '" + newCustomer.getCheckin() + "', '" + newCustomer.getCheckout() + "')";
        if (connection.createStatement().executeUpdate(query) > 0) {
            System.out.println("Checking in for <<" + newCustomer.getFirstName() + ", " + newCustomer.getLastName() + ">> in room <<" + newCustomer.getRoom() + ">>");
            executed = true;
        } else
            executed = false;
        return executed;
    }

    public ResultSet getEmptyRoom(String roomType) throws SQLException {
        query = "SELECT r.roomNr FROM room r LEFT JOIN customer c ON r.roomNr = c.roomNr WHERE c.roomNr IS NULL  AND r.type = '" + roomType + "'";
        return connection.createStatement().executeQuery(query);
    }

    public ResultSet getCheckOutRoom() throws SQLException {
        query = "SELECT DISTINCT roomNr FROM customer";
        return connection.createStatement().executeQuery(query);
    }

    public int getAmountEmptyRoom(String roomType, LocalDate checkIn, LocalDate checkOut) throws SQLException {
        int busyRoom = 0;
        int totalRoom = 0;
        query = "SELECT COUNT(DISTINCT c.roomNr) FROM customer c JOIN room r ON r.roomNr = c.roomNr " +
                "WHERE r.type = '" + roomType + "' AND c.checkin < '" + checkIn + "' AND c.checkout >= '" + checkOut + "'";
        resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next())
            busyRoom = resultSet.getInt(1);
        query = "SELECT COUNT(type) FROM room WHERE type = '" + roomType + "'";
        resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next())
            totalRoom = resultSet.getInt(1);

        return (totalRoom - busyRoom);
    }

}
