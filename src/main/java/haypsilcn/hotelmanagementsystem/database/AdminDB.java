package haypsilcn.hotelmanagementsystem.database;

import haypsilcn.hotelmanagementsystem.exceptions.AdminLoginAuthorize;
import haypsilcn.hotelmanagementsystem.exceptions.AdminNotFound;
import haypsilcn.hotelmanagementsystem.exceptions.AdminUpdatePasswordException;
import haypsilcn.hotelmanagementsystem.login.Admin;

import java.sql.*;

public class AdminDB implements Database{

    private final Connection connection;
    private String query;
    private ResultSet resultSet;
    private boolean validate;

    public AdminDB() throws SQLException {
        this.connection = DriverManager.getConnection(database, username, password);
    }

    private boolean usernameValidate(String user) throws SQLException, AdminNotFound {
        query = "SELECT * FROM admin WHERE user = '" + user + "'";
        resultSet = connection.createStatement().executeQuery(query);
        validate = resultSet.next();
        if (validate)
            System.out.println("Admin <<" + user + ">> exists in db with id = " + resultSet.getInt(1));
        else
            throw new AdminNotFound("Admin <<" + user + ">> NOT FOUND IN DB");
        return validate;
    }

    private boolean passwordValidate(String password) throws SQLException {
        query = "SELECT DISTINCT * FROM admin WHERE password = '" + password + "'";
        resultSet = connection.createStatement().executeQuery(query);
        return resultSet.next();
    }

    public boolean loginAuthorize(Admin admin) throws SQLException, AdminNotFound, AdminLoginAuthorize {
        if (usernameValidate(admin.getUsername())) {
            query = "SELECT * FROM admin WHERE user = '" + admin.getUsername() + "' AND password = '" + admin.getPassword() + "'";
            resultSet = connection.createStatement().executeQuery(query);
            validate = resultSet.next();
            if (validate)
                System.out.println("Successfully login as <<" + admin.getUsername() + ">>");
            else
                throw new AdminLoginAuthorize("Password incorrect! Cannot login as <<" + admin.getUsername() + ">>");
            return validate;
        } else {
            System.out.println("Cannot authorize <<" + admin.getUsername() + ">>");
            return false;
        }

    }

    public boolean updatePassword(Admin admin, String newPassword) throws SQLException, AdminNotFound, AdminLoginAuthorize, AdminUpdatePasswordException {
        if (loginAuthorize(admin)) {
            if (!admin.getPassword().equals(newPassword)) {
                query = "UPDATE admin SET password = '" + newPassword + "' WHERE user = '" + admin.getUsername() + "'";
                if (connection.createStatement().executeUpdate(query) > 0) {
                    System.out.println("<<" + admin.getUsername() + ">> has a new password = " + newPassword);
                    return true;
                } else
                    throw new AdminUpdatePasswordException("Cannot update password for <<" + admin.getUsername() + ">>");
            } else
                throw new AdminUpdatePasswordException("New password cannot be the same as old password!");
        }
        else {
            System.out.println("Cannot update new password due to unknown user <<" + admin.getUsername() + ">>");
            return false;
        }
    }

    public ResultSet showAllExcept(Admin admin) throws SQLException {
        query = "SELECT * FROM admin WHERE admin.user != '" + admin.getUsername() + "'";
        resultSet = connection.createStatement().executeQuery(query);
        return resultSet;
    }
}
