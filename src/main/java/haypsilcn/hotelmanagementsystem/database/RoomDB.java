package haypsilcn.hotelmanagementsystem.database;

import haypsilcn.hotelmanagementsystem.customer.BookingCustomer;
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
        int bookedRoom = 0;
        int checkedRoom = 0;
        int totalEmptyRoom = 0;

        query = "SELECT SUM(roomAmount) FROM booking_customer WHERE roomType = '" + roomType +
                "' AND ((checkin < '" + checkIn + "' AND '" + checkIn + "' < checkout) " +
                "OR (checkin < '" + checkOut + "' AND '" + checkOut + "' < checkout) " +
                "OR (checkin = '" + checkIn + "' AND checkout = '" + checkOut + "')" +
                "OR ('" + checkIn + "' < checkin AND checkin < '" + checkOut + "')" +
                "OR ('" + checkIn + "' < checkout AND checkout < '" + checkOut + "'))";
                resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next())
            bookedRoom = resultSet.getInt(1);

        query = "SELECT COUNT(*) FROM customer c JOIN room r ON r.roomNr = c.roomNr " +
                "WHERE r.type = '" + roomType + "' " +
                "AND ((c.checkin < '" + checkIn + "' AND '" + checkIn + "' < c.checkout) " +
                "OR (c.checkin < '" + checkOut + "' AND '" + checkOut + "' < c.checkout)" +
                "OR (c.checkin = '" + checkIn + "' AND c.checkout = '" + checkOut + "')" +
                "OR ('" + checkIn + "' < c.checkin AND c.checkin < '" + checkOut + "') " +
                "OR ('" + checkIn + "' < c.checkout AND c.checkout < '" + checkOut + "'))";
        resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next())
            checkedRoom = resultSet.getInt(1);

        query = "SELECT COUNT(type) FROM room WHERE type = '" + roomType + "'";
        resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next())
            totalEmptyRoom = resultSet.getInt(1);

        return (totalEmptyRoom - bookedRoom - checkedRoom);
    }

    public boolean makeReservation(BookingCustomer bookingCustomer) throws SQLException {
        int roomAmount;
        query = "SELECT roomAmount FROM booking_customer WHERE first_name = '" + bookingCustomer.getFirstName() +
                "' AND last_name = '" + bookingCustomer.getLastName() + "' AND birthday = '" + bookingCustomer.getBirthday() +
                "' AND gender = '" + bookingCustomer.getGender() + "' AND roomType = '" + bookingCustomer.getRoomType() +
                "' AND checkin = '" + bookingCustomer.getCheckin() + "' AND checkout = '" + bookingCustomer.getCheckout() + "'";
        resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next()) {
            roomAmount = resultSet.getInt(1) + bookingCustomer.getRoomAmount();
            query = "UPDATE booking_customer SET roomAmount = '" + roomAmount + "' " +
                    "WHERE first_name = '" + bookingCustomer.getFirstName() + "' AND last_name = '" + bookingCustomer.getLastName() +
                    "' AND birthday = '" + bookingCustomer.getBirthday() + "' AND gender = '" + bookingCustomer.getGender() +
                    "' AND roomType = '" + bookingCustomer.getRoomType() + "' AND checkin = '" + bookingCustomer.getCheckin() +
                    "' AND checkout = '" + bookingCustomer.getCheckout() + "'";
            if (connection.createStatement().executeUpdate(query) > 0) {
                System.out.println("Update reservation for " + bookingCustomer);
                return true;
            } else
                return false;
        } else {
            query = "INSERT INTO booking_customer(first_name, last_name, birthday, gender, roomType, roomAmount, checkin, checkout) " +
                    "VALUES ('" + bookingCustomer.getFirstName() + "', '" + bookingCustomer.getLastName() + "', '" +
                    bookingCustomer.getBirthday() + "', '" + bookingCustomer.getGender() + "', '" + bookingCustomer.getRoomType() +
                    "', '" + bookingCustomer.getRoomAmount() + "', '" + bookingCustomer.getCheckin() + "', '" + bookingCustomer.getCheckout() + "')";
            if (connection.createStatement().executeUpdate(query) > 0) {
                System.out.println("Make a reservation for " + bookingCustomer);
                return true;
            } else
                return false;
        }
    }

}
