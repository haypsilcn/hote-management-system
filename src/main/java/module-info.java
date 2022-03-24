module haypsilcn.hotelmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.mariadb.jdbc;


    opens haypsilcn.hotelmanagementsystem to javafx.fxml;
    exports haypsilcn.hotelmanagementsystem;
    exports haypsilcn.hotelmanagementsystem.login;
}