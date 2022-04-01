package haypsilcn.hotelmanagementsystem;
import haypsilcn.hotelmanagementsystem.database.AdminDB;
import haypsilcn.hotelmanagementsystem.database.RoomDB;
import haypsilcn.hotelmanagementsystem.exceptions.AdminAlreadyExists;
import haypsilcn.hotelmanagementsystem.exceptions.AdminLoginAuthorize;
import haypsilcn.hotelmanagementsystem.exceptions.AdminNotFound;
import haypsilcn.hotelmanagementsystem.exceptions.AdminUpdatePasswordException;
import haypsilcn.hotelmanagementsystem.hotel.Hotel;
import haypsilcn.hotelmanagementsystem.login.Admin;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.chrono.IsoEra;

public class Main {
    public static void main(String[] args) throws SQLException, AdminNotFound, AdminLoginAuthorize, AdminUpdatePasswordException, AdminAlreadyExists {

//        Hotel hotel = new Hotel();

//        AdminDB adminDB = new AdminDB();
        /*Admin admin = new Admin("test", "1245");
        adminDB.loginAuthorize(admin);
        adminDB.updatePassword(admin, "124533333");*/

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/hotel-db", "root", "root");
        RoomDB roomDB = new RoomDB();
//        Admin newAdmin = new Admin("newAdmin", "123");

        /*try {
            System.out.println(adminDB.createAdmin(newAdmin));
        } catch (AdminAlreadyExists e) {
            System.out.println(e);
        }*/
        LocalDate today = LocalDate.now();

//        String query = "SELECT COUNT(type) FROM room WHERE type = '" + "Deluxe" + "'";
        /*String query = "SELECT COUNT(DISTINCT c.roomNr) FROM customer c JOIN room r ON r.roomNr = c.roomNr WHERE r.type = '" + "Single" + "' AND c.checkout <= '" + today + "' ORDER BY r.type, c.checkin";
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next())
            System.out.println(resultSet.getInt(1));*/

        /*System.out.println(roomDB.getAmountEmptyRoom("Single", LocalDate.now(), LocalDate.now().plusDays(2)));
        System.out.println(roomDB.getAmountEmptyRoom("Double", LocalDate.now(), LocalDate.now().plusDays(2)));
        System.out.println(roomDB.getAmountEmptyRoom("Deluxe", LocalDate.now(), LocalDate.now().plusDays(1)));
        System.out.println(roomDB.getAmountEmptyRoom("Luxury", LocalDate.now(), LocalDate.now().plusDays(1)));*/

        int room = roomDB.getAmountEmptyRoom("Single", LocalDate.now().plusDays(3), LocalDate.now().plusDays(9));
        System.out.println(room);
    }
}
