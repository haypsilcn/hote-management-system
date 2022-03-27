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

        /*AdminDB adminDB = new AdminDB();
        Admin admin = new Admin("test", "1245");
        adminDB.loginAuthorize(admin);
        adminDB.updatePassword(admin, "124533333");*/

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/hotel-db", "root", "root");
        String query = "SELECT DISTINCT r.*, cr.roomNr FROM room r LEFt JOIN  customer_room cr on r.roomNr = cr.roomNr WHERE cr.roomNr IS NULL";
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        while (resultSet.next())
            System.out.println(resultSet.getInt(1));


    }
}
