package haypsilcn.hotelmanagementsystem.hotel;

import haypsilcn.hotelmanagementsystem.customer.Customer;
import haypsilcn.hotelmanagementsystem.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel implements Database {


    private final ArrayList<Room> roomsList = new ArrayList<>();
    private final ArrayList<Customer> customersList = new ArrayList<>();

    private final Map<Room, List<Customer>> roomForCustomers = new HashMap<>();
    private final Map<Employee, List<Room>> employeeCleanRooms = new HashMap<>();

    public Hotel() {
        String customerQuery = "SELECT * FROM customer";

        try (Connection connection = DriverManager.getConnection(database, username, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(customerQuery);
            System.out.println("ID\t" + "first\t\t" + "last\t");
            while (resultSet.next()) {
                System.out.println(resultSet.getRow() + "\t" + resultSet.getString(2) + "\t\t" + resultSet.getString(3) + "\t\t");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void setUpDatabaseConnection() {


    }
    public void assignRoomToCustomer(Room room, List<Customer> customers) {
        /*for (Customer customer :customers) {
            if ()
        }*/
    }
}
