module haypsilcn.hotelmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens haypsilcn.hotelmanagementsystem to javafx.fxml;
    exports haypsilcn.hotelmanagementsystem;
}