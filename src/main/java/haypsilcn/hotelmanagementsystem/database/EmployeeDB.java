package haypsilcn.hotelmanagementsystem.database;

import haypsilcn.hotelmanagementsystem.exceptions.EmployeeAlreadyExist;
import haypsilcn.hotelmanagementsystem.hotel.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDB implements Database{

    private final Connection connection;
    private String query;
    private ResultSet resultSet;

    public EmployeeDB() throws SQLException {
        this.connection = DriverManager.getConnection(database, username, password);
    }

    public ResultSet showAll() throws SQLException {
        query = "SELECT * FROM employee";
        return connection.createStatement().executeQuery(query);
    }

    public boolean createEmployee(Employee newEmployee) throws SQLException, EmployeeAlreadyExist {
        boolean executed;
        query = "SELECT * FROM employee WHERE " +
                "firstName = '" + newEmployee.getFirstName() + "' AND " +
                "lastName = '" + newEmployee.getLastName() + "' AND " +
                "gender = '" + newEmployee.getGender() + "' AND " +
                "dob = '" + newEmployee.getDob() + "' AND " +
                "department = '" + newEmployee.getDepartment() + "'";
        resultSet = connection.createStatement().executeQuery(query);

        // if query don't return an existing employee then create new employee
        // otherwise, return false
        if (!resultSet.next()) {
            query = "INSERT INTO employee (firstName, lastName, dob, gender, department) " +
                    "VALUES ('" + newEmployee.getFirstName() + "', '" + newEmployee.getLastName() +
                    "', '" + newEmployee.getDob() + "', '" + newEmployee.getGender() + "', '" +
                    newEmployee.getdepartment() + "')";
            if (connection.createStatement().executeUpdate(query) > 0) {
                System.out.println("Created new employee:\nFirst name: " + newEmployee.getFirstName() +
                        "\nLast name: " + newEmployee.getLastName() + "\nDoB: " + newEmployee.getDob() +
                        "\nGender: " + newEmployee.getGender() + "\nDepartment: " + newEmployee.getDepartment());
                executed = true;
            } else
                executed = false;
        } else
            throw new EmployeeAlreadyExist("Employee <<" + newEmployee.getFirstName() + " " + newEmployee.getLastName() + ">> is already exists!");
        return executed;
    }
}
