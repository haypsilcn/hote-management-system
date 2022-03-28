package haypsilcn.hotelmanagementsystem.database;

import haypsilcn.hotelmanagementsystem.customer.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDB implements Database{

    private final Connection connection;
    private String query;
    private ResultSet resultSet;
    private boolean validate;


    public CustomerDB() throws SQLException {
        this.connection = DriverManager.getConnection(database, username, password);
    }

    public ResultSet showAll() throws SQLException {
        query = "SELECT * FROM customer ORDER BY checkin, roomNr ";
        return connection.createStatement().executeQuery(query);
    }
    public ResultSet showBooking() throws SQLException {
        query = "SELECT * FROM booking_customer ORDER BY checkin";
        return connection.createStatement().executeQuery(query);
    }

    public boolean singleRoomCheckIn(Customer customer) {
        return validate;
    }
}
