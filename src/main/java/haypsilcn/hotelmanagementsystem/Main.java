package haypsilcn.hotelmanagementsystem;
import haypsilcn.hotelmanagementsystem.database.AdminDB;
import haypsilcn.hotelmanagementsystem.exceptions.AdminLoginAuthorize;
import haypsilcn.hotelmanagementsystem.exceptions.AdminNotFound;
import haypsilcn.hotelmanagementsystem.exceptions.AdminUpdatePasswordException;
import haypsilcn.hotelmanagementsystem.hotel.Hotel;
import haypsilcn.hotelmanagementsystem.login.Admin;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, AdminNotFound, AdminLoginAuthorize, AdminUpdatePasswordException {

//        Hotel hotel = new Hotel();

        AdminDB adminDB = new AdminDB();
        Admin admin = new Admin("test", "1245");
        adminDB.loginAuthorize(admin);
        adminDB.updatePassword(admin, "124533333");

    }
}
